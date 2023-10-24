package io.github.xuefm.combiner;

import io.github.xuefm.config.ImageCombinerConfig;
import io.github.xuefm.element.Element;
import io.github.xuefm.enums.OutputFormat;
import io.github.xuefm.exception.ImageBuildException;
import io.github.xuefm.painter.IPainter;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ImageCombiner的默认实现
 */
public class DefaultImageCombiner extends AbstractImageCombiner {


    private DefaultImageCombiner(List<Element> elementList, int canvasWidth, int canvasHeight, Color backgroundColor, OutputFormat outputFormat, Integer roundCorner, Float quality) {
        super(elementList, canvasWidth, canvasHeight, backgroundColor, outputFormat, roundCorner, quality);
    }

    /**
     * 默认白色背景
     *
     * @param canvasWidth  画布宽
     * @param canvasHeight 画布高
     * @param outputFormat 文件类型
     * @param roundCorner  圆角
     * @param quality      图片质量
     * @return ImageCombiner
     */
    public static ImageCombiner of(int canvasWidth, int canvasHeight, OutputFormat outputFormat, Integer roundCorner, Float quality) {
        return new DefaultImageCombiner(new ArrayList<>(), canvasWidth, canvasHeight, Color.WHITE, outputFormat, roundCorner, quality);
    }

    /**
     * @param canvasWidth     画布宽
     * @param canvasHeight    画布高
     * @param outputFormat    文件类型
     * @param backgroundColor 背景颜色 (当图片至此透明时，为null时为透明背景)
     * @param roundCorner     圆角
     * @param quality         图片质量
     * @return ImageCombiner
     */
    public static ImageCombiner of(int canvasWidth, int canvasHeight, Color backgroundColor, OutputFormat outputFormat, Integer roundCorner, Float quality) {
        return new DefaultImageCombiner(new ArrayList<>(), canvasWidth, canvasHeight, backgroundColor, outputFormat, roundCorner, quality);
    }

    /**
     * image添加圆角
     *
     * @param image
     * @param cornerRadius
     * @return
     */
    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = output.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // 绘制圆角矩形到 BufferedImage 上
        RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, w, h, cornerRadius, cornerRadius);
        g2d.setPaint(new Color(0, 0, 0, 0)); // 设置填充颜色为透明色
        g2d.fill(roundRect);

        // 将 Graphics2D 对象的剪切区域设置为圆角矩形
        g2d.setClip(roundRect);

        // 将要添加圆角的图片绘制到 BufferedImage 上
        g2d.drawImage(image, 0, 0, null);

        g2d.dispose();
        return output;
    }

    @Override
    public ImageCombiner generate() {

        //根据图片类型指定颜色模型
        BufferedImage image;
        switch (canvasProperty.outputFormat) {
            case JPG, JPEG ->
                    image = new BufferedImage(this.canvasProperty.canvasWidth, this.canvasProperty.canvasHeight, BufferedImage.TYPE_INT_RGB);
            case PNG ->
                    image = new BufferedImage(this.canvasProperty.canvasWidth, this.canvasProperty.canvasHeight, BufferedImage.TYPE_INT_ARGB);
            case BMP ->
                    image = new BufferedImage(this.canvasProperty.canvasWidth, this.canvasProperty.canvasHeight, BufferedImage.TYPE_3BYTE_BGR);
            default -> throw new ImageBuildException("不支持该文件格式");
        }

        Graphics2D g2d = image.createGraphics();
        //设置抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        //在图片上绘制背景色
        if (Objects.nonNull(canvasProperty.backgroundColor)) {
            g2d.setColor(canvasProperty.backgroundColor);
            g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        }
        //逐个绘制每个元素
        for (Element element : this.elementList) {
            IPainter instance = ImageCombinerConfig.getInstance(element);
            instance.draw(g2d, element, canvasProperty);
        }
        g2d.dispose();
        //处理画布圆角
        if (Objects.nonNull(this.canvasProperty.roundCorner) && this.canvasProperty.roundCorner > 0) {
            image = makeRoundedCorner(image, this.canvasProperty.roundCorner);

        }
        combinedImage = image;
        return this;
    }

    @Override
    public InputStream getCombinedImageStream() throws ImageBuildException {
        if (combinedImage != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            // 获取 ImageWriter 对象
            ImageWriter writer = ImageIO.getImageWritersBySuffix(canvasProperty.outputFormat.getName()).next();
            // 创建 ImageWriteParam 对象，并设置压缩质量
            ImageWriteParam param = writer.getDefaultWriteParam();
            if (param.canWriteCompressed()
                    && canvasProperty.outputFormat != OutputFormat.BMP
            ) {
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(canvasProperty.quality);
            }
            try {
                // 将 BufferedImage 写入到 ByteArrayOutputStream
                writer.setOutput(new MemoryCacheImageOutputStream(outputStream));
                writer.write(null, new javax.imageio.IIOImage(combinedImage, null, null), param);
                writer.dispose();
                return new ByteArrayInputStream(outputStream.toByteArray());
            } catch (IOException e) {
                throw new ImageBuildException("执行图片合成失败，无法输出文件流");
            }
        } else {
            throw new ImageBuildException("尚未执行图片合成，无法输出文件流");
        }
    }

    @Override
    public void save(String filePath) throws ImageBuildException, IOException {
        if (combinedImage != null) {
            ImageWriter writer = ImageIO.getImageWritersBySuffix(canvasProperty.outputFormat.getName()).next();
            ImageWriteParam param = writer.getDefaultWriteParam();
            if (param.canWriteCompressed()
                    && canvasProperty.outputFormat != OutputFormat.BMP
            ) {
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(canvasProperty.quality);
            }
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            writer.setOutput(new MemoryCacheImageOutputStream(os));
            writer.write(null, new IIOImage(combinedImage, null, null), param);
            writer.dispose();
            FileOutputStream file = new FileOutputStream(filePath);
            file.write(os.toByteArray());
            file.close();
            os.close();
        } else {
            throw new ImageBuildException("尚未执行图片合成，无法保存文件");
        }
    }
}
