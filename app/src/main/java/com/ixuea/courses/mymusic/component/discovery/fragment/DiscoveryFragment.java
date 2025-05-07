package com.ixuea.courses.mymusic.component.discovery.fragment;

import static autodispose2.AutoDispose.autoDisposable;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ixuea.courses.mymusic.MainActivity;
import com.ixuea.courses.mymusic.component.ad.model.Ad;
import com.ixuea.courses.mymusic.component.api.HttpObserver;
import com.ixuea.courses.mymusic.component.discovery.adapter.DiscoverAdapter;
import com.ixuea.courses.mymusic.component.discovery.model.ui.BannerData;
import com.ixuea.courses.mymusic.component.sheet.model.ListResponse;
import com.ixuea.courses.mymusic.component.sheet.model.Sheet;
import com.ixuea.courses.mymusic.databinding.FragmentDiscoveryBinding;
import com.ixuea.courses.mymusic.fregment.BaseViewModelFragment;
import com.ixuea.courses.mymusic.model.ui.BaseMultiItemEntity;
import com.ixuea.courses.mymusic.model.ui.ButtonData;
import com.ixuea.courses.mymusic.model.ui.SheetData;
import com.ixuea.courses.mymusic.repository.DefaultRepository;
import com.ixuea.courses.mymusic.util.Constant;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.rxjava3.core.Observable;

public class DiscoveryFragment extends BaseViewModelFragment<FragmentDiscoveryBinding> implements OnBannerListener, DiscoverAdapter.DiscoveryAdapterListener {
    private static final String TAG = "DiscoveryFragment";
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

        //设置列表展示方式，按行？按列？行列是？*？
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
        adapter = new DiscoverAdapter(this, this);
        adapter.setDiscoveryAdapterListener(this);

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
                .to(autoDisposable(AndroidLifecycleScopeProvider.from(this)))//传入this能够自动监听当前fragment的生命周期，若fragment销毁，则会去销毁rxjava的引用防止内存泄漏
                .subscribe(new HttpObserver<ListResponse<Ad>>(this) {
                    @Override
                    public void onSucceeded(ListResponse<Ad> data) {
                        datum.add(new BannerData(
                                data.getData().getData())
                        );

                        //添加快捷按钮
                        datum.add(new ButtonData());

                        //请求歌单数据
                        loadSheetData();

                        //设置数据到适配器
                        adapter.setNewInstance(datum);
                    }
                });

    }

    private void loadSheetData() {
        //歌单API
        Observable<ListResponse<Sheet>> sheets = DefaultRepository.getInstance().sheets(Constant.SIZE12);
        sheets.to(autoDisposable(AndroidLifecycleScopeProvider.from(this)))//传入this能够自动监听当前fragment的生命周期，若fragment销毁，则会去销毁rxjava的引用防止内存泄漏
                .subscribe(new HttpObserver<ListResponse<Sheet>>(this) {
                    @Override
                    public void onSucceeded(ListResponse<Sheet> data) {

                        //添加歌单数据
                        datum.add(new SheetData(data.getData().getData()));

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


    /**
     * 轮播图点击
     *
     * @param data
     * @param position
     */
    @Override
    public void OnBannerClick(Object data, int position) {
        ((MainActivity) getHostActivity()).processAdClick((Ad) data);
    }


    @Override
    public void onSheetClick(Sheet data) {
        Log.d(TAG, "onSheetClick: " + data.getTitle());
    }

    @Override
    public void onSheetMoreClick() {

    }
}
