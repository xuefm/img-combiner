package io.github.xuefm.painter;

import io.github.xuefm.combiner.AbstractImageCombiner;
import io.github.xuefm.element.Element;
import io.github.xuefm.element.LineElement;

import java.awt.*;
import java.util.Objects;

/**
 * 默认线画家
 */
public class DefaultLinePainter implements IPainter {


    @Override
    public void drawBefore(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {
        LineElement lineElement = (LineElement) element;
        g2d.setColor(lineElement.getColor());
        //处理透明
        if (Objects.nonNull(element.getAlpha())) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, element.getAlpha()));
        }
        //处理旋转
        if (lineElement.getRotate() != null) {
            if (Objects.isNull(lineElement.getActualRotateX()) || Objects.isNull(lineElement.getActualRotateY())) {
                lineElement.setRotate(lineElement.getRotate(),
                        (lineElement.getX1() + lineElement.getX2()) / 2,
                        (lineElement.getY1() + lineElement.getY2()) / 2
                );
            }
            g2d.rotate(Math.toRadians(lineElement.getRotate()), lineElement.getActualRotateX(), lineElement.getActualRotateY());
        }
    }

    @Override
    public void doDraw(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {
        LineElement lineElement = (LineElement) element;
        g2d.drawLine(lineElement.getX1(), lineElement.getY1(), lineElement.getX2(), lineElement.getY2());
    }

    @Override
    public void drawAfter(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {
        LineElement lineElement = (LineElement) element;
        //绘制完后反向旋转，以免影响后续元素
        if (lineElement.getRotate() != null) {
            g2d.rotate(-Math.toRadians(lineElement.getRotate()), lineElement.getActualRotateX(), lineElement.getActualRotateY());
        }
        //绘制完后重新设置透明度，以免影响后续元素
        if (Objects.nonNull(element.getAlpha())) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
    }
}
