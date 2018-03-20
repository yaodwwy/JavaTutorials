package cn.adbyte.springboot.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author AdamYao
 */
public class ValidateUtils {
    private ValidateUtils() {
    }

    enum Type {
        //------------------    正则定义区    ------------------//
        IS_LINK("^http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?$"),
        IS_EMAIL("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$"),
        IS_IDCARD("^\\d{17}[\\d|X]|\\d{15}$"),//身份证
        IS_POST("^\\d{6}$"),//邮政编码
        IS_NUMBER("^([+-]?)\\d*\\.?\\d+$"),//数字
        IS_CHAR("^[A-Za-z]+$"),//字母
        IS_UPPER_CHAR("^[A-Za-z]+$"),//大写字母
        IS_LOWWER_CHAR("^[a-z]+$"),//小写字母
        IS_NUMBER_OR_CHAR("^[A-Za-z0-9]+$"),//字母和数字
        IS_MONTH("^(0?[1-9]|1[0-2])$"),//一年的12个月
        IS_DATE_OF_MONTH("^((0?[1-9])|((1|2)[0-9])|30|31)$"),//一个月的31天
        IS_DOUBLE("^([+-]?)\\d*\\.\\d+$"),//浮点数
        IS_DOUBLE_POSITIVE("^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$"),//正浮点数
        IS_DOUBLE_NEGATIVE("^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$"),//负浮点数
        NOT_DOUBLE_NEGATIVE("^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$"),//非负浮点数
        NOT_DOUBLE_POSITIVE("^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$"),//非正浮点数
        IS_INTEGER("^-?[1-9]\\d*$"),//整数
        IS_INTEGER_POSITIVE("^[1-9]\\d*$"),//正整数
        IS_INTEGER_NEGATIVE("^-[1-9]\\d*$"),//负整数
        IS_NUMBER_POSITIVE("^[1-9]\\d*|0$"),//正数
        IS_NUMBER_NEGATIVE("^-[1-9]\\d*|0$"),//负数
        IS_ACSII("^[\\x00-\\xFF]+$"),//ACSII字符
        IS_CHINESE("^[\\u4e00-\\u9fa5]+$"),//中文
        IS_COLOR("^[a-fA-F0-9]{6}$"),//颜色
        IS_DATE("^\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2}$"),//日期
        IS_IP("(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$"),//IP
        IS_MOBILE("^[0]?(13|15|16|17|18|14)[0-9]{9}$"),//手机
        NOT_NULL("^\\S+$"),//非空
        IS_PASSWORD("^[A-Za-z0-9_-]+$"),//密码
        IS_IMAGE("(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$"),//图片
        IS_QQ("^[1-9]*[1-9][0-9]*$"),//QQ号
        IS_ZIP_FILE("(.*)\\.(rar|zip|7zip|tgz)$"),//压缩文件
        IS_TELEPHONE("^[0-9\\-()（）]{7,18}$"),//电话号码
        IS_USERNAME("^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$"),//电话号码
        IS_COMPANY_NAME("^[A-Za-z0-9\\u4e00-\\u9fa5]+$"),//真实姓名
        ;

        public String des;

        Type(String des) {
            this.des = des;
        }
    }

    //------------------    验证方法区        ----------------//
    //判断字段是否为空 符合返回ture
    private static synchronized boolean StrisNull(String str) {
        return null == str || str.trim().length() <= 0;
    }

    //匹配是否符合正则表达式pattern 匹配返回true
    public static boolean check(Type pattern, String str) {
        if (null == str || str.trim().length() <= 0) {
            return false;
        }
        Pattern p = Pattern.compile(pattern.des);
        Matcher m = p.matcher(str);
        return m.matches();
    }



    public static void main(String[] args) {
//        Instant instant=new Date().toInstant();
//        Date date= Date.from(instant);
//        System.out.println(instant);
//        System.out.println(date);
        System.out.println(ValidateUtils.check(Type.IS_CHAR,"whitney.wang@nexteer.com"));
    }
}