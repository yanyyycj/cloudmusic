package com.ixuea.courses.mymusic;


import android.util.Log;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ixuea.courses.mymusic.activity.BaseViewModelActivity;
import com.ixuea.courses.mymusic.adapter.OnPageChangeListenerAdapter;
import com.ixuea.courses.mymusic.component.ad.model.Ad;
import com.ixuea.courses.mymusic.component.login.activity.LoginHomeActivity;
import com.ixuea.courses.mymusic.component.main.tab.TabEntity;
import com.ixuea.courses.mymusic.component.main.tab.adapter.MainAdapter;
import com.ixuea.courses.mymusic.databinding.ActivityMainBinding;
import com.ixuea.courses.mymusic.util.Constant;
import com.ixuea.courses.mymusic.util.StringUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends BaseViewModelActivity<ActivityMainBinding> {
    /**
     * 底部指示器（tab）文本，图标，选中的图标
     */
    private static final int[] indicatorTitles = new int[]{R.string.discovery, R.string.video, R.string.me, R.string.feed, R.string.live};
    private static final int[] indicatorIcons = new int[]{R.drawable.discovery, R.drawable.video, R.drawable.me, R.drawable.feed, R.drawable.live};
    private static final int[] indicatorSelectedIcons = new int[]{R.drawable.discovery_selected, R.drawable.video_selected, R.drawable.me_selected, R.drawable.feed_selected, R.drawable.live_selected};
    private static final String TAG = "MainActivity";
    private MainAdapter adapter;

    @Override
    protected void initViews() {
        super.initViews();
        //状态栏透明，内容显示到状态栏
        QMUIStatusBarHelper.translucent(this);

        //缓存页面数量
        // 默认是缓存一个
        binding.list.setOffscreenPageLimit(5);

        //指示器
        ArrayList<CustomTabEntity> indicatorTabs = new ArrayList<>();

        for (int i = 0; i < indicatorTitles.length; i++) {
            indicatorTabs.add(
                    new TabEntity(
                            getString(indicatorTitles[i]),
                            indicatorSelectedIcons[i],
                            indicatorIcons[i]
                    )
            );
        }

        binding.indicator.setTabData(indicatorTabs);

    }

    @Override
    protected void initDatum() {
        super.initDatum();

        // showMessageUnreadCount(1000);

        adapter = new MainAdapter(getHostActivity(), getSupportFragmentManager());
        binding.list.setAdapter(adapter);
        adapter.setDatum(Arrays.asList(0, 1, 2, 3, 4));

        String action = getIntent().getAction();
        if (action == Constant.ACTION_LOGIN) {
            startActivity(LoginHomeActivity.class);
        }

    }

    @Override
    protected void initListeners() {
        super.initListeners();

        //打开侧滑按钮点击
        binding.leftButton.setOnClickListener(v -> {
        });

        //搜索容器点击
        binding.searchContainer.setOnClickListener(v -> {

        });

        binding.indicator.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                binding.list.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        binding.list.addOnPageChangeListener(new OnPageChangeListenerAdapter() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.indicator.setCurrentTab(position);
            }
        });
    }


    public void showMessageUnreadCount(Integer unReadCount) {
        if (unReadCount > 0) {
            String messageCount = StringUtil.formatMessageCount(unReadCount);

            binding.indicator.showMsg(0, unReadCount);
            binding.leftButton.showTextBadge(messageCount);
        } else {
            binding.indicator.hideMsg(0);
            binding.leftButton.hiddenBadge();
        }
    }


    public void processAdClick(Ad data) {
        Log.d(TAG, "processAdClick:" + data.getIcon());
    }
}