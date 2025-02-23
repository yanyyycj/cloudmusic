package com.ixuea.courses.mymusic.component.splash.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;

import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.databinding.FregmentDialogTermServiceBinding;
import com.ixuea.courses.mymusic.fregment.BaseViewModelDialogFragment;
import com.ixuea.courses.mymusic.util.ScreenUtil;
import com.ixuea.courses.mymusic.util.SuperTextUtil;
import com.ixuea.superui.process.SuperProcessUtil;


public class TermServiceDialogFragment extends BaseViewModelDialogFragment<FregmentDialogTermServiceBinding> {

    private static final String TAG = "TermServiceDialogFragment";
    private View.OnClickListener onAgreementClickListener;

    public static TermServiceDialogFragment newInstance() {

        Bundle args = new Bundle();
        TermServiceDialogFragment fragment = new TermServiceDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 显示对话框
     *
     * @param fragmentManager
     * @param onAgreementClickListener
     */
    public static void show(FragmentManager fragmentManager, View.OnClickListener onAgreementClickListener) {
        TermServiceDialogFragment fragment = newInstance();

        fragment.onAgreementClickListener = onAgreementClickListener;
        //tag只是用于查找fragment
        fragment.show(fragmentManager, "TermServiceDialogFragment");
    }

    @Override
    protected void initViews() {
        super.initViews();
        //点击弹窗外边不能关闭
        setCancelable(false);


        SuperTextUtil.setLinkColor(binding.content, getActivity().getColor(R.color.link));
    }

    @Override
    protected void initDatum() {
        super.initDatum();


        Spanned content = Html.fromHtml(getString(R.string.term_service_privacy_content), Html.FROM_HTML_MODE_COMPACT);
        //常规写法
//        SpannableStringBuilder result = SuperTextUtil.setHtmlLinkClick(content, new SuperTextUtil.OnLinkClickListener() {
//            @Override
//            public void onLinkClick(String data) {
//                Log.d(TAG, "onLinkClick: "+data);
//            }
//        });

        //lambda写法，监听器里面只有一个方法才能这样写
        SpannableStringBuilder result = SuperTextUtil.setHtmlLinkClick(content, data -> Log.d(TAG, "onLinkClick: " + data));

        binding.content.setText(result);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        binding.primary.setOnClickListener(v -> {
            dismiss();
            onAgreementClickListener.onClick(v);
        });

        binding.disagree.setOnClickListener(v -> {
            dismiss();
            SuperProcessUtil.killApp();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //修改宽度，默认比AlertDialog.Builder显示对话框宽度窄，看着不好看
        //参考：https://stackoverflow.com/questions/12478520/how-to-set-dialogfragments-width-and-height
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();

        params.width = (int) (ScreenUtil.getScreenWith(getContext()) * 0.9);
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

}
