package com.ixuea.courses.mymusic.component.video.fragment;

import android.os.Bundle;

import com.ixuea.courses.mymusic.databinding.FragmentVideoBinding;
import com.ixuea.courses.mymusic.fregment.BaseViewModelFragment;

public class VideoFragment extends BaseViewModelFragment<FragmentVideoBinding> {
    public static VideoFragment newInstance() {

        Bundle args = new Bundle();

        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
