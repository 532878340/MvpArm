package com.mvp.arm.commonmodule.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Zijin
 * @date 2017/7/27
 */

public final class DateFormatUtils {
    private DateFormatUtils() {
    }

    /**
     * 时间格式 07-27 09:23
     */
    public static final String FORMAT_SIMPLE = "MM-dd HH:mm";

    /**
     * 日期格式 2017-07-27
     */
    public static final String FORMAT_DATE = "yyyy-MM-dd";

    /**
     * 时间格式 2017-07-27 09:23:35
     */
    public static final String FORMAT_STARNDARD = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式 2017-07-27 09:23:35.389
     */
    public static final String FORMAT_COMPLETE = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 返回当前时间
     */
    public static final String getCurrentTime() {
        return formatStandardTime(System.currentTimeMillis());
    }

    /**
     * 格式化时间
     */
    public static final String formatTime(long millions, String pattern) {
        return new SimpleDateFormat(pattern).format(millions);
    }

    /**
     * 标准日期
     */
    public static final String formatStandardDate(long millions) {
        return formatTime(millions, FORMAT_DATE);
    }

    /**
     * 标准时间
     */
    public static final String formatStandardTime(long millions) {
        return formatTime(millions, FORMAT_STARNDARD);
    }

    /**
     * 时间字符串转时间戳
     */
    public static final long parseToMillions(String dateStr, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(dateStr).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 标准日期转时间戳
     */
    public static final long parseToMillionsByDate(String dateStr) {
        return parseToMillions(dateStr, FORMAT_DATE);
    }

    /**
     * 标准时间转时间戳
     */
    public static final long parsetToMillionsByTime(String timeStr) {
        return parseToMillions(timeStr, FORMAT_STARNDARD);
    }
}
