package io.github.xuefm.element;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.awt.*;

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

    private Color color = new Color(0, 0, 0);   //颜色，默认黑色


    private LineElement(int x, int y, int x2, int y2) {
        super(x, y);
        this.x2 = x2;
        this.y2 = y2;
    }


    public static LineElement of(int x, int y, int x2, int y2) {
        return new LineElement(x, y, x2, y2);
    }


}
