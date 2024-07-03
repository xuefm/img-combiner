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
    private Integer width;

    /**
     * 绘制高度
     */
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

    /**
     * 构造方法
     *
     * @param x               横向偏移
     * @param y               纵向偏移
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     */
    private RectangleElement(Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        super(x, y, transverseAlign, verticalAlign);
    }

    /**
     * @param x               横向偏移
     * @param y               纵向偏移
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     * @param width           指定宽度
     * @param height          指定高度
     * @return RectangleElement
     */
    public static RectangleElement of(Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign, int width, int height) {
        return new RectangleElement(x, y, transverseAlign, verticalAlign)
                .setWidthAndHeight(width, height);
    }

    /**
     * 设置横向偏移
     *
     * @param x 横向偏移
     * @return RectangleElement
     */
    public RectangleElement setX(int x) {
        super.x = x;
        return this;
    }

    /**
     * 设置纵向偏移
     *
     * @param y 纵向偏移
     * @return RectangleElement
     */
    public RectangleElement setY(int y) {
        super.y = y;
        return this;
    }

    /**
     * 设置横向对齐方式
     *
     * @param transverseAlign 横向对齐方式
     * @return RectangleElement
     */
    public RectangleElement setTransverseAlign(AlignType.TransverseAlign transverseAlign) {
        super.transverseAlign = transverseAlign;
        return this;
    }

    /**
     * 设置纵向对齐方式
     *
     * @param verticalAlign 纵向对齐方式
     * @return RectangleElement
     */
    public RectangleElement setVerticalAlign(AlignType.VerticalAlign verticalAlign) {
        super.verticalAlign = verticalAlign;
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

    /**
     * 设置设置透明度
     *
     * @param alpha 透明度，取值范围为0~1，值越小越透明
     * @return RectangleElement
     */
    public RectangleElement setAlpha(float alpha) {
        super.alpha = alpha;
        return this;
    }

    /**
     * 设置旋转(默认按元素中心旋转)
     *
     * @param rotate 旋转角度
     * @return RectangleElement
     */
    public RectangleElement setRotate(@NonNull Integer rotate) {
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
     * 设置宽度和高度
     *
     * @param width  宽度
     * @param height 高度
     * @return RectangleElement
     */
    public RectangleElement setWidthAndHeight(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }
}
