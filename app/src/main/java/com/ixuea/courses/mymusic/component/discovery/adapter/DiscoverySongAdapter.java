package com.ixuea.courses.mymusic.component.discovery.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.component.song.model.Song;
import com.ixuea.courses.mymusic.util.ImageUtil;

public class DiscoverySongAdapter extends BaseQuickAdapter<Song, BaseViewHolder> {
    public DiscoverySongAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder holder, Song data) {
        ImageUtil.show(getContext(), holder.getView(R.id.icon), data.getIcon());
        holder.setText(R.id.title, data.getTitle());
        holder.setText(R.id.more, String.format("%s-%s", data.getSinger().getNickname(), "专辑名称"));

        //最后一个控件的分割线不显示 ，报错但是能正常运行
        holder.setGone(R.id.divider, holder.getLayoutPosition() == getItemCount() - 1);
    }
}
