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
    public void draw(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) throws ImageBuildException {
        ImageElement imageElement = (ImageElement) element;

        //处理宽高
        int width = imageElement.getImage().getWidth();
        int height = imageElement.getImage().getHeight();
        if (Objects.nonNull(imageElement.getWidth())) {
            width = imageElement.getWidth();
        }
        if (Objects.nonNull(imageElement.getWidth())) {
            height = imageElement.getHeight();
        }
        //处理对齐方式
        int x = 0;
        int y = 0;
        switch (element.getTransverseAlign()) {
            case LEFT -> x = element.getX();
            case CENTER -> x = (canvasProperty.getCanvasWidth() - ((ImageElement) element).getWidth()) / 2;
            case RIGHT -> x = canvasProperty.getCanvasWidth() - ((ImageElement) element).getWidth();
            default -> throw new ImageBuildException("对齐方式错误");
        }
        switch (element.getVerticalAlign()) {
            case TOP -> y = element.getY();
            case CENTER -> y = (canvasProperty.getCanvasHeight() - imageElement.getHeight()) / 2;
            case BOTTOM -> y = canvasProperty.getCanvasHeight() - imageElement.getHeight();
            default -> throw new ImageBuildException("对齐方式错误");
        }

        //处理透明
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, element.getAlpha()));
        //处理旋转
        if (imageElement.getRotate() != null) {
            g2d.rotate(Math.toRadians(imageElement.getRotate()), x + imageElement.getWidth() / 2, y + imageElement.getHeight() / 2);
        }

        //处理圆角
        if (Objects.nonNull(imageElement.getRoundCorner())) {
            g2d.setClip(new RoundRectangle2D.Double(x, y, width, height, imageElement.getRoundCorner(), imageElement.getRoundCorner()));
        }

        //绘制
        g2d.drawImage(imageElement.getImage(), x, y, width, height, null);


        //绘制完后反向旋转，以免影响后续元素
        if (imageElement.getRotate() != null) {
            g2d.rotate(-Math.toRadians(imageElement.getRotate()), x + imageElement.getWidth() / 2, y + imageElement.getHeight() / 2);
        }
        //绘制完后重新设置透明度，以免影响后续元素
        if (Objects.nonNull(element.getAlpha())) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }


    }
}
