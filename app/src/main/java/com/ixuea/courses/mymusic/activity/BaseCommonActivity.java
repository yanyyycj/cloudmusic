package com.ixuea.courses.mymusic.activity;

import android.app.Activity;
import android.content.Intent;

public class BaseCommonActivity extends BaseActivity {

    /**
     * 启动界面
     *
     * @param clazz
     */
    protected void startActivity(Class<? extends Activity> clazz) {
        startActivity(new Intent(this, clazz));
    }

    /**
     * 启动界面并关闭当前界面
     *
     * @param clazz
     *
     *
     */
    protected void startActivityAfterFinishThis(Class<? extends Activity> clazz) {
        startActivity(clazz);
        finish();
    }

}
