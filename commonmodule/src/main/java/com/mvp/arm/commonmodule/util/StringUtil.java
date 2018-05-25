package com.mvp.arm.commonmodule.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

/**
 * 字符串工具类
 *
 * @author Gjm
 * @date 2018/5/7
 */
public class StringUtil {
    /**
     * 判断集合是否为空
     */
    public static boolean isListEmpty(List list){
        return list == null || list.size() == 0;
    }

    /**
     * 返回集合的大小
     */
    public static int getListSize(List list){
        return list == null ? 0 : list.size();
    }

    /**
     * 保留一位小数
     */
    public static String getDecimalFormatDot(double value){
        return new DecimalFormat("#0.0").format(value);
    }

    /**
     * 保留两位小数
     */
    public static String getDecimalFormat(double value) {
        return new DecimalFormat("#0.00").format(value);
    }

    /**
     * 最多保留两位小数
     */
    public static String getDecimalFormatAtMost(double value){
        return new DecimalFormat("#.##").format(value);
    }

    /**
     * 保留两位小数（向上取）
     */
    public static String getDecimalFormatUp(double value){
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(value);
    }

    /**
     * 保留两位小数（向下取）
     */
    public static String getDecimalFormatDown(double value){
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setRoundingMode(RoundingMode.FLOOR);
        return df.format(value);
    }
}
