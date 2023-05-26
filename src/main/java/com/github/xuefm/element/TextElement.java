package com.github.xuefm.element;

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

    //基础属性
    private String text;                //文本
    @Setter
    private Font font = new Font(null, Font.BOLD, 32);                  //字体
    @Setter
    private Float space;                //字间距
    private boolean strikeThrough;      //删除线
    @Setter
    private Color color = new Color(0, 0, 0);   //颜色，默认黑色
    @Setter
    private Integer rotate;             //旋转


    private TextElement(String text, int x, int y) {
        super(x, y);
        this.text = text;
    }


    public static TextElement of(String text, int x, int y) {
        return new TextElement(text, x, y);
    }


}
