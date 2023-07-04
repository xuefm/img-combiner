package io.github.xuefm.element;

import io.github.xuefm.enums.AlignType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * 图片元素
 */
@Getter
@Accessors(chain = true)
public class ImageElement extends Element {

    /**
     * 图片对象
     */
    private BufferedImage image;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 绘制宽度
     */
    @Setter
    private Integer width;

    /**
     * 绘制高度
     */
    @Setter
    private Integer height;

    /**
     * 圆角大小
     */
    @Setter
    private Integer roundCorner;

    public ImageElement setTransverseAlign(AlignType.TransverseAlign transverseAlign) {
        super.transverseAlign = transverseAlign;
        return this;
    }

    public ImageElement setVerticalAlign(AlignType.VerticalAlign verticalAlign) {
        super.verticalAlign = verticalAlign;
        return this;
    }

    private ImageElement(BufferedImage image, int x, int y) {
        super(x, y);
        this.image = image;
    }

    public void setActualXAndY(Integer actualX, Integer actualY) {
        super.actualX = actualX;
        super.actualY = actualY;
    }

    /**
     * 默认按元素中心旋转
     *
     * @param rotate 旋转角度
     * @return ImageElement
     */
    public ImageElement setRotate(@NonNull Integer rotate) {
        super.rotate = rotate;
        return this;
    }

    /**
     * 设置旋转并设置实际旋转x和y坐标
     *
     * @param rotate        旋转角度
     * @param actualRotateX 实际旋转x坐标
     * @param actualRotateY 实际旋转y坐标
     * @return ImageElement
     */
    public ImageElement setRotate(@NonNull Integer rotate, @NonNull Integer actualRotateX, @NonNull Integer actualRotateY) {
        this.rotate = rotate;
        this.actualRotateX = actualRotateX;
        this.actualRotateY = actualRotateY;
        return this;
    }


    private ImageElement(String imgUrl, int x, int y) {
        super(x, y);
        this.imgUrl = imgUrl;
        try {
            image = ImageIO.read(new URL(imgUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ImageElement(String imgUrl, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        super(transverseAlign, verticalAlign);
        this.imgUrl = imgUrl;
        try {
            image = ImageIO.read(new URL(imgUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ImageElement(String imgUrl, int x, AlignType.VerticalAlign verticalAlign) {
        super(x, verticalAlign);
        this.imgUrl = imgUrl;
        try {
            image = ImageIO.read(new URL(imgUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ImageElement(String imgUrl, AlignType.TransverseAlign transverseAlign, int y) {
        super(transverseAlign, y);
        this.imgUrl = imgUrl;
        try {
            image = ImageIO.read(new URL(imgUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ImageElement(String imgUrl, int x, int y, Integer width, Integer height) {
        super(x, y);
        this.imgUrl = imgUrl;
        this.width = width;
        this.height = height;
        try {
            image = ImageIO.read(new URL(imgUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ImageElement(String imgUrl, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign, Integer width, Integer height) {
        super(transverseAlign, verticalAlign);
        this.imgUrl = imgUrl;
        this.width = width;
        this.height = height;
        try {
            image = ImageIO.read(new URL(imgUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ImageElement(String imgUrl, AlignType.TransverseAlign transverseAlign, int y, Integer width, Integer height) {
        super(transverseAlign, y);
        this.imgUrl = imgUrl;
        this.width = width;
        this.height = height;
        try {
            image = ImageIO.read(new URL(imgUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ImageElement(String imgUrl, int x, AlignType.VerticalAlign verticalAlign, Integer width, Integer height) {
        super(x, verticalAlign);
        this.imgUrl = imgUrl;
        this.width = width;
        this.height = height;
        try {
            image = ImageIO.read(new URL(imgUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ImageElement(BufferedImage image, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        super(transverseAlign, verticalAlign);
        this.image = image;
    }

    private ImageElement(BufferedImage image, int x, AlignType.VerticalAlign verticalAlign) {
        super(x, verticalAlign);
        this.image = image;
    }

    private ImageElement(BufferedImage image, AlignType.TransverseAlign transverseAlign, int y) {
        super(transverseAlign, y);
        this.image = image;
    }

    private ImageElement(BufferedImage image, int x, int y, Integer width, Integer height) {
        super(x, y);
        this.image = image;
        this.width = width;
        this.height = height;
    }

    private ImageElement(BufferedImage image, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign, Integer width, Integer height) {
        super(transverseAlign, verticalAlign);
        this.image = image;
        this.width = width;
        this.height = height;
    }

    private ImageElement(BufferedImage image, AlignType.TransverseAlign transverseAlign, int y, Integer width, Integer height) {
        super(transverseAlign, y);
        this.image = image;
        this.width = width;
        this.height = height;
    }

    private ImageElement(BufferedImage image, int x, AlignType.VerticalAlign verticalAlign, Integer width, Integer height) {
        super(x, verticalAlign);
        this.image = image;
        this.width = width;
        this.height = height;
    }

    /**
     * @param imgUrl 图片url
     * @param x      x坐标
     * @param y      y坐标
     * @return ImageElement
     */
    public static ImageElement of(String imgUrl, int x, int y) {
        return new ImageElement(imgUrl, x, y);
    }

    /**
     * @param imgUrl          图片url
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     * @return ImageElement
     */
    public static ImageElement of(String imgUrl, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        return new ImageElement(imgUrl, transverseAlign, verticalAlign);
    }

    /**
     * @param imgUrl        图片url
     * @param x             x坐标
     * @param verticalAlign 纵向对齐方式
     * @return ImageElement
     */
    public static ImageElement of(String imgUrl, int x, AlignType.VerticalAlign verticalAlign) {
        return new ImageElement(imgUrl, x, verticalAlign);
    }

    /**
     * @param imgUrl          图片url
     * @param transverseAlign 横向对齐方式
     * @param y               y坐标
     * @return ImageElement
     */
    public static ImageElement of(String imgUrl, AlignType.TransverseAlign transverseAlign, int y) {
        return new ImageElement(imgUrl, transverseAlign, y);
    }

    /**
     * @param imgUrl 图片url
     * @param x      x坐标
     * @param y      y坐标
     * @param width  指定宽度
     * @param height 指定高度
     * @return ImageElement
     */
    public static ImageElement of(String imgUrl, int x, int y, Integer width, Integer height) {
        return new ImageElement(imgUrl, x, y, width, height);
    }

    /**
     * @param imgUrl          图片url
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     * @param width           指定宽度
     * @param height          指定高度
     * @return ImageElement
     */
    public static ImageElement of(String imgUrl, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign, Integer width, Integer height) {
        return new ImageElement(imgUrl, transverseAlign, verticalAlign, width, height);
    }

    /**
     * @param imgUrl          图片url
     * @param transverseAlign 横向对齐方式
     * @param y               y坐标
     * @param width           指定宽度
     * @param height          指定高度
     * @return ImageElement
     */
    public static ImageElement of(String imgUrl, AlignType.TransverseAlign transverseAlign, int y, Integer width, Integer height) {
        return new ImageElement(imgUrl, transverseAlign, y, width, height);
    }

    /**
     * @param imgUrl        图片url
     * @param x             x坐标
     * @param verticalAlign 纵向对齐方式
     * @param width         指定宽度
     * @param height        指定高度
     * @return ImageElement
     */
    public static ImageElement of(String imgUrl, int x, AlignType.VerticalAlign verticalAlign, Integer width, Integer height) {
        return new ImageElement(imgUrl, x, verticalAlign, width, height);
    }

    /**
     * @param image BufferedImage
     * @param x     x坐标
     * @param y     y坐标
     * @return ImageElement
     */
    public static ImageElement of(BufferedImage image, int x, int y) {
        return new ImageElement(image, x, y);
    }

    /**
     * @param image           BufferedImage
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     * @return ImageElement
     */
    public static ImageElement of(BufferedImage image, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        return new ImageElement(image, transverseAlign, verticalAlign);
    }

    /**
     * @param image         BufferedImage
     * @param x             x坐标
     * @param verticalAlign 纵向对齐方式
     * @return ImageElement
     */
    public static ImageElement of(BufferedImage image, int x, AlignType.VerticalAlign verticalAlign) {
        return new ImageElement(image, x, verticalAlign);
    }

    /**
     * @param image           BufferedImage
     * @param transverseAlign 横向对齐方式
     * @param y               y坐标
     * @return ImageElement
     */
    public static ImageElement of(BufferedImage image, AlignType.TransverseAlign transverseAlign, int y) {
        return new ImageElement(image, transverseAlign, y);
    }

    /**
     * @param image  BufferedImage
     * @param x      x坐标
     * @param y      y坐标
     * @param width  指定宽度
     * @param height 指定高度
     * @return ImageElement
     */
    public static ImageElement of(BufferedImage image, int x, int y, Integer width, Integer height) {
        return new ImageElement(image, x, y, width, height);
    }

    /**
     * @param image           BufferedImage
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     * @param width           指定宽度
     * @param height          指定高度
     * @return ImageElement
     */
    public static ImageElement of(BufferedImage image, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign, Integer width, Integer height) {
        return new ImageElement(image, transverseAlign, verticalAlign, width, height);
    }

    /**
     * @param image           BufferedImage
     * @param transverseAlign 横向对齐方式
     * @param y               y坐标
     * @param width           指定宽度
     * @param height          指定高度
     * @return ImageElement
     */
    public static ImageElement of(BufferedImage image, AlignType.TransverseAlign transverseAlign, int y, Integer width, Integer height) {
        return new ImageElement(image, transverseAlign, y, width, height);
    }

    /**
     * @param image         BufferedImage
     * @param x             x坐标
     * @param verticalAlign 纵向对齐方式
     * @param width         指定宽度
     * @param height        指定高度
     * @return ImageElement
     */
    public static ImageElement of(BufferedImage image, int x, AlignType.VerticalAlign verticalAlign, Integer width, Integer height) {
        return new ImageElement(image, x, verticalAlign, width, height);
    }

    /**
     * 设置透明度
     *
     * @param alpha 透明度，取值范围为0~1，值越小越透明
     * @return ImageElement
     */
    public ImageElement setAlpha(float alpha) {
        super.alpha = alpha;
        return this;
    }


}
