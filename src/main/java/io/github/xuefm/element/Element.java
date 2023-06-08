package io.github.xuefm.element;


import io.github.xuefm.enums.AlignType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Accessors(chain = true)
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
     * 透明度，取值范围为0~1，值越小越透明
     */
    @Setter
    @NonNull
    protected Float alpha = 1f;

    /**
     * 横向对齐方式(默认左对齐)
     */
    @Setter
    @NonNull
    protected AlignType.TransverseAlign transverseAlign = AlignType.TransverseAlign.LEFT;

    /**
     * 纵向对齐方式(默认顶部对齐)
     */
    @Setter
    @NonNull
    protected AlignType.VerticalAlign verticalAlign = AlignType.VerticalAlign.TOP;


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
