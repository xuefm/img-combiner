package io.github.xuefm.config;

import io.github.xuefm.element.*;
import io.github.xuefm.exception.ImageBuildException;
import io.github.xuefm.painter.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ImageCombiner配置
 */
public class ImageCombinerConfig {

    /**
     * painter集合
     */
    private static final Map<Class<? extends Element>, IPainter> painterMap = new ConcurrentHashMap<>();

    static {
        painterMap.put(ImageElement.class, new DefaultImagePainter());
        painterMap.put(TextElement.class, new DefaultITextPainter());
        painterMap.put(LineElement.class, new DefaultLinePainter());
        painterMap.put(RectangleElement.class, new DefaultRectanglePainter());
        painterMap.put(OvalElement.class, new DefaultOvalPainter());
    }

    /**
     * 为Element添加Painter
     *
     * @param elementClass Element子类
     * @param painter      不能为null
     */
    public static void setPainter(Class<? extends Element> elementClass, IPainter painter) {
        if (painter == null)
            throw new ImageBuildException("painter不能为null");
        painterMap.put(elementClass, painter);

    }

    /**
     * 获取element对应的Painter
     *
     * @param element Element子类
     * @return IPainter
     * @throws ImageBuildException 若未找到匹配的Painter则抛出异常
     */
    public static IPainter getInstance(Element element) throws ImageBuildException {
        if (painterMap.containsKey(element.getClass())) {
            return painterMap.get(element.getClass());
        } else {
            throw new ImageBuildException("找不到匹配的IPainter，请使用ImageCombinerConfig.setPainter()方法为" + element.getClass() + "添加IPainter");
        }
    }
}
