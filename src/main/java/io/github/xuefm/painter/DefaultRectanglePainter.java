package io.github.xuefm.painter;

import io.github.xuefm.combiner.AbstractImageCombiner;
import io.github.xuefm.element.Element;
import io.github.xuefm.element.RectangleElement;
import io.github.xuefm.exception.ImageBuildException;

import java.awt.*;

/**
 * 默认矩形画家
 */
public class DefaultRectanglePainter implements IPainter {

    @Override
    public void draw(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) throws ImageBuildException {
        RectangleElement rectangleElement = (RectangleElement) element;
        g2d.setColor(rectangleElement.getColor());

        switch (rectangleElement.getRectangleType()) {
            case DrawRect ->
                    g2d.drawRect(element.getX(), element.getY(), rectangleElement.getWidth(), rectangleElement.getHeight());
            case FillRect ->
                    g2d.fillRect(element.getX(), element.getY(), rectangleElement.getWidth(), rectangleElement.getHeight());
            case Draw3DRect ->
                    g2d.draw3DRect(element.getX(), element.getY(), rectangleElement.getWidth(), rectangleElement.getHeight(), false);
            case Fill3DRect ->
                    g2d.fill3DRect(element.getX(), element.getY(), rectangleElement.getWidth(), rectangleElement.getHeight(), false);
            case DrawRoundRect ->
                    g2d.drawRoundRect(element.getX(), element.getY(), rectangleElement.getWidth(), rectangleElement.getHeight(), rectangleElement.getRoundCorner(), rectangleElement.getRoundCorner());
            case FillRoundRect ->
                    g2d.fillRoundRect(element.getX(), element.getY(), rectangleElement.getWidth(), rectangleElement.getHeight(), rectangleElement.getRoundCorner(), rectangleElement.getRoundCorner());
            default -> throw new ImageBuildException("PLEASE SET 'rectangleType' PROPERTY");
        }

    }
}
