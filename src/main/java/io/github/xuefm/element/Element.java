package io.github.xuefm.element;


import lombok.Getter;
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
    protected Float alpha;


    protected Element(int x, int y) {
        this.x = x;
        this.y = y;
    }


}
