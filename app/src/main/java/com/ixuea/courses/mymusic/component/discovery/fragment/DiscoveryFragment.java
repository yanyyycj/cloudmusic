package com.ixuea.courses.mymusic.component.discovery.fragment;

import android.os.Bundle;

import com.ixuea.courses.mymusic.databinding.FragmentDiscoveryBinding;
import com.ixuea.courses.mymusic.fregment.BaseViewModelFragment;

public class DiscoveryFragment extends BaseViewModelFragment<FragmentDiscoveryBinding> {
    public static DiscoveryFragment newInstance() {

        Bundle args = new Bundle();

        DiscoveryFragment fragment = new DiscoveryFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
