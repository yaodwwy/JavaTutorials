/*
package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

*/
/**
 * Created by yaodw on 2016/7/20.
 *//*

public class NumericUtil {

    public static boolean isInteger(String str) {
        if (str == null || "".equals(str.trim())) {
            return false;
        }
        Pattern pattern = Pattern.compile("(\\d+)\\.?[0]*");
        Matcher isNumStr = pattern.matcher(str);
        return isNumStr.matches();
    }


    public static boolean isDouble(String str) {
        if (Utils.isNotBlankTrimed(str)) {
            Pattern pattern = Pattern.compile("(\\d*\\.)?\\d+");
            Matcher isNum = pattern.matcher(str);
            return isNum.matches();
        }else{
            return false;
        }
    }


    public static Integer parseInt(String str) {
        if(Utils.isNotBlankTrimed(str)) {
            if (str.matches("\\d*")) {
                return Integer.parseInt(str);
            } else if (str.matches("(\\d+)\\.?[0]*")) {
                String[] split = str.split("\\.");
                return Integer.parseInt(split[0]);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(parseInt("0.5.0"));
    }
}
*/
