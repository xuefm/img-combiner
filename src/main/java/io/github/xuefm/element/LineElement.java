package io.github.xuefm.element;

import io.github.xuefm.enums.AlignType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.awt.*;

/**
 * 线形元素
 */
@Getter
@Accessors(chain = true)
public class LineElement extends Element {


    /**
     * 结束坐标x，相对左上角
     */
    protected int x2;

    /**
     * 结束坐标y，相对左上角
     */
    protected int y2;

    /**
     * 颜色，默认黑色
     */
    @Setter
    private Color color = new Color(0, 0, 0);


    public LineElement setTransverseAlign(AlignType.TransverseAlign transverseAlign) {
        super.transverseAlign = transverseAlign;
        return this;
    }

    public LineElement setVerticalAlign(AlignType.VerticalAlign verticalAlign) {
        super.verticalAlign = verticalAlign;
        return this;
    }

    public LineElement setAlpha(float alpha) {
        super.alpha = alpha;
        return this;
    }

    public LineElement setActualXAndY(Integer actualX, Integer actualY) {
        super.actualX = actualX;
        super.actualY = actualY;
        return this;
    }

    /**
     * 默认按元素中心旋转
     *
     * @param rotate 旋转角度
     * @return LineElement
     */
    public LineElement setRotate(@NonNull Integer rotate) {
        super.rotate = rotate;
        return this;
    }

    /**
     * 设置旋转并设置实际旋转x和y坐标
     *
     * @param rotate        旋转角度
     * @param actualRotateX 实际旋转x坐标
     * @param actualRotateY 实际旋转y坐标
     * @return LineElement
     */
    public LineElement setRotate(@NonNull Integer rotate, @NonNull Integer actualRotateX, @NonNull Integer actualRotateY) {
        this.rotate = rotate;
        this.actualRotateX = actualRotateX;
        this.actualRotateY = actualRotateY;
        return this;
    }


    private LineElement(int x, int y, int x2, int y2) {
        super(x, y);
        this.x2 = x2;
        this.y2 = y2;
    }


    public static LineElement of(int x, int y, int x2, int y2) {
        return new LineElement(x, y, x2, y2);
    }


}
