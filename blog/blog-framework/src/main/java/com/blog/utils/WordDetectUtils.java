package com.blog.utils;

public class WordDetectUtils {
    // 判断字符串中是否包含中文
    public static boolean containsChinese(String str) {
        return str != null && str.matches(".*[\u4e00-\u9fa5].*");
    }

    // 判断字符串中是否仅含有英文、数字、下滑线
    public static boolean containsOnlyEnglishAndNumberAndUnderline(String str) {
        return str != null && str.matches("^[a-zA-Z0-9_]+$");
    }

    // 判断字符串中是否仅含有英文、数字
    public static boolean containsOnlyEnglishAndNumber(String str) {
        return str != null && str.matches("^[a-zA-Z0-9]+$");
    }

    // 判断是否是纯数字
    public static boolean isNumeric(String str) {
        return str != null && str.matches("^[0-9]+$");
    }

    // 对邮箱进行格式判断
    public static boolean isEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    }
}
