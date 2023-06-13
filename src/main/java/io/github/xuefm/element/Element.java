package io.github.xuefm.element;


import io.github.xuefm.enums.AlignType;
import lombok.Getter;


/**
 * 抽象元素
 * 为了能使用链式调用该类不提供set方法，由子类提供
 */
@Getter
public abstract class Element {

    /**
     * 起始坐标x，相对左上角
     */
    protected int x;

    /**
     * 起始坐标y，相对左上角
     */
    protected int y;

    /**
     * 横向对齐方式(默认左对齐)
     */
    protected AlignType.TransverseAlign transverseAlign = AlignType.TransverseAlign.LEFT;

    /**
     * 纵向对齐方式(默认顶部对齐)
     */
    protected AlignType.VerticalAlign verticalAlign = AlignType.VerticalAlign.TOP;

    /**
     * 透明度，取值范围为0~1，值越小越透明
     */
    protected Float alpha = 1f;

    /**
     * 旋转角度
     */
    protected Integer rotate;


    /**
     * 实际x坐标
     */
    protected Integer actualX;

    /**
     * 实际y坐标
     */
    protected Integer actualY;


    /**
     * 实际旋转x坐标
     */
    protected Integer actualRotateX;

    /**
     * 实际旋转y坐标
     */
    protected Integer actualRotateY;


    protected Element(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected Element(AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        this.transverseAlign = transverseAlign;
        this.verticalAlign = verticalAlign;
    }

    protected Element(AlignType.TransverseAlign transverseAlign, int y) {
        this.transverseAlign = transverseAlign;
        this.y = y;

    }

    protected Element(int x, AlignType.VerticalAlign verticalAlign) {
        this.x = x;
        this.verticalAlign = verticalAlign;
    }


}
