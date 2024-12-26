package com.blog.utils;

import java.util.List;

import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import com.github.houbb.sensitive.word.support.result.WordResultHandlers;

public class WordDetectUtils {
    // 判断字符串中是否包含中文
    public static boolean containsChinese(String str) {
        return str.matches(".*[\u4e00-\u9fa5].*");
    }

    // 判断字符串中是否仅含有英文、数字、下滑线
    public static boolean containsOnlyEnglishAndNumberAndUnderline(String str) {
        return !str.matches("^[a-zA-Z0-9_]+$");
    }

    // 判断字符串中是否仅含有英文、数字
    public static boolean containsOnlyEnglishAndNumber(String str) {
        return !str.matches("^[a-zA-Z0-9]+$");
    }

    // 判断是否是纯数字
    public static boolean isNumeric(String str) {
        return str != null && str.matches("^[0-9]+$");
    }

    // 对邮箱进行格式判断
    public static boolean isEmail(String email) {
        return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    }

    //检测字符串中是否有敏感词并抛出异常
    public static void checkSensitiveWord(String str){
        if(SensitiveWordHelper.contains(str)){
            throw new RuntimeException("您输入的内容包含敏感词: \""+SensitiveWordHelper.findFirst(str,WordResultHandlers.word())+"\" ......");
        }
    }

    //检测是否有敏感词
    public static boolean hasSensitiveWord(String str) {
        return SensitiveWordHelper.contains(str);
    }

    //返回所有敏感词
    public static List<String> getAllSensitiveWord(String str) {
        return SensitiveWordHelper.findAll(str, WordResultHandlers.word());
    }

    //返回第一个敏感词
    public static String getFirstSensitiveWord(String str) {
        return SensitiveWordHelper.findFirst(str, WordResultHandlers.word());
    }

    //返回敏感词的个数
    public static int getSensitiveWordCount(String str) {
        return SensitiveWordHelper.findAll(str, WordResultHandlers.word()).size();
    }

    //默认敏感词替换
    public static String replaceSensitiveWord(String str) {
        return SensitiveWordHelper.replace(str);
    }

    //自定义敏感词替换
    public static String replaceSensitiveWord(String str, char replace) {
        return SensitiveWordHelper.replace(str, replace);
    }
}
