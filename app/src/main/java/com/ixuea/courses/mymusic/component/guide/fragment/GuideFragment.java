package com.ixuea.courses.mymusic.component.guide.fragment;

import android.os.Bundle;

import com.ixuea.courses.mymusic.databinding.FregmentGuideBinding;
import com.ixuea.courses.mymusic.fregment.BaseViewModelFragment;

public class GuideFragment extends BaseViewModelFragment<FregmentGuideBinding> {

    public static GuideFragment newInstance(Integer data) {

        Bundle args = new Bundle();
        args.putInt("ID", data);
        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        int data = getArguments().getInt("ID");
        binding.icon.setImageResource(data);
    }

}
