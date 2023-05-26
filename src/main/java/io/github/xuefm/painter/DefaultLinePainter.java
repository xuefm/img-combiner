package io.github.xuefm.painter;

import io.github.xuefm.element.Element;
import io.github.xuefm.element.LineElement;
import io.github.xuefm.exception.ImageBuildException;

import java.awt.*;

/**
 * 默认线画家
 */
public class DefaultLinePainter implements ILinePainter {
    @Override
    public void draw(Graphics2D g2d, Element element) throws ImageBuildException {
        LineElement lineElement = (LineElement) element;
        g2d.setColor(lineElement.getColor());
        g2d.drawLine(lineElement.getX(), lineElement.getY(), lineElement.getX2(), lineElement.getY2());
    }
}
