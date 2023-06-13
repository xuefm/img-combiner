package io.github.xuefm.painter;

import io.github.xuefm.combiner.AbstractImageCombiner;
import io.github.xuefm.element.Element;
import io.github.xuefm.element.LineElement;

import java.awt.*;

/**
 * 默认线画家
 */
public class DefaultLinePainter implements IPainter {


    @Override
    public void drawBefore(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {

    }

    @Override
    public void doDraw(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {
        LineElement lineElement = (LineElement) element;
        g2d.setColor(lineElement.getColor());
        g2d.drawLine(lineElement.getX(), lineElement.getY(), lineElement.getX2(), lineElement.getY2());
    }

    @Override
    public void drawAfter(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {

    }
}
