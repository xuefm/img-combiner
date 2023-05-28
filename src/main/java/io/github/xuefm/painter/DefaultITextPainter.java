package io.github.xuefm.painter;

import io.github.xuefm.element.Element;
import io.github.xuefm.element.TextElement;
import io.github.xuefm.exception.ImageBuildException;

import java.awt.*;
import java.util.Objects;

/**
 * 默认文本画家
 */
public class DefaultITextPainter implements ITextPainter {

    @Override
    public void draw(Graphics2D g2d, Element element) throws ImageBuildException {
        TextElement textElement = (TextElement) element;
        FontMetrics fontMetrics = g2d.getFontMetrics(textElement.getFont());
        int textWidth = fontMetrics.stringWidth(textElement.getText()); // 获取文字的宽度
        int textHeight = fontMetrics.getHeight(); // 获取文字的高度


        //处理透明
        if (Objects.nonNull(element.getAlpha())) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, element.getAlpha()));
        }

        //处理旋转
        if (textElement.getRotate() != null) {
            g2d.rotate(Math.toRadians(textElement.getRotate()), element.getX() + textWidth / 2, element.getY() + textHeight / 2);
        }

        g2d.setColor(textElement.getColor());
        g2d.setFont(textElement.getFont());
        g2d.drawString(textElement.getText(), textElement.getX(), textElement.getY());


        //绘制完后反向旋转，以免影响后续元素
        if (textElement.getRotate() != null) {
            g2d.rotate(-Math.toRadians(textElement.getRotate()), element.getX() + textWidth / 2, element.getY() + textHeight / 2);
        }
        //绘制完后重新设置透明度，以免影响后续元素
        if (Objects.nonNull(element.getAlpha())) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
    }
}
