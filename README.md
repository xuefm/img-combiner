## 一、介绍

本项目为个人学习使用参考[dromara](https://gitee.com/dromara) / [image-combiner](https://gitee.com/dromara/image-combiner)

生产环境建议使用[dromara](https://gitee.com/dromara) / [image-combiner](https://gitee.com/dromara/image-combiner)

版本要求：JDK17

## 二、安装

```xml
<dependency>
    <groupId>io.github.xuefm</groupId>
    <artifactId>img-combiner</artifactId>
    <version>0.1.1</version>
</dependency>
```

## 三、示例

以下是测试方法

```java
package io.github.xuefm;

import io.github.xuefm.combiner.DefaultImageCombiner;
import io.github.xuefm.combiner.ImageCombiner;
import io.github.xuefm.element.ImageElement;
import io.github.xuefm.element.RectangleElement;
import io.github.xuefm.element.TextElement;
import io.github.xuefm.enums.AlignType;
import io.github.xuefm.enums.LineWrapType;
import io.github.xuefm.enums.OutputFormat;

import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImgTest {

    private static String filePath = "file:///" + System.getProperty("user.dir") + "/test_file/";
    private static String generateFilePath = System.getProperty("user.dir") + "/test_file/generate/";


    @BeforeClass
    public static void init() {
        File file = new File(generateFilePath);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            File[] files = file.listFiles();
            assert files != null;
            for (File file1 : files) {
                file1.delete();
            }
        }
    }

    /**
     * 图片测试(主要测试横向对齐)
     *
     * @throws IOException
     */
    @Test
    public void t01ImgTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                ImageElement.of(filePath + "cat.jpg", AlignType.TransverseAlign.LEFT, 0, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(45)
                        .setAlpha(0.25f),
                ImageElement.of(filePath + "cat.jpg", AlignType.TransverseAlign.CENTER, 0, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(90)
                        .setAlpha(0.5f),
                ImageElement.of(filePath + "cat.jpg", AlignType.TransverseAlign.RIGHT, 0, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(180)
                        .setAlpha(0.75f)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "图片01" + "_t01ImgTest.png");
    }

    /**
     * 图片测试(主要测试纵向对齐)
     * @throws IOException
     */
    @Test
    public void t02ImgTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                ImageElement.of(filePath + "cat.jpg", 0, AlignType.VerticalAlign.TOP, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(45)
                        .setAlpha(0.25f),
                ImageElement.of(filePath + "cat.jpg", 0, AlignType.VerticalAlign.CENTER, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(90)
                        .setAlpha(0.5f),
                ImageElement.of(filePath + "cat.jpg", 0, AlignType.VerticalAlign.BOTTOM, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(180)
                        .setAlpha(0.75f)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "图片02" + "_t02ImgTest.png");
    }

    /**
     * 矩形测试(主要横向试纵向对齐)
     * @throws IOException
     */
    @Test
    public void t01RectangleTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                RectangleElement.of(AlignType.TransverseAlign.LEFT, 0, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(30)
                        .setAlpha(0.25f)
                        .setColor(Color.RED)
                ,
                RectangleElement.of(AlignType.TransverseAlign.CENTER, 0, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(60)
                        .setAlpha(0.5f)
                        .setColor(Color.GREEN),
                RectangleElement.of(AlignType.TransverseAlign.RIGHT, 0, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(90)
                        .setAlpha(0.75f)
                        .setColor(Color.BLUE)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "矩形01" + "_t02RectangleTest.png");
    }

    /**
     * 矩形测试(主要横向试纵向对齐)
     * @throws IOException
     */
    @Test
    public void t02RectangleTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                RectangleElement.of(0, AlignType.VerticalAlign.TOP, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(30)
                        .setAlpha(0.25f)
                        .setColor(Color.RED),
                RectangleElement.of(0, AlignType.VerticalAlign.CENTER, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(60)
                        .setAlpha(0.5f)
                        .setColor(Color.GREEN),
                RectangleElement.of(0, AlignType.VerticalAlign.BOTTOM, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(90)
                        .setAlpha(0.75f)
                        .setColor(Color.BLUE)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "矩形02" + "_t02RectangleTest.png");
    }

    @Test
    public void t01TextTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("汉字文本：你好世界", 0, 0).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.CYAN)
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3),
                TextElement.of("English text:Hello world", 0, 200).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.GREEN)
                        .setLineFeed(LineWrapType.BY_PIXEL, 100)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "文本01" + "_t01TextTest.png");
    }

    @Test
    public void t02TextTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("汉字文本：你好世界", AlignType.TransverseAlign.CENTER, 0).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.CYAN)
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3),
                TextElement.of("English text:Hello world", AlignType.TransverseAlign.CENTER, 200).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.GREEN)
                        .setLineFeed(LineWrapType.BY_PIXEL, 100)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "文本02" + "_t02TextTest.png");
    }

    @Test
    public void t03TextTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("汉字文本：你好世界", 0, AlignType.VerticalAlign.TOP).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.CYAN)
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3),
                TextElement.of("English text:Hello world", 0, AlignType.VerticalAlign.BOTTOM).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.GREEN)
                        .setLineFeed(LineWrapType.BY_PIXEL, 100)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "文本03" + "_t03TextTest.png");
    }

    @Test
    public void t01MixingTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(

                ImageElement.of(filePath + "cat.jpg", AlignType.TransverseAlign.CENTER, AlignType.VerticalAlign.TOP, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(45),
                RectangleElement.of(AlignType.TransverseAlign.CENTER, AlignType.VerticalAlign.CENTER, 100, 100)
                        .setRoundCorner(50)
                        .setColor(Color.RED),
                TextElement.of("汉字文本：你好世界", AlignType.TransverseAlign.CENTER, AlignType.VerticalAlign.BOTTOM).setFont(new Font("微软雅黑", Font.PLAIN, 32))
                        .setAlpha(1f)
                        .setColor(Color.CYAN)
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3),
                TextElement.of("汉字文本：你好世界", 0, 0).setFont(new Font("宋体", Font.PLAIN, 32))
                        .setAlpha(1f)
                        .setColor(Color.CYAN)
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "混合01" + "_t01MixingTest.png");
    }


}

```

## 四、详细

#### 1.元素介绍

| 元素类              | 元素介绍 |      |
|------------------|------|------|
| ImageElement     | 图片元素 |      |
| TextElement      | 文本元素 |      |
| RectangleElement | 矩形元素 |      |
| LineElement      | 线形元素 |      |

## 五、扩展方法

如果提供的元素不能满足或有更好的实现可按一下方式进行扩展

1.创建XxxElement并继承Element

2.创建XxxPainter并继承IPainter

3.在配置中添加对应配置ImageCombinerConfig.setPainter(XxxElement.class,new XxxPainter());




