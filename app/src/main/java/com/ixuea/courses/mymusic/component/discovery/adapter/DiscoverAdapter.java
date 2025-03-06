package com.ixuea.courses.mymusic.component.discovery.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.model.ui.BaseMultiItemEntity;
import com.ixuea.courses.mymusic.util.Constant;

import java.util.ArrayList;

/*
发现界面适配器
 */
public class DiscoverAdapter extends BaseMultiItemQuickAdapter<BaseMultiItemEntity, BaseViewHolder> {

    public DiscoverAdapter() {
        super(new ArrayList<>());
        //添加多类型布局

        //banner类型
        addItemType(Constant.STYLE_BANNER, R.layout.item_discovery_banner);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, BaseMultiItemEntity d) {

    }
}
