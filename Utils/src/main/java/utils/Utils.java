/*
package utils;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

*/
/**
 * Created by momo on 2016/6/1.
 *//*

public class Utils {

    public static int PAGE_SIZE_DEFAULT = 20;
    public static int PAGE_SIZE_MAX = 50;

    public static Integer Long2Integer(Long num) {
        return new Long(num).intValue();
    }

    public static int long2in(long num) {
        return new Long(num).intValue();
    }

    */
/**
     * 翻页页尺寸限制
     *//*

    public static Integer reqPageSize(Integer size) {
        return size == null ? PAGE_SIZE_DEFAULT : (size <= 0 ? PAGE_SIZE_DEFAULT : (size > PAGE_SIZE_MAX ? PAGE_SIZE_DEFAULT : size));
    }

    public static Integer reqInt(Integer val, Integer def) {
        return val == null ? def : val;
    }

    public static Double reqDoubleFromString(String val) {
        return NumericUtil.isDouble(val) ? Double.parseDouble(val) : null;
    }

    public static Integer reqIntegerFromString(String val, Integer def) {
        return NumericUtil.isInteger(val) ? NumericUtil.parseInt(val) : def;
    }

    public static Boolean isNotBlankTrimed(String val) {
        return (StringUtils.isNotBlank(val) && StringUtils.isNotBlank(val.trim()));
    }

    public static Boolean isBlankDouble(String val) {
        return !(val != null && !val.equals("") && !NumericUtil.isDouble(val));
    }

    public static Boolean isBlankInteger(String val) {
        return !(val != null && !val.equals("") && !NumericUtil.isInteger(val));
    }

    public static Boolean isChinese(String val) {
        return Pattern.compile("[\u4e00-\u9fa5]").matcher(val).find();
    }

    //TODO:
    public static double toDouble(String val) {
        if (val == null) return 0;
        return Double.parseDouble(val);
    }

    //TODO:
    public static int toInt(String val) {
        if (val == null) return 0;
        return Integer.parseInt(val);
    }

}
*/
