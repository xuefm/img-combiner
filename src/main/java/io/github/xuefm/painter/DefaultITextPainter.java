package io.github.xuefm.painter;

import io.github.xuefm.combiner.AbstractImageCombiner;
import io.github.xuefm.element.Element;
import io.github.xuefm.element.TextElement;
import io.github.xuefm.exception.ImageBuildException;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 默认文本画家
 */
public class DefaultITextPainter implements IPainter {

    /**
     * 按文本最大长度将目标String拆分为List<String>
     *
     * @param str
     * @param textLengthMax
     * @return
     */
    public static List<String> splitByTextCount(String str, int textLengthMax) {
        //计算数组长度，向上取整
        int arrLen = (int) Math.ceil((double) str.length() / textLengthMax);
        List<String> stringList = new ArrayList<>(arrLen);
        for (int i = 0; i < arrLen; i++) {
            if (i == arrLen - 1) {
                stringList.add(str.substring(i * textLengthMax));
            } else {
                stringList.add(str.substring(i * textLengthMax, (i + 1) * textLengthMax));
            }
        }
        return stringList;
    }

    /**
     * 按像素将目标String拆分为List<String>
     *
     * @param str
     * @param widthMax
     * @param fontMetrics
     * @return
     */
    public static List<String> splitByPixel(String str, int widthMax, FontMetrics fontMetrics) {
        List<String> stringList = new ArrayList<>();
        char[] charArray = str.toCharArray();
        StringBuffer text = new StringBuffer();
        for (char c : charArray) {
            int textWidth = fontMetrics.stringWidth(text.toString() + c);
            if (textWidth >= widthMax) {
                stringList.add(text.toString());
                text.setLength(0);
            }
            text.append(c);
        }
        if (text.length() > 0) {
            stringList.add(text.toString());
        }
        return stringList;
    }

    @Override
    public void drawBefore(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {
        TextElement textElement = (TextElement) element;
        //设置颜色
        g2d.setColor(textElement.getColor());
        //设置字体
        g2d.setFont(textElement.getFont());

        FontMetrics fontMetrics = g2d.getFontMetrics(textElement.getFont());
        //获取文字的高度
        int textHeight = fontMetrics.getHeight();
        textElement.setTextHeight(textHeight);

        //处理换行
        //转换为List<String>
        List<String> textList = new ArrayList<>();
        switch (textElement.getLineWrapType()) {
            case NO_LINE_BREAKS -> textList.add(textElement.getText());
            case BY_TEXT_COUNT -> textList.addAll(splitByTextCount(textElement.getText(), textElement.getLineMax()));
            case BY_PIXEL ->
                    textList.addAll(splitByPixel(textElement.getText(), textElement.getLineMax(), fontMetrics));
            default -> throw new ImageBuildException("换行方式错误");
        }

        List<AttributedString> attributedStringList = new ArrayList<>(textList.size());
        for (String s : textList) {
            AttributedString attributedString = new AttributedString(s);
            //字体
            attributedString.addAttribute(TextAttribute.FONT, textElement.getFont());
            //下划线
            if (textElement.isUnderline()) {
                attributedString.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            }
            //删除线
            if (textElement.isStrikethrough()) {
                attributedString.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
            }
            attributedStringList.add(attributedString);
        }
        textElement.setAttributedStringList(attributedStringList);
        //计算文本所占宽高
        int width = 0; //宽(文本宽,文本最大宽)
        int height = textHeight * textList.size(); //高(文本所占高,单行高度*行数)
        for (String s : textList) {
            int i = fontMetrics.stringWidth(s);
            if (i > width) {
                width = i;
            }
        }
        textElement.setCalculatedValue(textList, width, height);

        //处理对齐方式(计算出实际绘制的x和y坐标)
        int x = 0;
        int y = 0;
        switch (element.getTransverseAlign()) {
            case LEFT -> x = element.getX();
            case CENTER -> x = element.getX() + (canvasProperty.getCanvasWidth() - textElement.getWidth()) / 2;
            case RIGHT -> x = element.getX() + canvasProperty.getCanvasWidth() - textElement.getWidth();
            default -> throw new ImageBuildException("对齐方式错误");
        }
        switch (element.getVerticalAlign()) {
            case TOP -> y = element.getY();
            case CENTER -> y = element.getY() + (canvasProperty.getCanvasHeight() - textElement.getHeight()) / 2;
            case BOTTOM -> y = element.getY() + canvasProperty.getCanvasHeight() - textElement.getHeight();
            default -> throw new ImageBuildException("对齐方式错误");
        }
        y = y - fontMetrics.getDescent();
        textElement.setActualXAndY(x, y);

        //处理透明
        if (Objects.nonNull(element.getAlpha())) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, element.getAlpha()));
        }
        //处理旋转
        if (textElement.getRotate() != null) {
            if (Objects.isNull(textElement.getActualRotateX()) || Objects.isNull(textElement.getActualRotateY())) {
                textElement.setRotate(textElement.getRotate(),
                        textElement.getActualX() + textElement.getWidth() / 2,
                        textElement.getActualY() - textElement.getTextHeight() + textElement.getHeight() / 2
                );
            }
            g2d.rotate(Math.toRadians(textElement.getRotate()), textElement.getActualRotateX(), textElement.getActualRotateY());
        }
    }

    @Override
    public void doDraw(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) throws ImageBuildException {
        TextElement textElement = (TextElement) element;

        //开始绘制
        int y = textElement.getActualY();
        for (AttributedString attributedString : textElement.getAttributedStringList()) {
            y += textElement.getTextHeight();
            g2d.drawString(attributedString.getIterator(), textElement.getActualX(), y);
        }

    }

    @Override
    public void drawAfter(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {
        TextElement textElement = (TextElement) element;
        //绘制完后反向旋转，以免影响后续元素
        if (textElement.getRotate() != null) {
            g2d.rotate(-Math.toRadians(textElement.getRotate()), textElement.getActualRotateX(), textElement.getActualRotateY());
        }
        //绘制完后重新设置透明度，以免影响后续元素
        if (Objects.nonNull(element.getAlpha())) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
    }

}
