package com.github.xuefm.enums;

/**
 * 输出格式
 */
public enum OutputFormat {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    BMP("bmp");

    public final String name;

    OutputFormat(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}