package io.github.xuefm;

import io.github.xuefm.combiner.DefaultImageCombiner;
import io.github.xuefm.combiner.ImageCombiner;
import io.github.xuefm.element.*;
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

    @Test
    public void canvasRoundCorner() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 700, outputFormat, 100, 0f);

        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "画布圆角01." + outputFormat.getName());

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
                ImageElement.of(filePath + "cat.jpg", null, null, AlignType.TransverseAlign.LEFT, null, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(45)
                        .setAlpha(0.25f),
                ImageElement.of(filePath + "cat.jpg", null, null, AlignType.TransverseAlign.CENTER, null, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(90)
                        .setAlpha(0.5f),
                ImageElement.of(filePath + "cat.jpg", null, null, AlignType.TransverseAlign.RIGHT, null, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(180)
                        .setAlpha(0.75f)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "图片01." + outputFormat.getName());
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
                ImageElement.of(filePath + "cat.jpg", null, null, null, AlignType.VerticalAlign.TOP, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(45)
                        .setAlpha(0.25f),
                ImageElement.of(filePath + "cat.jpg", null, null, null, AlignType.VerticalAlign.CENTER, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(90)
                        .setAlpha(0.5f),
                ImageElement.of(filePath + "cat.jpg", null, null, null, AlignType.VerticalAlign.BOTTOM, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(180)
                        .setAlpha(0.75f)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "图片02." + outputFormat.getName());
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
                RectangleElement.of(null, null, AlignType.TransverseAlign.LEFT, null, 0, 100)
                        .setRoundCorner(50)
                        .setRotate(30)
                        .setAlpha(0.25f)
                        .setColor(Color.RED)
                ,
                RectangleElement.of(null, null, AlignType.TransverseAlign.CENTER, null, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(60)
                        .setAlpha(0.5f)
                        .setColor(Color.GREEN),
                RectangleElement.of(null, null, AlignType.TransverseAlign.RIGHT, null, 0, 100)
                        .setRoundCorner(50)
                        .setRotate(90)
                        .setAlpha(0.75f)
                        .setColor(Color.BLUE)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "矩形01." + outputFormat.getName());
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
                RectangleElement.of(0, null, null, AlignType.VerticalAlign.TOP, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(30)
                        .setAlpha(0.25f)
                        .setColor(Color.RED),
                RectangleElement.of(0, null, null, AlignType.VerticalAlign.CENTER, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(60)
                        .setAlpha(0.5f)
                        .setColor(Color.GREEN),
                RectangleElement.of(0, null, null, AlignType.VerticalAlign.BOTTOM, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(90)
                        .setAlpha(0.75f)
                        .setColor(Color.BLUE)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "矩形02." + outputFormat.getName());
    }

    @Test
    public void t01TextTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, outputFormat, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("汉字文本：你好世界", 0, 0, null, null).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.CYAN)
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3),
                TextElement.of("English text:Hello world", 0, 200, null, null).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.GREEN)
                        .setLineFeed(LineWrapType.BY_PIXEL, 100)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "文本01." + outputFormat.getName());
    }

    @Test
    public void t02TextTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, outputFormat, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("汉字文本：你好世界", null, 0, AlignType.TransverseAlign.CENTER, null).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.CYAN)
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3),
                TextElement.of("English text:Hello world", null, 200, AlignType.TransverseAlign.CENTER, null).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.GREEN)
                        .setLineFeed(LineWrapType.BY_PIXEL, 100)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "文本02." + outputFormat.getName());
    }

    @Test
    public void t03TextTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, outputFormat, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("汉字文本：你好世界", 0, null, null, AlignType.VerticalAlign.TOP).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.CYAN)
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3),
                TextElement.of("English text:Hello world", 0, null, null, AlignType.VerticalAlign.BOTTOM).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.GREEN)
                        .setLineFeed(LineWrapType.BY_PIXEL, 100)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "文本03." + outputFormat.getName());
    }

    @Test
    public void t04TextTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, outputFormat, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("汉字文本：你好世界English text:Hello world", 0, 0, null, null).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.CYAN)
                        .setGradient(new Color[]{Color.RED, Color.GREEN, Color.BLUE},
                                new float[]{0.0f, 0.1f, 0.5f}),
                TextElement.of("汉字文本：你好世界English text:Hello world", 0, 200, null, null).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setColor(Color.CYAN)


        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "文本04." + outputFormat.getName());
    }

    @Test
    public void t05TextTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, outputFormat, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("静夜思\n" +
                                "床前明月光，\n" +
                                "疑是地上霜。\n" +
                                "举头望明月，\n" +
                                "低头思故乡。", 0, null, null, AlignType.VerticalAlign.TOP).setFont(new Font("微软雅黑", Font.PLAIN, 36))
                        .setGradient(new Color[]{Color.RED, Color.GREEN, Color.BLUE},
                                new float[]{0.0f, 0.1f, 0.5f})
                        .setLineFeed(LineWrapType.BY_LINE_BREAKS, 0)

        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "文本05." + outputFormat.getName());
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
        imageCombiner.save(generateFilePath + "线01." + outputFormat.getName());
    }

    @Test
    public void t02lineTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 700, outputFormat, 0, 0f);
        imageCombiner.addElement(
                LineElement.of(100, 100, 100, 100, 200, 200, null, null),
                LineElement.of(200, 200, 200, 300)

        );

        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "线02." + outputFormat.getName());
    }


    @Test
    public void oval1() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 300, outputFormat, 0, 0f);
        imageCombiner.addElement(OvalElement.of(0, 0, AlignType.TransverseAlign.CENTER, AlignType.VerticalAlign.CENTER, 200, 100)
                .setColor(Color.RED));

        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "椭圆01." + outputFormat.getName());

    }

    @Test
    public void t01MixingTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 600, outputFormat, 0, 0f);
        imageCombiner.addElement(

                ImageElement.of(filePath + "cat.jpg", null, null, AlignType.TransverseAlign.CENTER, AlignType.VerticalAlign.TOP, 100, 100)
                        .setRoundCorner(50)
                        .setRotate(45),
                RectangleElement.of(null, null, AlignType.TransverseAlign.CENTER, AlignType.VerticalAlign.CENTER, 100, 100)
                        .setRoundCorner(50)
                        .setColor(Color.RED),
                TextElement.of("汉字文本：你好世界", null, null, AlignType.TransverseAlign.CENTER, AlignType.VerticalAlign.BOTTOM).setFont(new Font("微软雅黑", Font.PLAIN, 32))
                        .setAlpha(1f)
                        .setColor(Color.CYAN)
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3)
                        .setStrikethrough(true)
                ,
                TextElement.of("汉字文本：你好世界", 0, 0, null, null).setFont(new Font("宋体", Font.PLAIN, 32))
                        .setAlpha(1f)
                        .setColor(Color.CYAN)
                        .setLineFeed(LineWrapType.BY_TEXT_COUNT, 3)
                        .setUnderline(true)
        );
        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "混合01." + outputFormat.getName());
    }

    @Test
    public void clockTest() throws IOException {
        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 700, outputFormat, 0, 0f);
        imageCombiner.addElement(
                TextElement.of("你好啊", null, 100, AlignType.TransverseAlign.CENTER, null)
                        .setColor(new Color(0, 255, 255))
                        .setFont(new Font("宋体", Font.PLAIN, 72))
                        .setAlpha(0.35f)
        );
        //表盘
        imageCombiner.addElement(
                RectangleElement.of(null, null, AlignType.TransverseAlign.CENTER, AlignType.VerticalAlign.CENTER, 200, 200)
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
                TextElement.of("你好啊", null, 600, AlignType.TransverseAlign.CENTER, null)
                        .setColor(new Color(255, 0, 255))
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
        imageCombiner.save(generateFilePath + "时钟02." + outputFormat.getName());

    }

    @Test
    public void t() throws IOException {
        String text = """
                静夜思
                床前明月光，疑是地上霜。
                举头望明月，低头思故乡。
                """;
        ImageCombiner imageCombiner = DefaultImageCombiner.of(800, 700, outputFormat, 0, 0f);
        imageCombiner.addElement(TextElement.of(text, -100, -100, AlignType.TransverseAlign.CENTER, AlignType.VerticalAlign.CENTER)
                .setColor(Color.decode("#6967bb"))
                .setUnderline(true)
                .setLineFeed(LineWrapType.BY_LINE_BREAKS, 2)
                .setGradient(new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.RED, Color.GREEN, Color.BLUE, Color.RED, Color.GREEN, Color.BLUE},
                        new float[]{0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 1.0f})
        );

        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "静夜思." + outputFormat.getName());

    }

    @Test
    public void t2312() throws IOException {

        ImageCombiner imageCombiner = DefaultImageCombiner.of(400, 700, outputFormat, 0, 0f);
        imageCombiner.addElement(
                LineElement.of(300, 100, 100, 300)
                        .setTransverseAlign(AlignType.TransverseAlign.RIGHT)
                        .setVerticalAlign(AlignType.VerticalAlign.BOTTOM)
                        .setColor(Color.RED),
                LineElement.of(300, 100, 100, 300)
                        .setX(-100)
                        .setTransverseAlign(AlignType.TransverseAlign.RIGHT)
                        .setVerticalAlign(AlignType.VerticalAlign.BOTTOM)
                        .setColor(Color.BLUE),
                LineElement.of(300, 100, 100, 300)
                        .setX(-100).setY(-100)
                        .setTransverseAlign(AlignType.TransverseAlign.RIGHT)
                        .setVerticalAlign(AlignType.VerticalAlign.BOTTOM)
                        .setColor(Color.BLACK)

        );


        imageCombiner.generate();
        imageCombiner.save(generateFilePath + "线测试." + outputFormat.getName());

    }


}
