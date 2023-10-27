package io.github.xuefm.painter;

import io.github.xuefm.combiner.AbstractImageCombiner;
import io.github.xuefm.element.Element;
import io.github.xuefm.element.OvalElement;
import io.github.xuefm.exception.ImageBuildException;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;

/**
 * 默认椭圆形画家
 */
public class DefaultOvalPainter extends AbstractPainter {

    @Override
    public void drawBefore(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {
        OvalElement ovalElement = (OvalElement) element;
        g2d.setColor(ovalElement.getColor());

        //处理对齐方式(计算出实际绘制的x和y坐标)
        int x = 0;
        int y = 0;
        switch (element.getTransverseAlign()) {
            case LEFT -> x = element.getX();
            case CENTER ->
                    x = element.getX() + (canvasProperty.getCanvasWidth() - ((OvalElement) element).getWidth()) / 2;
            case RIGHT -> x = element.getX() + canvasProperty.getCanvasWidth() - ((OvalElement) element).getWidth();
            default -> throw new ImageBuildException("对齐方式错误");
        }
        switch (element.getVerticalAlign()) {
            case TOP -> y = element.getY();
            case CENTER -> y = element.getY() + (canvasProperty.getCanvasHeight() - ovalElement.getHeight()) / 2;
            case BOTTOM -> y = element.getY() + canvasProperty.getCanvasHeight() - ovalElement.getHeight();
            default -> throw new ImageBuildException("对齐方式错误");
        }
        ovalElement.setActualXAndY(x, y);
        //处理透明
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, element.getAlpha()));
        //处理旋转
        if (ovalElement.getRotate() != null) {
            if (Objects.isNull(ovalElement.getActualRotateX()) || Objects.isNull(ovalElement.getActualRotateY())) {
                ovalElement.setRotate(ovalElement.getRotate(),
                        ovalElement.getActualX() + ovalElement.getWidth() / 2,
                        ovalElement.getActualY() + ovalElement.getHeight() / 2
                );
            }
            g2d.rotate(Math.toRadians(ovalElement.getRotate()), ovalElement.getActualRotateX(), ovalElement.getActualRotateY());
        }

        //处理圆角
        if (Objects.nonNull(ovalElement.getRoundCorner()) && ovalElement.getRoundCorner() != 0) {
            g2d.setClip(new RoundRectangle2D.Double(ovalElement.getActualX(), ovalElement.getActualY(), ovalElement.getWidth(), ovalElement.getHeight(), ovalElement.getRoundCorner(), ovalElement.getRoundCorner()));
        }
    }

    @Override
    public void doDraw(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) throws ImageBuildException {
        OvalElement ovalElement = (OvalElement) element;


        switch (ovalElement.getOvalType()) {
            case DrawOval ->
                    g2d.drawOval(ovalElement.getActualX(), ovalElement.getActualY(), ovalElement.getWidth(), ovalElement.getHeight());
            case FillOval ->
                    g2d.fillOval(ovalElement.getActualX(), ovalElement.getActualY(), ovalElement.getWidth(), ovalElement.getHeight());
            default -> throw new ImageBuildException("椭圆形属性不支持" + ovalElement.getOvalType());
        }


    }

    @Override
    public void drawAfter(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {
        OvalElement ovalElement = (OvalElement) element;
        //绘制完后反向旋转，以免影响后续元素
        if (ovalElement.getRotate() != null) {
            g2d.rotate(-Math.toRadians(ovalElement.getRotate()), ovalElement.getActualRotateX(), ovalElement.getActualRotateY());
        }
        //绘制完后重新设置透明度，以免影响后续元素
        if (Objects.nonNull(element.getAlpha())) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
        //处理圆角
        if (Objects.nonNull(ovalElement.getRoundCorner()) && ovalElement.getRoundCorner() != 0) {
            g2d.setClip(null);
        }
    }
}
