package com.github.xuefm.combiner;

import com.github.xuefm.element.Element;
import com.github.xuefm.enums.OutputFormat;
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
     * 图片保存质量
     */
    protected Float quality = 1f;                                         //

    public AbstractImageCombiner(List<Element> elementList, int canvasWidth, int canvasHeight, OutputFormat outputFormat, Integer roundCorner, Float quality) {
        this.elementList = elementList;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.outputFormat = outputFormat;
        this.roundCorner = roundCorner;
        this.quality = quality;
    }

    @Override
    public ImageCombiner addElement(Element... elements) {
        elementList.addAll(Arrays.asList(elements));
        return this;
    }


}
