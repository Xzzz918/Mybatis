package com.gemini.utils;

import java.util.UUID;

/**
 * @author gemini
 * Created in  2021/5/2 21:27
 */
//设置随机ID以免重复
public class IDUtils {
    public static String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
