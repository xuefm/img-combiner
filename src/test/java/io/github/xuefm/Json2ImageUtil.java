package io.github.xuefm;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.github.xuefm.combiner.DefaultImageCombiner;
import io.github.xuefm.combiner.ImageCombiner;
import io.github.xuefm.element.ImageElement;
import io.github.xuefm.element.TextElement;
import io.github.xuefm.enums.LineWrapType;
import io.github.xuefm.enums.OutputFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Json2ImageUtil {

    public static void json2Image(String json, String file) throws IOException {


        JSONObject jsonObject = JSONUtil.parseObj(json);
        CanvasData canvasData = BeanUtil.copyProperties(jsonObject, CanvasData.class, "elementDataList");
        List<JSONObject> elementDataJsonList = jsonObject.getJSONArray("elementDataList").toList(JSONObject.class);
        List<ElementData> elementDataList = new ArrayList<>();
        for (JSONObject elementDataJson : elementDataJsonList) {
            switch (elementDataJson.getInt("type")) {
                case 0: {
                    ImageElementData bean = elementDataJson.toBean(ImageElementData.class);
                    elementDataList.add(bean);
                    break;
                }
                case 1: {
                    TextElementData bean = elementDataJson.toBean(TextElementData.class);
                    elementDataList.add(bean);
                    break;
                }
                default: {

                }
            }
        }
        Color color = null;
        if (StrUtil.isNotBlank(canvasData.getBackgroundColor())) {
            color = Color.decode(canvasData.getBackgroundColor());
        }
        canvasData.setElementDataList(elementDataList);
        ImageCombiner imageCombiner = DefaultImageCombiner.of(canvasData.getCanvasWidth(),
                canvasData.getCanvasHeight(),
                color,
                OutputFormat.PNG,
                canvasData.getRoundCorner(),
                canvasData.getQuality()
        );

        for (ElementData elementData : elementDataList) {
            if (elementData instanceof ImageElementData data) {
                imageCombiner.addElement(imageElementData2ImageElement(data));
            } else if (elementData instanceof TextElementData data) {
                imageCombiner.addElement(textElementData2TextElement(data));
            }
        }
        imageCombiner.generate();
        imageCombiner.save(file);
        System.out.println(JSONUtil.toJsonPrettyStr(canvasData));
    }

    private static ImageElement imageElementData2ImageElement(ImageElementData data) {
        ImageElement imageElement = ImageElement.of(data.getImgUrl(), 0, 0, null, null);
        if (Objects.nonNull(data.getWidth()) && Objects.nonNull(data.getHeight())) {
            imageElement.setWidthAndHeight(data.getWidth(), data.getHeight());
        }
        if (Objects.nonNull(data.getRoundCorner())) {
            imageElement.setRoundCorner(data.getRoundCorner());
        }
        if (Objects.nonNull(data.getRotate())) {
            imageElement.setRotate(data.getRotate());
        }
        if (Objects.nonNull(data.getAlpha())) {
            imageElement.setAlpha(data.getAlpha());
        }
        return imageElement;
    }

    private static TextElement textElementData2TextElement(TextElementData data) {
        TextElement textElement = TextElement.of(data.getText(), 100, 100, null, null);
        textElement.setFont(new Font(data.getFontName(),
                (data.getBold() ? Font.BOLD : Font.PLAIN)
                        | (data.getItalic() ? Font.BOLD : Font.PLAIN)
                , data.getFontSize()));
        if (StrUtil.isNotBlank(data.getColor())) {
            textElement.setColor(Color.decode(data.getColor()));
        }
        if (Objects.nonNull(data.getLineWrapType())) {
            switch (data.getLineWrapType()) {
                case 0 -> textElement.setLineFeed(LineWrapType.NO_LINE_BREAKS, 0);
                case 1 -> textElement.setLineFeed(LineWrapType.BY_TEXT_COUNT, data.getLineMax());
                case 2 -> textElement.setLineFeed(LineWrapType.BY_PIXEL, data.getLineMax());
            }
        }
        if (Objects.nonNull(data.getRotate())) {
            textElement.setRotate(data.getRotate());
        }
        if (Objects.nonNull(data.getAlpha())) {
            textElement.setAlpha(data.getAlpha());
        }
        return textElement;
    }

    public static void main(String[] args) throws IOException {
        String json = """
                {
                "canvasWidth":400,
                "canvasHeight":300,
                "backgroundColor":"#ffffff",
                "roundCorner":50,
                "quality":1,
                "elementDataList":[
                    {
                        "type":0,
                        "index":1,
                        "alpha":"0.5",
                        "rotate":"45",
                        
                        "imgUrl":"https://zk-micro.oss-cn-hangzhou.aliyuncs.com/expect-cat.jpg",
                        "width":"200",
                        "height":"200",
                        "roundCorner":"100",
                        
                    },
                    {
                        "type":1,
                        "index":2,
                        "alpha":"0.5",
                        "rotate":"45",
                        
                        "text":"你好啊",
                        "color":"#fb4a3e",
                        
                    }
                ]
                }
                """;

        String file = "F:\\temp\\ni.png";
        json2Image(json, file);
    }

    @Data
    public static class CanvasData {
        /**
         * 画布宽度
         */
        private int canvasWidth;

        /**
         * 画布高度
         */
        private int canvasHeight;

        /**
         * 画布背景色(默认白色，当值为为null时为透明背景)
         */
        private String backgroundColor;

        /**
         * 输出图片格式
         */
        private String outputFormat;

        /**
         * 画布圆角（针对整图）
         */
        private Integer roundCorner;

        /**
         * 图片保存质量(取值0~1) 数值越大则生成的图片文件越大、越清晰
         */
        private Float quality = 1f;


        private List<ElementData> elementDataList;
    }

    @Data
    public static abstract class ElementData {

        /**
         * 类型  0:图片 1:文字 2:矩形 3:线
         */
        private String type;

        /**
         * 绘制顺序
         */
        private Integer index;

        /**
         * 透明度，取值范围为0~1，值越小越透明
         */
        private Float alpha = 1f;

        /**
         * 旋转角度
         */
        private Integer rotate;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ImageElementData extends ElementData {

        /**
         * 图片地址
         */
        private String imgUrl;

        /**
         * 绘制宽度
         */
        private Integer width;

        /**
         * 绘制高度
         */
        private Integer height;

        /**
         * 圆角大小
         */
        private Integer roundCorner;

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class TextElementData extends ElementData {

        /**
         * text
         */
        private String text;


        /**
         * 字体名
         */
        private String fontName;

        /**
         * 字体大小
         */
        private Integer fontSize = 24;

        /**
         * 是否加粗
         */
        private Boolean bold = false;

        /**
         * 是否斜体
         */
        private Boolean italic = false;

        /**
         * 是否使用下划线(默认不使用)
         */
        private Boolean underline = false;

        /**
         * 是否使用删除线(默认不使用)
         */
        private Boolean strikethrough = false;


        /**
         * 颜色，默认黑色
         */
        private String color;

        /**
         * 自动换行类型 默认不自动换行
         * 0 NO_LINE_BREAKS 不自动换行
         * 1 BY_TEXT_COUNT 按文本字数换行
         * 2 BY_PIXEL  按像素换行
         */
        private Integer lineWrapType = 0;

        /**
         * 每行最大容量(自动换行)
         */
        private Integer lineMax;


    }
}
