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
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ImageCombiner的默认实现
 */
public class DefaultImageCombiner extends AbstractImageCombiner {


    private DefaultImageCombiner(List<Element> elementList, int canvasWidth, int canvasHeight, OutputFormat outputFormat, Integer roundCorner, Float quality) {
        super(elementList, canvasWidth, canvasHeight, outputFormat, roundCorner, quality);
    }

    public static ImageCombiner of(int canvasWidth, int canvasHeight, OutputFormat outputFormat, Integer roundCorner, Float quality) {
        return new DefaultImageCombiner(new ArrayList<>(), canvasWidth, canvasHeight, outputFormat, roundCorner, quality);
    }

    @Override
    public ImageCombiner generate() {

        // 创建空白图片，并指定为支持透明度的类型
        BufferedImage image = new BufferedImage(this.canvasProperty.canvasWidth, this.canvasProperty.canvasHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = image.createGraphics();
        // 设置抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 在图片上绘制背景色
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());

        for (Element element : this.elementList) {
            IPainter instance = ImageCombinerConfig.getInstance(element);
            instance.draw(g2d, element, canvasProperty);
        }
        g2d.dispose();
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
            if (param.canWriteCompressed()) {
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
            if (param.canWriteCompressed()) {
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
