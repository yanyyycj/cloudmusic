package com.ixuea.superui.util;

import android.view.View;

public class SuperViewUtil {

    /**
     * 显示
     *
     * @param data
     */
    public static void show(View data) {
        data.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏
     *
     * @param data
     */
    public static void gone(View data) {
        data.setVisibility(View.GONE);
    }
}
