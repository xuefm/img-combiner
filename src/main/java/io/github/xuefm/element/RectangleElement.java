package io.github.xuefm.element;

import io.github.xuefm.enums.RectangleType;
import lombok.Getter;
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
    private RectangleType rectangleType = RectangleType.FillRect;

    /**
     * 圆角大小 当为圆角矩形时生效
     */
    @Setter
    private Integer roundCorner = 0;

    private RectangleElement(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    private RectangleElement(int x, int y, int width, int height, RectangleType rectangleType) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.rectangleType = rectangleType;
    }

    public static RectangleElement of(int x, int y, int width, int height, RectangleType rectangleType) {
        return new RectangleElement(x, y, width, height, rectangleType);
    }

    public static RectangleElement of(int x, int y, int width, int height) {
        return new RectangleElement(x, y, width, height);
    }
}
