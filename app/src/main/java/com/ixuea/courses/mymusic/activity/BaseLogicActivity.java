package com.ixuea.courses.mymusic.activity;

import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.util.PreferenceUtil;
import com.ixuea.superui.loading.SuperRoundLoadingDialogFragment;

import java.lang.ref.WeakReference;

public class BaseLogicActivity extends BaseCommonActivity {

    protected PreferenceUtil sp;
    private WeakReference<SuperRoundLoadingDialogFragment> loadingWeakReference;

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


    /**
     * 显示加载对话框
     */
    public void showLoading() {
        showLoading(getString(R.string.loading));
    }

    /**
     * 显示加载对话框
     */
    public void showLoading(int data) {
        showLoading(getString(data));
    }

    /**
     * 显示加载对话框
     */
    public void showLoading(String message) {
        if (loadingWeakReference == null || loadingWeakReference.get() == null) {
            loadingWeakReference = new WeakReference<>(
                    SuperRoundLoadingDialogFragment.newInstance(message)
            );
        }

        SuperRoundLoadingDialogFragment dialog = loadingWeakReference.get();
        if (dialog.getDialog() == null || !dialog.getDialog().isShowing()) {
            dialog.show(getSupportFragmentManager(), "SuperRoundLoadingDialogFragment");
        }
    }

    /**
     * 隐藏加载对话框
     */
    public void hideLoading() {
        SuperRoundLoadingDialogFragment dialog = loadingWeakReference.get();
        if (dialog != null) {
            dialog.dismiss();
            loadingWeakReference.clear();
        }
        loadingWeakReference = null;
    }


}
