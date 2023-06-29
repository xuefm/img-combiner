package io.github.xuefm.combiner;

import io.github.xuefm.element.Element;
import io.github.xuefm.exception.ImageBuildException;

import java.io.IOException;
import java.io.InputStream;


/**
 * 图像组合器
 */
public interface ImageCombiner {

    /**
     * 添加元素
     *
     * @param elements 元素
     * @return ImageCombiner
     */
    ImageCombiner addElement(Element... elements);

    /**
     * 合成图片
     *
     * @return ImageCombiner
     */
    ImageCombiner generate();

    /**
     * 获取合成图片输入流
     * 在调用generate方法后调用否则抛出异常
     *
     * @return InputStream
     * @throws ImageBuildException 图片合成异常
     */
    InputStream getCombinedImageStream() throws ImageBuildException;

    /**
     * 保存图片到文件
     * 在调用generate方法后调用否则抛出异常
     *
     * @param filePath 文件保存路径
     * @throws ImageBuildException 图片合成异常
     * @throws IOException         IOException
     */
    void save(String filePath) throws ImageBuildException, IOException;
}
