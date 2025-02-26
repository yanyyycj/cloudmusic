package com.ixuea.courses.mymusic.component.guide.activity;

import android.view.View;

import com.ixuea.courses.mymusic.MainActivity;
import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.activity.BaseViewModelActivity;
import com.ixuea.courses.mymusic.component.guide.adapter.GuideAdapter;
import com.ixuea.courses.mymusic.databinding.ActivityGuideBinding;
import com.ixuea.courses.mymusic.util.SuperDarkUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseViewModelActivity<ActivityGuideBinding> implements View.OnClickListener {

    private GuideAdapter adapter;

    @Override
    protected void initViews() {
        super.initViews();
        //设置沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);

        if (SuperDarkUtil.isDark(this)) {
            //状态栏文字白色
            QMUIStatusBarHelper.setStatusBarDarkMode(this);
        } else {
            //状态栏文字黑色
            QMUIStatusBarHelper.setStatusBarLightMode(this);
        }
    }

    protected void initDatum() {
        super.initDatum();
//        OkHttpClient okHttpClient = NetworkModule.provideOkHttpClient();
//        Retrofit retrofit = NetworkModule.provideRetrofit(okHttpClient);
//        service = retrofit.create(DefaultService.class);

        //创建适配器
        adapter = new GuideAdapter(getHostActivity(), getSupportFragmentManager());

        //设置适配器到控件
        binding.list.setAdapter(adapter);

        //让指示器根据列表控件配合工作
        binding.indicator.setViewPager(binding.list);

        //适配器注册数据源观察者
        adapter.registerDataSetObserver(binding.indicator.getDataSetObserver());

        //准备数据
        List<Integer> datum = new ArrayList<>();
        datum.add(R.drawable.guide1);
        datum.add(R.drawable.guide2);
        datum.add(R.drawable.guide3);
        datum.add(R.drawable.guide4);
        datum.add(R.drawable.guide5);

        //设置数据到适配器
        adapter.setDatum(datum);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        binding.loginOrRegister.setOnClickListener(this);
        binding.experienceNow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_or_register) {
            setShowGuide();

        } else {
            startActivityAfterFinishThis(MainActivity.class);
            setShowGuide();
        }

    }

    private void setShowGuide() {
        sp.setShowGuide(false);
    }

}
