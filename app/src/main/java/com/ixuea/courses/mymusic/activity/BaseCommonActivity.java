package com.ixuea.courses.mymusic.activity;

import android.content.Intent;

import com.ixuea.courses.mymusic.component.guide.activity.GuideActivity;

public class BaseCommonActivity extends BaseActivity {

    /**
     * 启动界面并关闭当前界面
     *
     * @param clazz
     */
    protected void startActivityAfterThis(Class<GuideActivity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

}
