package io.github.xuefm.painter;

import io.github.xuefm.combiner.AbstractImageCombiner;
import io.github.xuefm.element.Element;
import io.github.xuefm.element.TextElement;
import io.github.xuefm.exception.ImageBuildException;

import java.awt.*;
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

    }

    @Override
    public void doDraw(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) throws ImageBuildException {
        TextElement textElement = (TextElement) element;
        FontMetrics fontMetrics = g2d.getFontMetrics(textElement.getFont());
        int textHeight = fontMetrics.getHeight(); // 获取文字的高度


        //处理透明
        if (Objects.nonNull(element.getAlpha())) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, element.getAlpha()));
        }

        //处理换行
        //转换为List<String>
        List<String> stringList = new ArrayList<>();
        switch (textElement.getLineWrapType()) {
            case NO_LINE_BREAKS -> stringList.add(textElement.getText());
            case BY_TEXT_COUNT -> stringList.addAll(splitByTextCount(textElement.getText(), textElement.getLineMax()));
            case BY_PIXEL ->
                    stringList.addAll(splitByPixel(textElement.getText(), textElement.getLineMax(), fontMetrics));
            default -> throw new ImageBuildException("换行方式错误");
        }
        //计算文本所占宽高
        int width = 0; //宽(文本宽,文本最大宽)
        int height = textHeight * stringList.size(); //高(文本所占高,单行高度*行数)
        for (String s : stringList) {
            int i = fontMetrics.stringWidth(s);
            if (i > width) {
                width = i;
            }
        }


        //处理旋转
        if (textElement.getRotate() != null) {
            g2d.rotate(Math.toRadians(textElement.getRotate()), element.getX() + width / 2, element.getY() + height / 2);
        }

        g2d.setColor(textElement.getColor());
        g2d.setFont(textElement.getFont());
        //开始绘制
        int y = textElement.getY();
        for (String string : stringList) {
            g2d.drawString(string, textElement.getX(), y);
            y += textHeight;
        }
        //绘制完后反向旋转，以免影响后续元素
        if (textElement.getRotate() != null) {
            g2d.rotate(-Math.toRadians(textElement.getRotate()), element.getX() + width / 2, element.getY() + height / 2);
        }
        //绘制完后重新设置透明度，以免影响后续元素
        if (Objects.nonNull(element.getAlpha())) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
    }

    @Override
    public void drawAfter(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {

    }

}
