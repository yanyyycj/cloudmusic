package com.ixuea.courses.mymusic.util;

/**
 * 字符串相关工具类
 */
public class StringUtil {
    /**
     * 格式化消息数量
     *
     * @param data
     * @return
     */
    public static String formatMessageCount(Integer data) {
        if (data > 99) {
            return "99+";
        }

        return String.valueOf(data);
    }
}