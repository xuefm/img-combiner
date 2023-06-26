package io.github.xuefm.combiner;

import io.github.xuefm.element.Element;
import io.github.xuefm.enums.OutputFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

/**
 * ImageCombiner通用方法的实现，以及共用字段的管理
 */
@Getter
public abstract class AbstractImageCombiner implements ImageCombiner {

    /**
     * 待绘制的元素集合
     */
    protected List<Element> elementList;

    /**
     * 合成后的图片对象
     */
    protected BufferedImage combinedImage;

    protected final CanvasProperty canvasProperty;

    public AbstractImageCombiner(List<Element> elementList, int canvasWidth, int canvasHeight, OutputFormat outputFormat, Integer roundCorner, Float quality) {
        this.elementList = elementList;
        this.canvasProperty = new CanvasProperty(canvasWidth, canvasHeight, outputFormat, roundCorner, quality);
    }


    @Getter
    @AllArgsConstructor
    public static class CanvasProperty {
        /**
         * 画布宽度
         */
        protected int canvasWidth;
        /**
         * 画布高度
         */
        protected int canvasHeight;

        /**
         * 输出图片格式
         */
        protected OutputFormat outputFormat;

        /**
         * 画布圆角（针对整图）
         */
        protected Integer roundCorner;

        /**
         * 图片保存质量(取值0~1) 数值越大则生成的图片文件越大、越清晰
         */
        protected Float quality = 1f;
    }

    @Override
    public ImageCombiner addElement(Element... elements) {
        elementList.addAll(Arrays.asList(elements));
        return this;
    }


}
