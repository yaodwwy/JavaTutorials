package utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 根据Java8更新新版时间日期处理工具
 */
public class DateTimeUtils {

    /**
     * 常量
     **/
    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE = "yyyy-MM-dd";
    public static final String YEAR_MONTH = "yyyy-MM";
    public static final String YEAR = "yyyy";
    public static final String DATETIME_SERIAL = "yyyyMMddHHmmss";
    public static final String DATETIME_HOUR = "yyyyMMddHH";
    public static final String DATE_SERIAL = "yyyyMMdd";
    public static final String YEAR_MONTH_SERIAL = "yyyyMM";
    public static final String MONTH_DATE_SERIAL = "M-d";

    /**
     * 日期到字符串
     *
     * @param dateTime
     * @param format
     * @return
     */
    public static String dateTimeToString(LocalDateTime dateTime, String format) {
        if (StringUtils.isEmpty(format)) {
            format = DATETIME;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format, Locale.SIMPLIFIED_CHINESE);
        return dateTimeFormatter.format(dateTime);
    }

    /**
     * 根据日期时间字符串获取日期时间
     *
     * @param datetime
     * @param format   为null或者空时默认为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static LocalDateTime stringToDateTime(String datetime, String format) {

        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(datetime, formatter);
    }

    /**
     * 根据时间获取时间戳
     *
     * @param dateTime
     * @return
     */
    public static Long dateTimeToTimestamp(LocalDateTime dateTime) {

        if (dateTime == null) {
            return null;
        }
        return dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }


    /**
     * 根据时间戳获取日期时间
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime timestampToDateTime(Long timestamp) {
        return timestamp == null ? null : LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8));
    }
}
