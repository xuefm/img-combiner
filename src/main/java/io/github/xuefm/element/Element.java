package io.github.xuefm.element;


import io.github.xuefm.enums.AlignType;
import lombok.Getter;

import java.util.Objects;

/**
 * 抽象元素
 * 为了能使用链式调用该类不提供set方法，由子类提供
 */
@Getter
public abstract class Element {

    /**
     * 横向偏移
     */
    protected int x = 0;

    /**
     * 纵向偏移
     */
    protected int y = 0;

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

    protected Element(Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        if (Objects.nonNull(x))
            this.x = x;
        if (Objects.nonNull(y))
            this.y = y;
        if (Objects.nonNull(transverseAlign))
            this.transverseAlign = transverseAlign;
        if (Objects.nonNull(verticalAlign))
            this.verticalAlign = verticalAlign;
    }


}
