package io.github.xuefm.element;

import io.github.xuefm.enums.AlignType;
import io.github.xuefm.enums.LineWrapType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.awt.*;
import java.text.AttributedString;
import java.util.List;

/**
 * 文本元素
 */
@Getter
@Accessors(chain = true)
public class TextElement extends Element {

    /**
     * 文本
     */
    private final String text;

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
     * 自动换行类型 默认不自动换行
     */
    private LineWrapType lineWrapType = LineWrapType.NO_LINE_BREAKS;

    /**
     * 每行最大容量(自动换行)
     */
    private int lineMax;


    /**
     * 是否使用下划线(默认不使用)
     */
    @Setter
    private boolean underline;

    /**
     * 是否使用删除线(默认不使用)
     */
    @Setter
    private boolean strikethrough;

    /**
     * 行高(计算值，请勿手动设置)
     */
    private Integer textHeight;

    /**
     * 绘制宽度(计算值，请勿手动设置)
     */
    private Integer width;

    /**
     * 绘制高度(计算值，请勿手动设置)
     */
    private Integer height;

    /**
     * 换行处理后的textList(计算值，请勿手动设置)
     */
    private List<String> textList;
    /**
     * 带属性(字体、下划线、删除线等属性)字符串列表(计算值，请勿手动设置)
     */
    private List<AttributedString> attributedStringList;

    private TextElement(String text, Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        super(x, y, transverseAlign, verticalAlign);
        this.text = text;
    }

    /**
     * @param text 文本
     * @param x    x坐标
     * @param y    y坐标
     * @return TextElement
     */
    public static TextElement of(String text, Integer x, Integer y, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        return new TextElement(text, x, y, transverseAlign, verticalAlign);
    }


    public TextElement setTransverseAlign(AlignType.TransverseAlign transverseAlign) {
        super.transverseAlign = transverseAlign;
        return this;
    }

    public TextElement setVerticalAlign(AlignType.VerticalAlign verticalAlign) {
        super.verticalAlign = verticalAlign;
        return this;
    }

    public TextElement setX(int x) {
        super.x = x;
        return this;
    }

    public void setTextList(List<String> textList) {
        this.textList = textList;
    }

    public void setAttributedStringList(List<AttributedString> attributedStringList) {
        this.attributedStringList = attributedStringList;
    }

    public void setTextHeight(int textHeight) {
        this.textHeight = textHeight;
    }


    public void setActualXAndY(int actualX, int actualY) {
        super.actualX = actualX;
        super.actualY = actualY;
    }

    public TextElement setY(int y) {
        super.y = y;
        return this;
    }

    /**
     * 设置透明度
     *
     * @param alpha 透明度，取值范围为0~1，值越小越透明
     * @return TextElement
     */
    public TextElement setAlpha(float alpha) {
        super.alpha = alpha;
        return this;
    }

    /**
     * 设置旋转并设置实际旋转x和y坐标
     *
     * @param rotate        旋转角度
     * @param actualRotateX 实际旋转x坐标
     * @param actualRotateY 实际旋转y坐标
     * @return TextElement
     */
    public TextElement setRotate(int rotate, int actualRotateX, int actualRotateY) {
        this.rotate = rotate;
        this.actualRotateX = actualRotateX;
        this.actualRotateY = actualRotateY;
        return this;
    }

    /**
     * 设置自动换行
     *
     * @param lineWrapType 自动换行类型
     * @param lineMax      每行最多容纳多少
     * @return TextElement
     */
    public TextElement setLineFeed(LineWrapType lineWrapType, int lineMax) {
        this.lineWrapType = lineWrapType;
        this.lineMax = lineMax;
        return this;
    }

    /**
     * 设置宽和高
     *
     * @param width  宽
     * @param height 高
     */
    public void setWidthAndHeight(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * 设置旋转 默认按元素中心旋转
     *
     * @param rotate 旋转角度
     * @return TextElement
     */
    public TextElement setRotate(int rotate) {
        super.rotate = rotate;
        return this;
    }


}
