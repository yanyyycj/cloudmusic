package com.ixuea.courses.mymusic.component.me.fragment;

import android.os.Bundle;

import com.ixuea.courses.mymusic.databinding.FragmentMeBinding;
import com.ixuea.courses.mymusic.fregment.BaseViewModelFragment;

public class MeFragment extends BaseViewModelFragment<FragmentMeBinding> {
    public static MeFragment newInstance() {

        Bundle args = new Bundle();

        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
