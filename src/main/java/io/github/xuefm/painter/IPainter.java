package io.github.xuefm.painter;

import io.github.xuefm.combiner.AbstractImageCombiner;
import io.github.xuefm.element.Element;
import io.github.xuefm.exception.ImageBuildException;

import java.awt.*;

/**
 * 画家接口
 */
public interface IPainter {

    /**
     * 绘制
     *
     * @param g2d
     * @param element
     * @throws ImageBuildException
     */
//    void draw1(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) throws ImageBuildException;
    default void draw(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) throws ImageBuildException {
        drawBefore(g2d, element, canvasProperty);
        doDraw(g2d, element, canvasProperty);
        drawAfter(g2d, element, canvasProperty);
    }

    /**
     * 绘制前处理（处理颜色、旋转、透明、实际绘制位置）
     *
     * @param g2d
     * @param element
     * @param canvasProperty
     */
    void drawBefore(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty);

    /**
     * 开始绘制
     *
     * @param g2d
     * @param element
     * @param canvasProperty
     */
    void doDraw(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty);

    /**
     * 绘制后处理（清理颜色、旋转、透明等 防止影响后面添加的元素）
     *
     * @param g2d
     * @param element
     * @param canvasProperty
     */
    void drawAfter(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty);
}
