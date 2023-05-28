package io.github.xuefm.element;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.awt.*;

/**
 * 图片元素
 */
@Getter
@Accessors(chain = true)
public class TextElement extends Element {

    /**
     * 文本
     */
    private String text;

    /**
     * 字体
     */
    @Setter
    private Font font = new Font(null, Font.BOLD, 32);

    /**
     * 颜色，默认黑色
     */
    @Setter
    private Color color = new Color(0, 0, 0);

    /**
     * 旋转
     */
    @Setter
    private Integer rotate;


    private TextElement(String text, int x, int y) {
        super(x, y);
        this.text = text;
    }


    public static TextElement of(String text, int x, int y) {
        return new TextElement(text, x, y);
    }


}
