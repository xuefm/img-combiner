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
     * @param g
     * @param element
     * @throws ImageBuildException
     */

    void draw(Graphics2D g, Element element, AbstractImageCombiner.CanvasProperty canvasProperty) throws ImageBuildException;
}
