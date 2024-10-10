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
public class DefaultLinePainter extends AbstractPainter {


    @Override
    public void drawBefore(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {
        LineElement lineElement = (LineElement) element;
        g2d.setColor(lineElement.getColor());
        //处理透明
        if (Objects.nonNull(element.getAlpha())) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, element.getAlpha()));
        }
        int width = Math.abs(lineElement.getX2() - lineElement.getX1());
        int height = Math.abs(lineElement.getY2() - lineElement.getY1());
        int x1 = lineElement.getX1();
        int y1 = lineElement.getY1();
        int x2 = lineElement.getX2();
        int y2 = lineElement.getY2();


        switch (element.getTransverseAlign()) {
            case LEFT -> {
                if (lineElement.getX1() < lineElement.getX2()) {
                    x1 = 0;
                    x2 = width;
                } else if (lineElement.getX1() > lineElement.getX2()) {
                    x1 = width;
                    x2 = 0;
                } else {
                    x1 = 0;
                    x2 = 0;
                }
            }
            case CENTER -> {
                if (lineElement.getX1() < lineElement.getX2()) {
                    x1 = 0;
                    x2 = width;
                } else if (lineElement.getX1() > lineElement.getX2()) {
                    x1 = width;
                    x2 = 0;
                } else {
                    x1 = 0;
                    x2 = 0;
                }
                x1 += (canvasProperty.getCanvasWidth() - width) / 2;
                x2 += (canvasProperty.getCanvasWidth() - width) / 2;
            }
            case RIGHT -> {
                if (lineElement.getX1() < lineElement.getX2()) {
                    x1 = canvasProperty.getCanvasWidth() - width;
                    x2 = canvasProperty.getCanvasWidth();
                } else if (lineElement.getX1() > lineElement.getX2()) {
                    x2 = canvasProperty.getCanvasWidth() - width;
                    x1 = canvasProperty.getCanvasWidth();
                } else {
                    x1 = canvasProperty.getCanvasWidth();
                    x2 = canvasProperty.getCanvasWidth();
                }
            }

            default -> throw new ImageBuildException("对齐方式错误");
        }
        x1 += element.getX();
        x2 += element.getX();
        switch (element.getVerticalAlign()) {
            case TOP -> {
                if (lineElement.getY1() < lineElement.getY2()) {
                    y1 = 0;
                    y2 = height;
                } else if (lineElement.getY1() > lineElement.getY2()) {
                    y1 = height;
                    y2 = 0;
                } else {
                    y1 = 0;
                    y2 = 0;
                }
            }
            case CENTER -> {
                if (lineElement.getY1() < lineElement.getY2()) {
                    y1 = 0;
                    y2 = height;
                } else if (lineElement.getY1() > lineElement.getY2()) {
                    y1 = height;
                    y2 = 0;
                } else {
                    y1 = 0;
                    y2 = 0;
                }
                y1 += (canvasProperty.getCanvasHeight() - height) / 2;
                y2 += (canvasProperty.getCanvasHeight() - height) / 2;
            }
            case BOTTOM -> {
                if (lineElement.getY1() < lineElement.getY2()) {
                    y1 = canvasProperty.getCanvasHeight() - height;
                    y2 = canvasProperty.getCanvasHeight();
                } else if (lineElement.getY1() > lineElement.getY2()) {
                    y2 = canvasProperty.getCanvasHeight() - height;
                    y1 = canvasProperty.getCanvasHeight();
                } else {
                    y1 = canvasProperty.getCanvasHeight();
                    y2 = canvasProperty.getCanvasHeight();
                }
            }
            default -> throw new ImageBuildException("对齐方式错误");
        }
        y1 += element.getY();
        y2 += element.getY();
        lineElement.setCalculatedValue(x1, y1, x2, y2, width, height);

        //处理旋转
        if (lineElement.getRotate() != null) {
            if (Objects.isNull(lineElement.getActualRotateX()) || Objects.isNull(lineElement.getActualRotateY())) {
                lineElement.setRotate(lineElement.getRotate(),
                        (lineElement.getActualX1() + lineElement.getActualX2()) / 2,
                        (lineElement.getActualY1() + lineElement.getActualY2()) / 2
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
