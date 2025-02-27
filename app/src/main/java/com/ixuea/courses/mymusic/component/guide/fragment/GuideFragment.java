package com.ixuea.courses.mymusic.component.guide.fragment;

import android.os.Bundle;

import com.ixuea.courses.mymusic.databinding.FregmentGuideBinding;
import com.ixuea.courses.mymusic.fregment.BaseViewModelFragment;
import com.ixuea.courses.mymusic.util.Constant;

public class GuideFragment extends BaseViewModelFragment<FregmentGuideBinding> {

    public static GuideFragment newInstance(Integer data) {

        Bundle args = new Bundle();
        args.putInt(Constant.ID, data);
        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        int data = getArguments().getInt(Constant.ID);
        binding.icon.setImageResource(data);
    }

}
