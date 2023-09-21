package io.github.xuefm.painter;

import io.github.xuefm.combiner.AbstractImageCombiner;
import io.github.xuefm.element.Element;
import io.github.xuefm.element.LineElement;
import io.github.xuefm.exception.ImageBuildException;

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
        int width = Math.abs(lineElement.getX1() - lineElement.getX2());
        int height = Math.abs(lineElement.getY1() - lineElement.getY2());
        int x1;
        int y1;
        int x2;
        int y2;
        switch (element.getTransverseAlign()) {
            case LEFT -> x1 = element.getX();
            case CENTER -> x1 = element.getX() + (canvasProperty.getCanvasWidth() - width) / 2;

            case RIGHT -> x1 = element.getX() + canvasProperty.getCanvasWidth() - width;

            default -> throw new ImageBuildException("对齐方式错误");
        }
        x2 = x1 + width;
        switch (element.getVerticalAlign()) {
            case TOP -> y1 = element.getY();
            case CENTER -> y1 = element.getY() + (canvasProperty.getCanvasHeight() - height) / 2;
            case BOTTOM -> y1 = element.getY() + canvasProperty.getCanvasHeight() - height;
            default -> throw new ImageBuildException("对齐方式错误");
        }
        y2 = y1 + height;

        lineElement.setCalculatedValue(x1, y1, x2, y2, width, height);

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
        g2d.drawLine(lineElement.getActualX1(), lineElement.getActualY1(), lineElement.getActualX2(), lineElement.getActualY2());
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
