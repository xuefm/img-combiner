package io.github.xuefm;

import io.github.xuefm.combiner.DefaultImageCombiner;
import io.github.xuefm.combiner.ImageCombiner;
import io.github.xuefm.element.ImageElement;
import io.github.xuefm.element.LineElement;
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
import java.time.LocalTime;

public class ImgTest {

    private static String filePath = "file:///" + System.getProperty("user.dir") + "/test_file/";
    private static String generateFilePath = System.getProperty("user.dir") + "/test_file/generate/";
    private static OutputFormat outputFormat = OutputFormat.PNG;


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
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, outputFormat, 0, 0f);
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
        imageCombiner.save(generateFilePath + "图片01" + "_t01ImgTest." + outputFormat.getName());
    }

    /**
     * 图片测试(主要测试纵向对齐)
     *
     * @throws IOException
     */
    @Test
    public void t02ImgTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, outputFormat, 0, 0f);
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
        imageCombiner.save(generateFilePath + "图片02" + "_t02ImgTest." + outputFormat.getName());
    }

    /**
     * 矩形测试(主要横向试纵向对齐)
     *
     * @throws IOException
     */
    @Test
    public void t01RectangleTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, outputFormat, 0, 0f);
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
        imageCombiner.save(generateFilePath + "矩形01" + "_t02RectangleTest." + outputFormat.getName());
    }

    /**
     * 矩形测试(主要横向试纵向对齐)
     *
     * @throws IOException
     */
    @Test
    public void t02RectangleTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, outputFormat, 0, 0f);
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
        imageCombiner.save(generateFilePath + "矩形02" + "_t02RectangleTest." + outputFormat.getName());
    }

    @Test
    public void t01TextTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, outputFormat, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("汉字文本：你好世界", 0, 0).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.CYAN)
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3),
                TextElement.of("English text:Hello world", 0, 200).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.GREEN)
                        .setLineFeed(LineWrapType.BY_PIXEL, 100)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "文本01" + "_t01TextTest." + outputFormat.getName());
    }

    @Test
    public void t02TextTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, outputFormat, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("汉字文本：你好世界", AlignType.TransverseAlign.CENTER, 0).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.CYAN)
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3),
                TextElement.of("English text:Hello world", AlignType.TransverseAlign.CENTER, 200).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.GREEN)
                        .setLineFeed(LineWrapType.BY_PIXEL, 100)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "文本02" + "_t02TextTest." + outputFormat.getName());
    }

    @Test
    public void t03TextTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, outputFormat, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("汉字文本：你好世界", 0, AlignType.VerticalAlign.TOP).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.CYAN)
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3),
                TextElement.of("English text:Hello world", 0, AlignType.VerticalAlign.BOTTOM).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.GREEN)
                        .setLineFeed(LineWrapType.BY_PIXEL, 100)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "文本03" + "_t03TextTest." + outputFormat.getName());
    }

    @Test
    public void t01lineTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 700, outputFormat, 0, 0f);

        imageCombiner.addElement(
                LineElement.of(200, 300, 200, 400)
                        .setColor(Color.RED)
        );
        imageCombiner.addElement(
                LineElement.of(200, 300, 200, 400)
                        .setColor(Color.RED)
                        .setRotate(90)
        );

        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "线01" + "_t01lineTest." + outputFormat.getName());
    }

    @Test
    public void t01MixingTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, outputFormat, 0, 0f);
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
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3)
                        .setStrikethrough(true)
                ,
                TextElement.of("汉字文本：你好世界", 0, 0).setFont(new Font("宋体", Font.PLAIN, 32))
                        .setAlpha(1f)
                        .setColor(Color.CYAN)
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3)
                        .setUnderline(true)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "混合01" + "_t01MixingTest." + outputFormat.getName());
    }

    @Test
    public void clockTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 700, outputFormat, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("你好啊", AlignType.TransverseAlign.CENTER, 100)
                        .setColor(new Color(255, 255, 255))
                        .setFont(new Font("宋体", Font.PLAIN, 72))
                        .setAlpha(0.35f)
        );
        //表盘
        imageCombiner.addElement(
                RectangleElement.of(AlignType.TransverseAlign.CENTER, AlignType.VerticalAlign.CENTER, 200, 200)
                        .setColor(new Color(30, 31, 34))
                        .setRoundCorner(200)
        );
        //时间刻度
        for (int i = 0; i < 12; i++) {
            imageCombiner.addElement(
                    LineElement.of(200, 251, 200, (i % 3 == 0 ? 270 : 260))
                            .setColor(Color.WHITE)
                            .setRotate(30 * i, 200, 350)
            );
        }
        //时针、分针、秒针
        LocalTime localTime = LocalTime.now();
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
        double hourAngle = (hour % 12 + minute / 60.0 + second / 3600.0) * 30;
        double minuteAngle = (minute + second / 60.0) * 6;
        double secondAngle = second * 6;
        imageCombiner.addElement(
                LineElement.of(200, 330, 200, 350)
                        .setColor(Color.WHITE)
                        .setRotate((int) hourAngle, 200, 350)
        );
        imageCombiner.addElement(
                LineElement.of(200, 310, 200, 350)
                        .setColor(Color.WHITE)
                        .setRotate((int) minuteAngle, 200, 350)
        );
        imageCombiner.addElement(
                LineElement.of(200, 300, 200, 350)
                        .setColor(Color.RED)
                        .setRotate((int) secondAngle, 200, 350)
        );
        imageCombiner.addElement(
                TextElement.of("你好啊", AlignType.TransverseAlign.CENTER, 600)
                        .setColor(new Color(255, 255, 255))
                        .setFont(new Font("宋体", Font.PLAIN, 72))
                        .setAlpha(0.35f)
        );

        imageCombiner.addElement(
                LineElement.of(200, 500, 200, 600)
                        .setColor(Color.RED)
        );
        imageCombiner.addElement(
                LineElement.of(200, 500, 200, 600)
                        .setColor(Color.RED)
                        .setRotate(90)
        );

        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "时钟02" + "_clockTest." + outputFormat.getName());

    }
}
