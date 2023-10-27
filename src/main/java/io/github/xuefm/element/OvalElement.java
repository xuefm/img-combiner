package io.github.xuefm.element;

import io.github.xuefm.enums.AlignType;
import io.github.xuefm.enums.OvalType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.awt.*;

@Getter
@Accessors(chain = true)
public class OvalElement extends Element {

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
    private OvalType ovalType = OvalType.DrawOval;

    /**
     * 圆角大小 当为圆角矩形时生效
     */
    @Setter
    private Integer roundCorner = 0;

    private OvalElement(Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        super(x, y, transverseAlign, verticalAlign);
    }

    public static OvalElement of(Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign, int width, int height) {
        return new OvalElement(x, y, transverseAlign, verticalAlign)
                .setWidthAndHeight(width, height);
    }

    public OvalElement setX(int x) {
        super.x = x;
        return this;
    }

    public OvalElement setY(int y) {
        super.y = y;
        return this;
    }

    public OvalElement setTransverseAlign(AlignType.TransverseAlign transverseAlign) {
        super.transverseAlign = transverseAlign;
        return this;
    }

    public OvalElement setVerticalAlign(AlignType.VerticalAlign verticalAlign) {
        super.verticalAlign = verticalAlign;
        return this;
    }

    public OvalElement setAlpha(float alpha) {
        super.alpha = alpha;
        return this;
    }

    /**
     * 默认按元素中心旋转
     *
     * @param rotate 旋转角度
     * @return OvalElement
     */
    public OvalElement setRotate(@NonNull Integer rotate) {
        super.rotate = rotate;
        return this;
    }

    /**
     * 设置旋转并设置实际旋转x和y坐标
     *
     * @param rotate        旋转角度
     * @param actualRotateX 实际旋转x坐标
     * @param actualRotateY 实际旋转y坐标
     * @return OvalElement
     */
    public OvalElement setRotate(@NonNull Integer rotate, @NonNull Integer actualRotateX, @NonNull Integer actualRotateY) {
        this.rotate = rotate;
        this.actualRotateX = actualRotateX;
        this.actualRotateY = actualRotateY;
        return this;
    }

    public void setActualXAndY(Integer actualX, Integer actualY) {
        super.actualX = actualX;
        super.actualY = actualY;
    }

    /**
     * 设置宽和高
     *
     * @param width  宽
     * @param height 高
     * @return OvalElement
     */
    public OvalElement setWidthAndHeight(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }
}
