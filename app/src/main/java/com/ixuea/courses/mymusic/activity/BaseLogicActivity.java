package com.ixuea.courses.mymusic.activity;

import com.ixuea.courses.mymusic.util.PreferenceUtil;

public class BaseLogicActivity extends BaseCommonActivity {

    protected PreferenceUtil sp;

    @Override
    protected void initDatum() {
        super.initDatum();
        sp = PreferenceUtil.getInstance(getHostActivity());
    }

    /**
     * 获取界面方法
     *
     * @return
     */
    protected BaseLogicActivity getHostActivity() {
        return this;
    }


}
