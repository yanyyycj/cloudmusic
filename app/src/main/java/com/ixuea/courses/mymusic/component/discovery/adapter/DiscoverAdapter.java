package com.ixuea.courses.mymusic.component.discovery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.component.ad.model.Ad;
import com.ixuea.courses.mymusic.component.discovery.model.ui.BannerData;
import com.ixuea.courses.mymusic.databinding.DiscoveryButtonBinding;
import com.ixuea.courses.mymusic.model.ui.BaseMultiItemEntity;
import com.ixuea.courses.mymusic.model.ui.ButtonData;
import com.ixuea.courses.mymusic.model.ui.IconTitleButtonData;
import com.ixuea.courses.mymusic.util.Constant;
import com.ixuea.courses.mymusic.util.ImageUtil;
import com.ixuea.courses.mymusic.util.ScreenUtil;
import com.ixuea.superui.util.DensityUtil;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

/*
发现界面适配器
 */
public class DiscoverAdapter extends BaseMultiItemQuickAdapter<BaseMultiItemEntity, BaseViewHolder> {

    private final Fragment fragment;
    private final OnBannerListener onBannerListener;

    public DiscoverAdapter(Fragment fragment, OnBannerListener onBannerListener) {
        super(new ArrayList<>());

        this.fragment = fragment;
        this.onBannerListener = onBannerListener;
        
        //添加多类型布局

        //banner类型
        addItemType(Constant.STYLE_BANNER, R.layout.item_discovery_banner);

        //button类型
        addItemType(Constant.STYLE_BUTTON, R.layout.item_discovery_button);
    }


    /**
     * 绑定数据方法
     * 复用等步骤不用管
     * 框架内部自动处理
     *
     * @param holder
     * @param d
     */
    @Override
    protected void convert(@NonNull BaseViewHolder holder, BaseMultiItemEntity d) {
        switch (holder.getItemViewType()) { //报错但是能正常运行
            case Constant.STYLE_BANNER:
                //banner
                BannerData data = (BannerData) d;

                // 找到控件
                Banner bannerView = holder.getView(R.id.banner);

                //设置数据
                BannerImageAdapter<Ad> bannerImageAdapter = new BannerImageAdapter<Ad>(data.getData()) {
                    @Override
                    public void onBindView(BannerImageHolder holder, Ad data, int position, int size) {
                        ImageUtil.show(getContext(), (ImageView) holder.itemView, data.getIcon());
                    }
                };

                bannerView.setAdapter(bannerImageAdapter);
                bannerView.setBannerRound(DensityUtil.dip2px(getContext(), 10));

                //添加生命周期观察者
                bannerView.addBannerLifecycleObserver(fragment);

                bannerView.setOnBannerListener(onBannerListener);

                bannerView.setIndicator(new CircleIndicator(getContext()));
                break;
            case Constant.STYLE_BUTTON:
                //按钮
                bindButtonData(holder, (ButtonData) d);
                break;
        }
    }

    private void bindButtonData(BaseViewHolder holder, ButtonData data) {
        LinearLayout container = holder.getView(R.id.container);
        if (container.getChildCount() > 0) {
            return;
        }

        //横向显示5个半
        float containerWidth = ScreenUtil.getScreenWith(container.getContext()) - DensityUtil.dip2px(container.getContext(), 10 * 2);
        int itemWidth = (int) (containerWidth / 5.5);

        DiscoveryButtonBinding binding;
        LinearLayout.LayoutParams layoutParams;
        for (IconTitleButtonData it : data.getData()) {
            binding = DiscoveryButtonBinding.inflate(LayoutInflater.from(getContext()));
            binding.icon.setImageResource(it.getIcon());
            binding.title.setText(it.getTitle());

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            layoutParams = new LinearLayout.LayoutParams(itemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            container.addView(binding.getRoot(), layoutParams);
        }
    }
}
