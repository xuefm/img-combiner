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
    private final int x1;

    /**
     * 结束坐标y，相对左上角
     */
    private final int y1;


    /**
     * 结束坐标x，相对左上角
     */
    private final int x2;

    /**
     * 结束坐标y，相对左上角
     */
    private final int y2;

    /**
     * 颜色，默认黑色
     */
    @Setter
    private Color color = new Color(0, 0, 0);


    /**
     * 实际开始坐标x，相对左上角(计算值，请勿手动设置)
     */
    private int actualX1;

    /**
     * 实际开始坐标y，相对左上角(计算值，请勿手动设置)
     */
    private int actualY1;


    /**
     * 实际结束坐标x，相对左上角(计算值，请勿手动设置)
     */
    private int actualX2;

    /**
     * 实际结束坐标y，相对左上角(计算值，请勿手动设置)
     */
    private int actualY2;

    /**
     * 绘制宽度(计算值，请勿手动设置)
     */
    private Integer width;

    /**
     * 绘制高度(计算值，请勿手动设置)
     */
    private Integer height;


    /**
     * 设置横向对齐方式
     *
     * @param transverseAlign 横向对齐方式
     * @return LineElement
     */
    public LineElement setTransverseAlign(AlignType.TransverseAlign transverseAlign) {
        super.transverseAlign = transverseAlign;
        return this;
    }

    /**
     * 设置纵向对齐方式
     *
     * @param verticalAlign 纵向对齐方式
     * @return LineElement
     */
    public LineElement setVerticalAlign(AlignType.VerticalAlign verticalAlign) {
        super.verticalAlign = verticalAlign;
        return this;
    }


    /**
     * 设置透明度
     *
     * @param alpha 透明度，取值范围为0~1，值越小越透明
     * @return LineElement
     */
    public LineElement setAlpha(float alpha) {
        super.alpha = alpha;
        return this;
    }

    /**
     * 设置横向偏移
     *
     * @param x 横向偏移
     * @return LineElement
     */
    public LineElement setX(int x) {
        super.x = x;
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

    /**
     * 设置纵向偏移
     *
     * @param y 纵向偏移
     * @return LineElement
     */
    public LineElement setY(int y) {
        super.y = y;
        return this;
    }

    /**
     * 设置旋转角度(默认按元素中心旋转)
     *
     * @param rotate 旋转角度
     * @return LineElement
     */
    public LineElement setRotate(@NonNull Integer rotate) {
        super.rotate = rotate;
        return this;
    }




    /**
     * 设置计算值
     *
     * @param actualX1 实际x1坐标
     * @param actualY1 实际y1坐标
     * @param actualX2 实际x2坐标
     * @param actualY2 实际y2坐标
     * @param width    宽度
     * @param height   高度
     * @return LineElement
     */
    public LineElement setCalculatedValue(int actualX1, int actualY1, int actualX2, int actualY2, int width, int height) {
        this.actualX1 = actualX1;
        this.actualY1 = actualY1;
        this.actualX2 = actualX2;
        this.actualY2 = actualY2;
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * 构造方法
     *
     * @param x               横向偏移
     * @param y               纵向偏移
     * @param x1              起始x坐标(只决定线的趋势,位置由x,y,transverseAlign,verticalAlign决定)
     * @param y1              起始y坐标(只决定线的趋势,位置由x,y,transverseAlign,verticalAlign决定)
     * @param x2              结束x坐标(只决定线的趋势,位置由x,y,transverseAlign,verticalAlign决定)
     * @param y2              结束y坐标(只决定线的趋势,位置由x,y,transverseAlign,verticalAlign决定)
     * @param transverseAlign 横向对齐方式
     * @param verticalAlign   纵向对齐方式
     */
    private LineElement(Integer x, Integer y, int x1, int y1, int x2, int y2, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        super(x, y, transverseAlign, verticalAlign);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }


    /**
     *
     * @param x1 起始x坐标
     * @param y1 起始y坐标
     * @param x2 结束x坐标
     * @param y2 结束y坐标
     * @return LineElement
     */
    public static LineElement of(int x1, int y1, int x2, int y2) {
        return new LineElement(null, null, x1, y1, x2, y2, null, null);
    }

    /**
     * @param x1              起始x坐标
     * @param y1              起始y坐标
     * @param x2              结束x坐标
     * @param y2              结束y坐标
     * @param transverseAlign 横向对齐方式(默认左对齐)
     * @param verticalAlign   纵向对齐方式(默认顶部对齐)
     * @return LineElement
     */
    public static LineElement of(int x1, int y1, int x2, int y2, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        return new LineElement(null, null, x1, y1, x2, y2, transverseAlign, verticalAlign);
    }

    /**
     * @param x               横向偏移
     * @param y               纵向偏移
     * @param x1              起始x坐标(只决定线的趋势,位置由x,y,transverseAlign,verticalAlign决定)
     * @param y1              起始y坐标(只决定线的趋势,位置由x,y,transverseAlign,verticalAlign决定)
     * @param x2              结束x坐标(只决定线的趋势,位置由x,y,transverseAlign,verticalAlign决定)
     * @param y2              结束y坐标(只决定线的趋势,位置由x,y,transverseAlign,verticalAlign决定)
     * @param transverseAlign 横向对齐方式(默认左对齐)
     * @param verticalAlign   纵向对齐方式(默认顶部对齐)
     * @return LineElement
     */
    public static LineElement of(int x, int y, int x1, int y1, int x2, int y2, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        return new LineElement(x, y, x1, y1, x2, y2, transverseAlign, verticalAlign);
    }


}
