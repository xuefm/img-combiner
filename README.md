## 一、介绍

本项目为个人学习使用参考[dromara](https://gitee.com/dromara) / [image-combiner](https://gitee.com/dromara/image-combiner)

生产环境建议使用[dromara](https://gitee.com/dromara) / [image-combiner](https://gitee.com/dromara/image-combiner)

版本要求：JDK17

## 二、安装

```xml
<dependency>
    <groupId>io.github.xuefm</groupId>
    <artifactId>img-combiner</artifactId>
    <version>0.0.2</version>
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
import io.github.xuefm.enums.OutputFormat;
import io.github.xuefm.enums.RectangleType;
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
     * 测试添加图片(指定位置)
     *
     * @throws IOException
     */
    @Test
    public void addImageTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(ImageElement.of(filePath + "cat.jpg", 10, 10));
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "img" + "指定位置.png");
    }

    /**
     * 添加图片(指定宽高)
     *
     * @throws IOException
     */
    @Test
    public void addImageWidthHeightTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(ImageElement.of(filePath + "cat.jpg", 10, 10, 150, 200));
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "img" + "指定宽高.png");
    }

    /**
     * 添加图片(圆角)
     *
     * @throws IOException
     */
    @Test
    public void addImageFilletTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                ImageElement.of(filePath + "cat.jpg", 50, 50, 150, 150).setRoundCorner(20)

        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "img" + "圆角.png");
    }

    /**
     * 添加图片(旋转)
     *
     * @throws IOException
     */
    @Test
    public void addImageRotateTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                ImageElement.of(filePath + "cat.jpg", 50, 50, 150, 150)
                        .setRotate(45)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "img" + "旋转.png");
    }

    /**
     * 添加图片(透明)
     *
     * @throws IOException
     */
    @Test
    public void addImageAlphaTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                ImageElement.of(filePath + "cat.jpg", 50, 50, 150, 150)
                        .setAlpha(0.5f)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "img" + "透明.png");
    }

    /**
     * 添加文本
     *
     * @throws IOException
     */
    @Test
    public void addTextTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("Hello World", 10, 10),
                TextElement.of("你好，世界", 10, 50)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "text" + "添加文本.png");
    }

    /**
     * 添加文本(颜色)
     *
     * @throws IOException
     */
    @Test
    public void addTextColorTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("Hello World", 10, 10).setColor(Color.CYAN),
                TextElement.of("你好，世界", 10, 50).setColor(Color.yellow)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "text" + "颜色.png");
    }


    /**
     * 添加文本(字体)
     *
     * @throws IOException
     */
    @Test
    public void addTextFontTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        int y = 0;
        imageCombiner.addElement(
                TextElement.of("Hello World", 10, y += 36).setFont(new Font("微软雅黑", Font.PLAIN, 36)),
                TextElement.of("Hello World", 10, y += 36).setFont(new Font("宋体", Font.PLAIN, 36)),
                TextElement.of("你好，世界", 10, y += 72).setFont(new Font("微软雅黑", Font.PLAIN, 72)),
                TextElement.of("你好，世界", 10, y += 72).setFont(new Font("宋体", Font.BOLD, 72))
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "text" + "字体.png");
    }


    /**
     * 添加文本(透明)
     *
     * @throws IOException
     */
    @Test
    public void addTextAlphaTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        int y = 0;
        imageCombiner.addElement(
                TextElement.of("Hello World", 10, y += 36).setFont(new Font("微软雅黑", Font.PLAIN, 36)),
                TextElement.of("Hello World", 10, y += 36).setFont(new Font("宋体", Font.PLAIN, 36)).setAlpha(0.5f),
                TextElement.of("你好，世界", 10, y += 72).setFont(new Font("微软雅黑", Font.PLAIN, 72)),
                TextElement.of("你好，世界", 10, y += 72).setFont(new Font("宋体", Font.BOLD, 72)).setAlpha(0.2f)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "text" + "文字透明.png");
    }

    /**
     * 添加文本(旋转)
     *
     * @throws IOException
     */
    @Test
    public void addTextRotateTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("Hello World", 0, -36).setFont(new Font("微软雅黑", Font.PLAIN, 36)).setRotate(180),
                TextElement.of("Hello World", 0, 72).setFont(new Font("微软雅黑", Font.PLAIN, 36))
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "text" + "文字旋转.png");
    }

    /**
     * 添加矩形
     *
     * @throws IOException
     */
    @Test
    public void addRectangleTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                RectangleElement.of(10, 10, 100, 100).setColor(Color.CYAN)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "rectangle" + "矩形.png");
    }

    /**
     * 添加矩形
     *
     * @throws IOException
     */
    @Test
    public void addRectangleFilletTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                RectangleElement.of(10, 10, 100, 100, RectangleType.DrawRoundRect).setRoundCorner(100).setColor(Color.CYAN),
                RectangleElement.of(110, 10, 100, 100, RectangleType.FillRoundRect).setRoundCorner(100).setColor(Color.CYAN)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "rectangle" + "矩形圆角.png");
    }

    /**
     * 添加全部
     *
     * @throws IOException
     */
    @Test
    public void addRectangleAllTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, OutputFormat.PNG, 0, 0f);
        imageCombiner.addElement(
                RectangleElement.of(10, 10, 100, 100, RectangleType.DrawRect).setColor(Color.CYAN),
                RectangleElement.of(120, 10, 100, 100, RectangleType.FillRect).setColor(Color.CYAN),

                RectangleElement.of(10, 110, 100, 100, RectangleType.Draw3DRect).setColor(Color.CYAN),
                RectangleElement.of(120, 110, 100, 100, RectangleType.Fill3DRect).setColor(Color.CYAN),

                RectangleElement.of(10, 220, 100, 100, RectangleType.DrawRoundRect).setRoundCorner(100).setColor(Color.CYAN),
                RectangleElement.of(120, 220, 100, 100, RectangleType.FillRoundRect).setRoundCorner(100).setColor(Color.CYAN)


        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "rectangle" + "矩形全部.png");
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


