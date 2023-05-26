package com.github.xuefm.combiner;

import com.github.xuefm.element.Element;
import com.github.xuefm.exception.ImageBuildException;

import java.io.IOException;
import java.io.InputStream;


/**
 * 图像组合器
 */
public interface ImageCombiner {

    /**
     * 添加元素
     *
     * @param elements
     * @return
     */
    ImageCombiner addElement(Element... elements);

    /**
     * 合成图片
     *
     * @return
     */
    ImageCombiner generate();

    /**
     * 获取合成图片输入流
     * 在调用generate方法后调用否则抛出异常
     *
     * @return
     * @throws ImageBuildException
     */
    InputStream getCombinedImageStream() throws ImageBuildException;

    /**
     * 保存图片到文件
     * 在调用generate方法后调用否则抛出异常
     *
     * @param filePath
     * @throws ImageBuildException
     */
    void save(String filePath) throws ImageBuildException, IOException;
}
