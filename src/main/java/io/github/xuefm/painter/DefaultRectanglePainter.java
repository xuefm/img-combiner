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
public class DefaultRectanglePainter extends AbstractPainter {

    @Override
    public void drawBefore(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {
        RectangleElement rectangleElement = (RectangleElement) element;
        g2d.setColor(rectangleElement.getColor());

        //处理对齐方式(计算出实际绘制的x和y坐标)
        int x = 0;
        int y = 0;
        switch (element.getTransverseAlign()) {
            case LEFT -> x = element.getX();
            case CENTER ->
                    x = element.getX() + (canvasProperty.getCanvasWidth() - ((RectangleElement) element).getWidth()) / 2;
            case RIGHT ->
                    x = element.getX() + canvasProperty.getCanvasWidth() - ((RectangleElement) element).getWidth();
            default -> throw new ImageBuildException("对齐方式错误");
        }
        switch (element.getVerticalAlign()) {
            case TOP -> y = element.getY();
            case CENTER -> y = element.getY() + (canvasProperty.getCanvasHeight() - rectangleElement.getHeight()) / 2;
            case BOTTOM -> y = element.getY() + canvasProperty.getCanvasHeight() - rectangleElement.getHeight();
            default -> throw new ImageBuildException("对齐方式错误");
        }
        rectangleElement.setActualXAndY(x, y);
        //处理透明
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, element.getAlpha()));
        //处理旋转
        if (rectangleElement.getRotate() != null) {
            if (Objects.isNull(rectangleElement.getActualRotateX()) || Objects.isNull(rectangleElement.getActualRotateY())) {
                rectangleElement.setRotate(rectangleElement.getRotate(),
                        rectangleElement.getActualX() + rectangleElement.getWidth() / 2,
                        rectangleElement.getActualY() + rectangleElement.getHeight() / 2
                );
            }
            g2d.rotate(Math.toRadians(rectangleElement.getRotate()), rectangleElement.getActualRotateX(), rectangleElement.getActualRotateY());
        }

        //处理圆角
        if (Objects.nonNull(rectangleElement.getRoundCorner())) {
            g2d.setClip(new RoundRectangle2D.Double(rectangleElement.getActualX(), rectangleElement.getActualY(), rectangleElement.getWidth(), rectangleElement.getHeight(), rectangleElement.getRoundCorner(), rectangleElement.getRoundCorner()));
        }
    }

    @Override
    public void doDraw(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) throws ImageBuildException {
        RectangleElement rectangleElement = (RectangleElement) element;


        switch (rectangleElement.getRectangleType()) {
            case DrawRect ->
                    g2d.drawRect(rectangleElement.getActualX(), rectangleElement.getActualY(), rectangleElement.getWidth(), rectangleElement.getHeight());
            case FillRect ->
                    g2d.fillRect(rectangleElement.getActualX(), rectangleElement.getActualY(), rectangleElement.getWidth(), rectangleElement.getHeight());
            case Draw3DRect ->
                    g2d.draw3DRect(rectangleElement.getActualX(), rectangleElement.getActualY(), rectangleElement.getWidth(), rectangleElement.getHeight(), false);
            case Fill3DRect ->
                    g2d.fill3DRect(rectangleElement.getActualX(), rectangleElement.getActualY(), rectangleElement.getWidth(), rectangleElement.getHeight(), false);
            case DrawRoundRect ->
                    g2d.drawRoundRect(rectangleElement.getActualX(), rectangleElement.getActualY(), rectangleElement.getWidth(), rectangleElement.getHeight(), rectangleElement.getRoundCorner(), rectangleElement.getRoundCorner());
            case FillRoundRect ->
                    g2d.fillRoundRect(rectangleElement.getActualX(), rectangleElement.getActualY(), rectangleElement.getWidth(), rectangleElement.getHeight(), rectangleElement.getRoundCorner(), rectangleElement.getRoundCorner());
            default -> throw new ImageBuildException("矩形属性不支持" + rectangleElement.getRectangleType());
        }


    }

    @Override
    public void drawAfter(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {
        RectangleElement rectangleElement = (RectangleElement) element;
        //绘制完后反向旋转，以免影响后续元素
        if (rectangleElement.getRotate() != null) {
            g2d.rotate(-Math.toRadians(rectangleElement.getRotate()), rectangleElement.getActualRotateX(), rectangleElement.getActualRotateY());
        }
        //绘制完后重新设置透明度，以免影响后续元素
        if (Objects.nonNull(element.getAlpha())) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
        //处理圆角
        if (Objects.nonNull(rectangleElement.getRoundCorner())) {
            g2d.setClip(null);
        }
    }
}
