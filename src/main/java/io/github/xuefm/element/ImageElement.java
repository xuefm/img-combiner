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

    /**
     * 构造方法
     *
     * @param imgUrl          图片地址
     * @param x               横向偏移
     * @param y               纵向偏移
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     * @param width           图片宽度
     * @param height          图片高度
     */
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

    /**
     * 构造方法
     *
     * @param x               横向偏移
     * @param y               纵向偏移
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     */
    private ImageElement(BufferedImage image, Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        super(x, y, transverseAlign, verticalAlign);
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    /**
     * 构造方法
     *
     * @param imgUrl          图片地址
     * @param x               横向偏移
     * @param y               纵向偏移
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     */
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
     * @param image           图片
     * @param x               横向偏移
     * @param y               纵向偏移
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     * @return ImageElement
     */
    public static ImageElement of(BufferedImage image, Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        return new ImageElement(image, x, y, transverseAlign, verticalAlign);
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
     * @param image           图片
     * @param x               横向偏移
     * @param y               纵向偏移
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

    /**
     * 设置横向对齐方式
     *
     * @param transverseAlign 横向对齐方式
     * @return ImageElement
     */
    public ImageElement setTransverseAlign(AlignType.TransverseAlign transverseAlign) {
        super.transverseAlign = transverseAlign;
        return this;
    }

    /**
     * 设置纵向对齐方式
     *
     * @param verticalAlign 纵向对齐方式
     * @return ImageElement
     */
    public ImageElement setVerticalAlign(AlignType.VerticalAlign verticalAlign) {
        super.verticalAlign = verticalAlign;
        return this;
    }

    /**
     * 设置横向偏移
     *
     * @param x 横向偏移
     * @return ImageElement
     */
    public ImageElement setX(int x) {
        super.x = x;
        return this;
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
     * 设置纵向偏移
     *
     * @param y 纵向偏移
     * @return ImageElement
     */
    public ImageElement setY(int y) {
        super.y = y;
        return this;
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
     * 设置旋转角度(默认按元素中心旋转)
     *
     * @param rotate 旋转角度
     * @return ImageElement
     */
    public ImageElement setRotate(@NonNull Integer rotate) {
        super.rotate = rotate;
        return this;
    }

    /**
     * 设置实际坐标
     *
     * @param actualX 实际x坐标
     * @param actualY 实际y坐标
     */
    public void setActualXAndY(Integer actualX, Integer actualY) {
        super.actualX = actualX;
        super.actualY = actualY;
    }

    /**
     * 设置宽和高
     *
     * @param width  宽度
     * @param height 高度
     * @return ImageElement
     */
    public ImageElement setWidthAndHeight(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }


}
