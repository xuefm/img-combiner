package io.github.xuefm.element;

import io.github.xuefm.enums.AlignType;
import io.github.xuefm.enums.LineWrapType;
import io.github.xuefm.exception.ImageBuildException;
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
     * 渐变颜色数组 (如:{Color.RED, Color.GREEN, Color.BLUE, Color.RED, Color.GREEN, Color.BLUE, Color.RED, Color.GREEN, Color.BLUE})
     */
    Color[] colors;
    /**
     * 渐变比率数组  (如:{0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 1.0f})
     */
    float[] fractions;

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

    public TextElement setY(int y) {
        super.y = y;
        return this;
    }

    public void setCalculatedValue(List<String> textList, int width, int height) {
        this.textList = textList;
        this.width = width;
        this.height = height;
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
     * 设置旋转 默认按元素中心旋转
     *
     * @param rotate 旋转角度
     * @return TextElement
     */
    public TextElement setRotate(int rotate) {
        super.rotate = rotate;
        return this;
    }

    /**
     * 设置渐变
     *
     * @param colors    渐变颜色数组
     * @param fractions 渐变比率数组
     * @return TextElement
     */
    public TextElement setGradient(Color[] colors, float[] fractions) {
        if (colors.length != fractions.length) {
            throw new ImageBuildException("Colors[]和float[]长度必须相同");
        }
        this.colors = colors;
        this.fractions = fractions;
        return this;
    }


}
