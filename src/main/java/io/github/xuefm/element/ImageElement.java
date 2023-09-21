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
    private Integer width;

    /**
     * 绘制高度
     */
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

    public ImageElement setX(int x) {
        super.x = x;
        return this;
    }

    public ImageElement setY(int y) {
        super.y = y;
        return this;
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

    private ImageElement(String imgUrl, Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign, Integer width, Integer height) {
        super(x, y, transverseAlign, verticalAlign);
        this.imgUrl = imgUrl;
        this.width = width;
        this.height = height;
        try {
            image = ImageIO.read(new URL(imgUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private ImageElement(BufferedImage image, Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        super(x, y, transverseAlign, verticalAlign);
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    private ImageElement(String imgUrl, Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        super(x, y, transverseAlign, verticalAlign);
        this.imgUrl = imgUrl;
        try {
            this.image = ImageIO.read(new URL(imgUrl));
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param imgUrl          图片url
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     * @return ImageElement
     */
    public static ImageElement of(String imgUrl, Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        return new ImageElement(imgUrl, x, y, transverseAlign, verticalAlign);
    }

    /**
     * @param image           图片
     * @param x               x坐标
     * @param y               y坐标
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     * @return ImageElement
     */
    public static ImageElement of(BufferedImage image, Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        return new ImageElement(image, x, y, transverseAlign, verticalAlign);
    }

    /**
     * @param imgUrl          图片url
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     * @param width           指定宽度
     * @param height          指定高度
     * @return ImageElement
     */
    public static ImageElement of(String imgUrl, Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign, Integer width, Integer height) {
        return new ImageElement(imgUrl, x, y, transverseAlign, verticalAlign)
                .setWidthAndHeight(width, height);
    }

    /**
     * @param image           图片
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     * @param width           指定宽度
     * @param height          指定高度
     * @return ImageElement
     */
    public static ImageElement of(BufferedImage image, Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign, Integer width, Integer height) {
        return new ImageElement(image, x, y, transverseAlign, verticalAlign)
                .setWidthAndHeight(width, height);
    }

    public void setActualXAndY(Integer actualX, Integer actualY) {
        super.actualX = actualX;
        super.actualY = actualY;
    }

    /**
     * 设置宽和高
     *
     * @param width
     * @param height
     * @return
     */
    public ImageElement setWidthAndHeight(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }


}
