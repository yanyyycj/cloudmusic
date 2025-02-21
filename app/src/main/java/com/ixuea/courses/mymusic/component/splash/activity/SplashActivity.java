package com.ixuea.courses.mymusic.component.splash.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.activity.BaseViewModelActivity;
import com.ixuea.courses.mymusic.component.guide.activity.GuideActivity;
import com.ixuea.courses.mymusic.component.splash.fragment.TermServiceDialogFragment;
import com.ixuea.courses.mymusic.databinding.ActivitySplashBinding;
import com.ixuea.courses.mymusic.util.DefaultPreferenceUtil;
import com.ixuea.courses.mymusic.util.SuperDarkUtil;
import com.ixuea.courses.mymusic.util.SuperDateUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;


//声明当前界面有动态获取权限逻辑
public class SplashActivity extends BaseViewModelActivity<ActivitySplashBinding> {

    private static final String TAG = "SplashActivity ";
    private TextView cp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @Override
    protected void initViews() {
        super.initViews();
        //设置沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        //状态栏文字黑色
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        if (SuperDarkUtil.isDark(this)) {
            //状态栏文字白色
            QMUIStatusBarHelper.setStatusBarDarkMode(this);
        } else {
            //状态栏文字黑色
            QMUIStatusBarHelper.setStatusBarLightMode(this);
        }
        cp = findViewById(R.id.copyright);
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        //设置版本控制年份
        int year = SuperDateUtils.currentYear();


        cp.setText(getResources().getString(R.string.copyright, year));
        // binding.copyright.setText(getResources().getString(R.string.copyright, year));
        if (DefaultPreferenceUtil.getInstance(getHostActivity()).isAcceptTermsServiceAgreement()) {
            //同意了协议
            prepareNext();

        } else {
            showTermsServiceAgreementDialog();
        }
    }

    private void showTermsServiceAgreementDialog() {
        TermServiceDialogFragment.show(getSupportFragmentManager(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DefaultPreferenceUtil.getInstance(getHostActivity()).setAcceptTermsServiceAgreement();
                prepareNext();
            }
        });
    }

    private void prepareNext() {
        Log.d(TAG, "perpareNext: ");


        startActivityAfterThis(GuideActivity.class);
    }


}