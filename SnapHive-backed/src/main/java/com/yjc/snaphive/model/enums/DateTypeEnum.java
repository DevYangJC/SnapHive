package com.yjc.snaphive.model.enums;

import lombok.Getter;


/**
 * 日期类型枚举�?
 */
@Getter
public enum DateTypeEnum {
    YEAR("�?, 0),
    MONTH("�?, 1),
    DAY("�?, 2);


    private final String text;
    private final int value;


    DateTypeEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }


    /**
     * 根据值获取对应的枚举实例
     *
     * @param value 要查找的枚举�?
     * @return 对应的枚举实例，如果没找到则返回 null
     */
    public static DateTypeEnum getEnumByValue(int value) {
        for (DateTypeEnum dateTypeEnum : DateTypeEnum.values()) {
            if (dateTypeEnum.value == value) {
                return dateTypeEnum;
            }
        }
        return null;
    }
}
