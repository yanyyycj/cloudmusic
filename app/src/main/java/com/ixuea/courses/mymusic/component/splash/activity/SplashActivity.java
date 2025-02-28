package com.ixuea.courses.mymusic.component.splash.activity;

import android.Manifest;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.ixuea.courses.mymusic.MainActivity;
import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.activity.BaseViewModelActivity;
import com.ixuea.courses.mymusic.component.guide.activity.GuideActivity;
import com.ixuea.courses.mymusic.component.splash.fragment.TermServiceDialogFragment;
import com.ixuea.courses.mymusic.config.Config;
import com.ixuea.courses.mymusic.databinding.ActivitySplashBinding;
import com.ixuea.courses.mymusic.util.DefaultPreferenceUtil;
import com.ixuea.courses.mymusic.util.SuperDarkUtil;
import com.ixuea.courses.mymusic.util.SuperDateUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;


//声明当前界面有动态获取权限逻辑
@RuntimePermissions
public class SplashActivity extends BaseViewModelActivity<ActivitySplashBinding> {

    private static final String TAG = "SplashActivity ";
    private TextView cp;


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
            checkPermission();

        } else {
            showTermsServiceAgreementDialog();
        }
    }

    private void showTermsServiceAgreementDialog() {
        TermServiceDialogFragment.show(getSupportFragmentManager(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DefaultPreferenceUtil.getInstance(getHostActivity()).setAcceptTermsServiceAgreement();
                checkPermission();
            }
        });
    }

    private void prepareNext() {
        Log.d(TAG, "prepareNext");
        if (sp.isShowGuide()) {
            startActivityAfterFinishThis(GuideActivity.class);
            return;
        }

        postNext();
    }

    private void postNext() {
        binding.copyright.postDelayed(new Runnable() {
            @Override
            public void run() {
                next();
            }
        }, Config.SPLASH_DEFAULT_DELAY_TIME);

    }


    private void next() {
        startActivityAfterFinishThis(MainActivity.class);
    }


    /**
     * 检查是否有需要的权限
     * <p>
     * <p>
     * 只有部分权限才需要动态授权
     * 例如：网络权限就不需要动态授权，但相机就需要动态授权
     * <p>
     * <p>
     * Google推荐在用到权限的时候才请求用户
     * 但真实项目中，如果在每个用到的位置请求权限可能比较麻烦
     * 例如：项目中有多个位置都用到了相机
     * <p>
     * <p>
     * 所以说大部分项目，像淘宝，京东等软件
     * 是在启动页请求项目所有需要的权限
     * <p>
     * <p>
     * 但如果大家的项目中有足够的时间
     * 肯定还是推荐在用到的时候再请求权限
     */
    private void checkPermission() {
        //让动态框架检查是否授权了

        //如果不使用框架就使用系统提供的API检查
        //它内部也是使用系统API检查
        //只是使用框架就更简单了
        SplashActivityPermissionsDispatcher.onPermissionGrantedWithPermissionCheck(this);
    }

    /**
     * 权限授权了就会调用该方法
     * 请求相机权限目的是扫描二维码，拍照
     */
    @NeedsPermission({
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
    void onPermissionGranted() {
        //如果有权限就进入下一步
        prepareNext();
    }

    /**
     * 显示权限授权对话框
     * 目的是提示用户
     */
    @OnShowRationale({
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
    void showRequestPermission(PermissionRequest request) {
        new AlertDialog.Builder(getHostActivity())
                .setMessage(R.string.permission_hint)
                .setPositiveButton(R.string.allow, (dialog, which) -> request.proceed())
                .setNegativeButton(R.string.deny, (dialog, which) -> request.cancel()).show();
    }

    /**
     * 拒绝了权限调用
     */
    @OnPermissionDenied({
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
    void showDenied() {
        //退出应用
        finish();
    }

    /**
     * 再次获取权限的提示
     */
    @OnNeverAskAgain({
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
    void showNeverAsk() {
        //继续请求权限
        checkPermission();
    }


    /**
     * 授权后回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //将授权结果传递到框架
        SplashActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }


}