package io.github.xuefm.element;

import io.github.xuefm.enums.AlignType;
import io.github.xuefm.enums.LineWrapType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.awt.*;
import java.util.List;

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
     * 自动换行类型 默认不自动换行
     */
    private LineWrapType lineWrapType = LineWrapType.NO_LINE_BREAKS;

    /**
     * 每行最大容量
     */
    private int lineMax;

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


    private TextElement(String text, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        super(transverseAlign, verticalAlign);
        this.text = text;
    }

    private TextElement(String text, AlignType.TransverseAlign transverseAlign, int y) {
        super(transverseAlign, y);
        this.text = text;
    }

    private TextElement(String text, int x, AlignType.VerticalAlign verticalAlign) {
        super(x, verticalAlign);
        this.text = text;
    }


    public TextElement setTransverseAlign(AlignType.TransverseAlign transverseAlign) {
        super.transverseAlign = transverseAlign;
        return this;
    }

    public TextElement setVerticalAlign(AlignType.VerticalAlign verticalAlign) {
        super.verticalAlign = verticalAlign;
        return this;
    }

    public TextElement setAlpha(float alpha) {
        super.alpha = alpha;
        return this;
    }

    public static TextElement of(String text, AlignType.TransverseAlign transverseAlign, AlignType.VerticalAlign verticalAlign) {
        return new TextElement(text, transverseAlign, verticalAlign);
    }

    public static TextElement of(String text, AlignType.TransverseAlign transverseAlign, int y) {
        return new TextElement(text, transverseAlign, y);
    }

    public static TextElement of(String text, int x, AlignType.VerticalAlign verticalAlign) {
        return new TextElement(text, x, verticalAlign);
    }

    private TextElement(String text, int x, int y) {
        super(x, y);
        this.text = text;
    }

    public void setWidthAndHeight(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setTextList(List<String> textList) {
        this.textList = textList;
    }

    public void setTextHeight(int textHeight) {
        this.textHeight = textHeight;
    }


    public static TextElement of(String text, int x, int y) {
        return new TextElement(text, x, y);
    }

    public void setActualXAndY(int actualX, int actualY) {
        super.actualX = actualX;
        super.actualY = actualY;
    }

    /**
     * 默认按元素中心旋转
     *
     * @param rotate
     */
    public TextElement setRotate(int rotate) {
        super.rotate = rotate;
        return this;
    }

    /**
     * 设置旋转并设置实际旋转x和y坐标
     *
     * @param rotate
     * @param actualRotateX
     * @param actualRotateY
     */
    public TextElement setRotate(int rotate, int actualRotateX, int actualRotateY) {
        this.rotate = rotate;
        this.actualRotateX = actualRotateX;
        this.actualRotateY = actualRotateY;
        return this;
    }

    public TextElement setLineFeed(LineWrapType lineWrapType, int lineMax) {
        this.lineWrapType = lineWrapType;
        this.lineMax = lineMax;
        return this;
    }


}
