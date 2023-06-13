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

    public ImageElement setAlpha(float alpha) {
        super.alpha = alpha;
        return this;
    }

    public void setActualXAndY(Integer actualX, Integer actualY) {
        super.actualX = actualX;
        super.actualY = actualY;
    }

    /**
     * 默认按元素中心旋转
     *
     * @param rotate
     */
    public ImageElement setRotate(@NonNull Integer rotate) {
        super.rotate = rotate;
        return this;
    }

    /**
     * 设置旋转并设置实际旋转x和y坐标
     *
     * @param rotate
     * @param actualRotateX
     * @param actualRotateY
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

    private ImageElement(BufferedImage image, int x, int y) {
        super(x, y);
        this.image = image;
    }


    public static ImageElement of(String imgUrl, int x, int y) {
        return new ImageElement(imgUrl, x, y);
    }

    public static ImageElement of(String imgUrl, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        return new ImageElement(imgUrl, transverseAlign, verticalAlign);
    }

    public static ImageElement of(String imgUrl, int x, AlignType.VerticalAlign verticalAlign) {
        return new ImageElement(imgUrl, x, verticalAlign);
    }

    public static ImageElement of(String imgUrl, AlignType.TransverseAlign transverseAlign, int y) {
        return new ImageElement(imgUrl, transverseAlign, y);
    }

    public static ImageElement of(String imgUrl, int x, int y, Integer width, Integer height) {
        return new ImageElement(imgUrl, x, y, width, height);
    }

    public static ImageElement of(String imgUrl, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign, Integer width, Integer height) {
        return new ImageElement(imgUrl, transverseAlign, verticalAlign, width, height);
    }

    public static ImageElement of(String imgUrl, AlignType.TransverseAlign transverseAlign, int y, Integer width, Integer height) {
        return new ImageElement(imgUrl, transverseAlign, y, width, height);
    }

    public static ImageElement of(String imgUrl, int x, AlignType.VerticalAlign verticalAlign, Integer width, Integer height) {
        return new ImageElement(imgUrl, x, verticalAlign, width, height);
    }

    public static ImageElement of(BufferedImage image, int x, int y) {
        return new ImageElement(image, x, y);
    }


}
