package io.github.xuefm.painter;

import io.github.xuefm.combiner.AbstractImageCombiner;
import io.github.xuefm.element.Element;
import io.github.xuefm.element.RectangleElement;
import io.github.xuefm.exception.ImageBuildException;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;

/**
 * 默认矩形画家
 */
public class DefaultRectanglePainter implements IPainter {

    @Override
    public void drawBefore(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {

    }

    @Override
    public void doDraw(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) throws ImageBuildException {
        RectangleElement rectangleElement = (RectangleElement) element;
        g2d.setColor(rectangleElement.getColor());

        //处理对齐方式
        int x = 0;
        int y = 0;
        switch (element.getTransverseAlign()) {
            case LEFT -> x = element.getX();
            case CENTER -> x = (canvasProperty.getCanvasWidth() - ((RectangleElement) element).getWidth()) / 2;
            case RIGHT -> x = canvasProperty.getCanvasWidth() - ((RectangleElement) element).getWidth();
            default -> throw new ImageBuildException("对齐方式错误");
        }
        switch (element.getVerticalAlign()) {
            case TOP -> y = element.getY();
            case CENTER -> y = (canvasProperty.getCanvasHeight() - rectangleElement.getHeight()) / 2;
            case BOTTOM -> y = canvasProperty.getCanvasHeight() - rectangleElement.getHeight();
            default -> throw new ImageBuildException("对齐方式错误");
        }

        //处理透明
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, element.getAlpha()));
        //处理旋转
        if (rectangleElement.getRotate() != null) {
            g2d.rotate(Math.toRadians(rectangleElement.getRotate()), x + rectangleElement.getWidth() / 2, y + rectangleElement.getHeight() / 2);
        }

        //处理圆角
        if (Objects.nonNull(rectangleElement.getRoundCorner())) {
            g2d.setClip(new RoundRectangle2D.Double(x, y, rectangleElement.getWidth(), rectangleElement.getHeight(), rectangleElement.getRoundCorner(), rectangleElement.getRoundCorner()));
        }


        switch (rectangleElement.getRectangleType()) {
            case DrawRect -> g2d.drawRect(x, y, rectangleElement.getWidth(), rectangleElement.getHeight());
            case FillRect -> g2d.fillRect(x, y, rectangleElement.getWidth(), rectangleElement.getHeight());
            case Draw3DRect -> g2d.draw3DRect(x, y, rectangleElement.getWidth(), rectangleElement.getHeight(), false);
            case Fill3DRect -> g2d.fill3DRect(x, y, rectangleElement.getWidth(), rectangleElement.getHeight(), false);
            case DrawRoundRect -> g2d.drawRoundRect(x, y, rectangleElement.getWidth(), rectangleElement.getHeight(), rectangleElement.getRoundCorner(), rectangleElement.getRoundCorner());
            case FillRoundRect -> g2d.fillRoundRect(x, y, rectangleElement.getWidth(), rectangleElement.getHeight(), rectangleElement.getRoundCorner(), rectangleElement.getRoundCorner());
            default -> throw new ImageBuildException("PLEASE SET 'rectangleType' PROPERTY");
        }

        //绘制完后反向旋转，以免影响后续元素
        if (rectangleElement.getRotate() != null) {
            g2d.rotate(-Math.toRadians(rectangleElement.getRotate()), x + rectangleElement.getWidth() / 2, y + rectangleElement.getHeight() / 2);
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
