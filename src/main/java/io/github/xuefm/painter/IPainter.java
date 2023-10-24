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
     * @param g2d            Graphics2D
     * @param element        元素
     * @param canvasProperty 画布属性
     * @throws ImageBuildException 图片合成异常
     */
    void draw(Graphics2D g2d, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) throws ImageBuildException;
}
