package io.github.xuefm.element;

import io.github.xuefm.enums.AlignType;
import io.github.xuefm.enums.LineWrapType;
import lombok.Getter;
import lombok.NonNull;
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

    /**
     * 自动换行类型 默认不自动换行
     */
    private LineWrapType lineWrapType = LineWrapType.NO_LINE_BREAKS;

    /**
     * 每行最大容量
     */
    private int lineMax;


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

    public void setActualXAndY(Integer actualX, Integer actualY) {
        super.actualX = actualX;
        super.actualY = actualY;
    }

    /**
     * 默认按元素中心旋转
     *
     * @param rotate
     */
    public TextElement setRotate(@NonNull Integer rotate) {
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
    public TextElement setRotate(@NonNull Integer rotate, @NonNull Integer actualRotateX, @NonNull Integer actualRotateY) {
        this.rotate = rotate;
        this.actualRotateX = actualRotateX;
        this.actualRotateY = actualRotateY;
        return this;
    }

    private TextElement(String text, int x, int y) {
        super(x, y);
        this.text = text;
    }


    public static TextElement of(String text, int x, int y) {
        return new TextElement(text, x, y);
    }

    public TextElement setLineFeed(LineWrapType lineWrapType, int lineMax) {
        this.lineWrapType = lineWrapType;
        this.lineMax = lineMax;
        return this;
    }


}
