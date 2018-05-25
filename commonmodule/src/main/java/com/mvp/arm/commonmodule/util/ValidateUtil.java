package com.mvp.arm.commonmodule.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 *
 * @author Gjm
 * @date 2018/2/24
 */
public class ValidateUtil {
    /**
     * 判断是否为手机号
     */
    public static boolean isMobileNo(String input){
        return validByRegex("^(1[3,4,5,7,8,9])\\d{9}$",input);
    }

    /**
     * 判断是否为6-20位密码
     */
    public static boolean isValidPwd(String input){
        return validByRegex("^\\S{6,20}$",input);
    }

    /**
     * 判断是否是Email
     */
    public static boolean isValidEmail(String input) {
        return validByRegex("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*",input);
    }

    public static boolean validByRegex(String regex, String input) {
        try{
            Pattern p = Pattern.compile(regex);
            Matcher regexMatcher = p.matcher(input);
            return regexMatcher.find();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        return false;
    }
}
