package com.ixuea.courses.mymusic.component.discovery.fragment;

import static autodispose2.AutoDispose.autoDisposable;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ixuea.courses.mymusic.component.ad.model.Ad;
import com.ixuea.courses.mymusic.component.api.HttpObserver;
import com.ixuea.courses.mymusic.component.discovery.adapter.DiscoverAdapter;
import com.ixuea.courses.mymusic.component.discovery.model.ui.BannerData;
import com.ixuea.courses.mymusic.component.sheet.model.ListResponse;
import com.ixuea.courses.mymusic.databinding.FragmentDiscoveryBinding;
import com.ixuea.courses.mymusic.fregment.BaseViewModelFragment;
import com.ixuea.courses.mymusic.model.ui.BaseMultiItemEntity;
import com.ixuea.courses.mymusic.repository.DefaultRepository;

import java.util.ArrayList;
import java.util.List;

import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.rxjava3.core.Observable;

public class DiscoveryFragment extends BaseViewModelFragment<FragmentDiscoveryBinding> {
    /*
    列表数据集合
     */
    private List<BaseMultiItemEntity> datum;
    private LinearLayoutManager layoutManager;
    private DiscoverAdapter adapter;

    @Override
    protected void initViews() {
        super.initViews();
        //高度固定
        //可以提交性能
        //但由于这里是项目课程
        //所以这里不讲解
        //会在《详解RecyclerView》课程中讲解
        //http://www.ixuea.com/courses/8
        binding.list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getHostActivity());
        binding.list.setLayoutManager(layoutManager);

        //分割线
        DividerItemDecoration decoration = new DividerItemDecoration(binding.list.getContext(), RecyclerView.VERTICAL);
        binding.list.addItemDecoration(decoration);
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        //创建适配器
        adapter = new DiscoverAdapter();

        //设置适配器
        binding.list.setAdapter(adapter);


        loadData();
    }

    @Override
    protected void loadData(boolean isPlaceholder) {
        datum = new ArrayList<>();

        //广告api
        Observable<ListResponse<Ad>> ads = DefaultRepository.getInstance().bannerAd();

        ads
                .to(autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new HttpObserver<ListResponse<Ad>>() {
                    @Override
                    public void onSucceeded(ListResponse<Ad> data) {
                        datum.add(new BannerData(
                                data.getData().getData())
                        );

                        //设置数据到适配器
                        adapter.setNewInstance(datum);
                    }
                });

    }

    public static DiscoveryFragment newInstance() {

        Bundle args = new Bundle();

        DiscoveryFragment fragment = new DiscoveryFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
