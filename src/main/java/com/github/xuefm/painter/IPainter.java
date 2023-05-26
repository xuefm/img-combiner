package com.github.xuefm.painter;

import com.github.xuefm.element.Element;
import com.github.xuefm.exception.ImageBuildException;

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

    void draw(Graphics2D g, Element element) throws ImageBuildException;
}
