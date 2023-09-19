package io.github.xuefm.painter;

import io.github.xuefm.combiner.AbstractImageCombiner;
import io.github.xuefm.element.Element;
import io.github.xuefm.element.ImageElement;
import io.github.xuefm.exception.ImageBuildException;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;

/**
 * 默认图片画家
 */
public class DefaultImagePainter implements IPainter {

    @Override
    public void drawBefore(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {
        ImageElement imageElement = (ImageElement) element;

        //处理对齐方式(计算出实际绘制的x和y坐标)
        int x = 0;
        int y = 0;
        switch (element.getTransverseAlign()) {
            case LEFT -> x = element.getX();
            case CENTER ->
                    x = element.getX() + (canvasProperty.getCanvasWidth() - ((ImageElement) element).getWidth()) / 2;
            case RIGHT -> x = element.getX() + canvasProperty.getCanvasWidth() - ((ImageElement) element).getWidth();
            default -> throw new ImageBuildException("对齐方式错误");
        }
        switch (element.getVerticalAlign()) {
            case TOP -> y = element.getY();
            case CENTER -> y = element.getY() + (canvasProperty.getCanvasHeight() - imageElement.getHeight()) / 2;
            case BOTTOM -> y = element.getY() + canvasProperty.getCanvasHeight() - imageElement.getHeight();
            default -> throw new ImageBuildException("对齐方式错误");
        }
        imageElement.setActualXAndY(x, y);
        //处理透明
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, element.getAlpha()));
        //处理旋转
        if (Objects.nonNull(imageElement.getRotate())) {
            if (Objects.isNull(imageElement.getActualRotateX()) || Objects.isNull(imageElement.getActualRotateY())) {
                imageElement.setRotate(imageElement.getRotate(),
                        imageElement.getActualX() + imageElement.getWidth() / 2,
                        imageElement.getActualY() + imageElement.getHeight() / 2
                );
            }
            g2d.rotate(Math.toRadians(imageElement.getRotate()), imageElement.getActualRotateX(), imageElement.getActualRotateY());
        }
        //处理圆角
        if (Objects.nonNull(imageElement.getRoundCorner())) {
            g2d.setClip(new RoundRectangle2D.Double(imageElement.getActualX(), imageElement.getActualY(), imageElement.getWidth(), imageElement.getHeight(), imageElement.getRoundCorner(), imageElement.getRoundCorner()));
        }
    }

    @Override
    public void doDraw(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {
        ImageElement imageElement = (ImageElement) element;
        g2d.drawImage(imageElement.getImage(), imageElement.getActualX(), imageElement.getActualY(), imageElement.getWidth(), imageElement.getHeight(), null);

    }

    @Override
    public void drawAfter(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) {
        ImageElement imageElement = (ImageElement) element;
        //绘制完后反向旋转，以免影响后续元素
        if (imageElement.getRotate() != null) {
            g2d.rotate(-Math.toRadians(imageElement.getRotate()), imageElement.getActualRotateX(), imageElement.getActualRotateY());
        }
        //绘制完后重新设置透明度，以免影响后续元素
        if (Objects.nonNull(element.getAlpha())) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
        //处理圆角
        if (Objects.nonNull(imageElement.getRoundCorner())) {
            g2d.setClip(null);
        }
    }
}
