package com.ixuea.courses.mymusic.util;

import java.util.Calendar;

public class SuperDateUtils {

    public static int currentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 当前天
     *
     * @return
     */
    public static int currentDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
}
