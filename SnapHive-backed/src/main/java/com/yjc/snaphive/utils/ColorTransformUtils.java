package com.yjc.snaphive.utils;

import java.awt.*;

/**
 * 颜色转换工具�?
 */
public class ColorTransformUtils {

    private ColorTransformUtils() {
        // 工具类不需要实例化
    }

    /**
     * 获取标准颜色（将数据万象�?5 位色值转�?6 位）
     *
     * @param color
     * @return
     */
    public static String getStandardColor(String color) {
        // 每一�?rgb 色值都有可能只有一�?0，要转换�?00)
        // 如果是六位，不用转换，如果是五位，要给第三位后面加个 0
        // 示例�?
        // 0x080e0 => 0x0800e
        // 如果�?5 位颜色值，添加一�?0 使其成为 6 �?
        if (color.length() == 6) {
            StringBuilder sb = new StringBuilder(color);
            sb.insert(3, "0");
            color = sb.toString();
        }

        return color;
    }
}
