package com.ixuea.superui.util;

import android.os.Handler;
import android.os.Looper;

/**
 * 延迟工具类
 */
public class SuperDelayUtil {
    private static Handler handler = new Handler(Looper.getMainLooper());

    /**
     * 延时后执行
     *
     * @param data     毫秒，如果小于等于0，马上执行
     * @param listener
     */
    public static void delay(long data, SuperDelayListener listener) {
        if (data < 0) {
            listener.onRun();
            return;
        }
        handler.postDelayed(() -> listener.onRun(), data);
    }

    public interface SuperDelayListener {
        void onRun();
    }
}