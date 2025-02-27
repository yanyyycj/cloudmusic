package com.ixuea.courses.mymusic;


import com.ixuea.courses.mymusic.activity.BaseViewModelActivity;
import com.ixuea.courses.mymusic.component.login.activity.LoginHomeActivity;
import com.ixuea.courses.mymusic.databinding.ActivityMainBinding;
import com.ixuea.courses.mymusic.util.Constant;


public class MainActivity extends BaseViewModelActivity<ActivityMainBinding> {
    @Override
    protected void initDatum() {
        super.initDatum();
        String action = getIntent().getAction();
        if (action == Constant.ACTION_LOGIN) {
            startActivity(LoginHomeActivity.class);
        }

    }
}