package io.github.xuefm.element;

import io.github.xuefm.enums.AlignType;
import io.github.xuefm.enums.RectangleType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.awt.*;

/**
 * 矩形元素
 */

@Getter
@Accessors(chain = true)
public class RectangleElement extends Element {

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
     * 颜色，默认黑色
     */
    @Setter
    private Color color = new Color(0, 0, 0);

    /**
     * 默认矩形填充
     */
    @Setter
    @NonNull
    private RectangleType rectangleType = RectangleType.FillRect;

    /**
     * 圆角大小 当为圆角矩形时生效
     */
    @Setter
    private Integer roundCorner = 0;


    public RectangleElement setTransverseAlign(AlignType.TransverseAlign transverseAlign) {
        super.transverseAlign = transverseAlign;
        return this;
    }

    public RectangleElement setVerticalAlign(AlignType.VerticalAlign verticalAlign) {
        super.verticalAlign = verticalAlign;
        return this;
    }

    public RectangleElement setAlpha(float alpha) {
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
     * @param rotate 旋转角度
     * @return RectangleElement
     */
    public RectangleElement setRotate(@NonNull Integer rotate) {
        super.rotate = rotate;
        return this;
    }

    /**
     * 设置旋转并设置实际旋转x和y坐标
     *
     * @param rotate        旋转角度
     * @param actualRotateX 实际旋转x坐标
     * @param actualRotateY 实际旋转y坐标
     * @return RectangleElement
     */
    public RectangleElement setRotate(@NonNull Integer rotate, @NonNull Integer actualRotateX, @NonNull Integer actualRotateY) {
        this.rotate = rotate;
        this.actualRotateX = actualRotateX;
        this.actualRotateY = actualRotateY;
        return this;
    }


    private RectangleElement(AlignType.TransverseAlign transverseAlign, int y, int width, int height, RectangleType rectangleType) {
        super(transverseAlign, y);
        this.width = width;
        this.height = height;
        this.rectangleType = rectangleType;
    }

    private RectangleElement(int x, AlignType.VerticalAlign verticalAlign, int width, int height, RectangleType rectangleType) {
        super(x, verticalAlign);
        this.width = width;
        this.height = height;
        this.rectangleType = rectangleType;
    }

    private RectangleElement(AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign, int width, int height, RectangleType rectangleType) {
        super(transverseAlign, verticalAlign);
        this.width = width;
        this.height = height;
        this.rectangleType = rectangleType;
    }

    private RectangleElement(int x, int y, int width, int height, RectangleType rectangleType) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.rectangleType = rectangleType;
    }

    private RectangleElement(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    private RectangleElement(AlignType.TransverseAlign transverseAlign, int y, int width, int height) {
        super(transverseAlign, y);
        this.width = width;
        this.height = height;
    }

    private RectangleElement(int x, AlignType.VerticalAlign verticalAlign, int width, int height) {
        super(x, verticalAlign);
        this.width = width;
        this.height = height;
    }

    private RectangleElement(AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign, int width, int height) {
        super(transverseAlign, verticalAlign);
        this.width = width;
        this.height = height;
    }


    public static RectangleElement of(int x, int y, int width, int height) {
        return new RectangleElement(x, y, width, height);
    }

    public static RectangleElement of(AlignType.TransverseAlign transverseAlign, int y, int width, int height) {
        return new RectangleElement(transverseAlign, y, width, height);
    }

    public static RectangleElement of(int x, AlignType.VerticalAlign verticalAlign, int width, int height) {
        return new RectangleElement(x, verticalAlign, width, height);
    }

    public static RectangleElement of(AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign, int width, int height) {
        return new RectangleElement(transverseAlign, verticalAlign, width, height);
    }


    public static RectangleElement of(int x, int y, int width, int height, RectangleType rectangleType) {
        return new RectangleElement(x, y, width, height, rectangleType);
    }

    public static RectangleElement of(AlignType.TransverseAlign transverseAlign, int y, int width, int height, RectangleType rectangleType) {
        return new RectangleElement(transverseAlign, y, width, height, rectangleType);
    }

    public static RectangleElement of(int x, AlignType.VerticalAlign verticalAlign, int width, int height, RectangleType rectangleType) {
        return new RectangleElement(x, verticalAlign, width, height, rectangleType);
    }

    public static RectangleElement of(AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign, int width, int height, RectangleType rectangleType) {
        return new RectangleElement(transverseAlign, verticalAlign, width, height, rectangleType);
    }
}
