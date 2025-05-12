package com.ixuea.courses.mymusic.component.discovery.fragment;

import static autodispose2.AutoDispose.autoDisposable;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ixuea.courses.mymusic.MainActivity;
import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.component.ad.model.Ad;
import com.ixuea.courses.mymusic.component.api.HttpObserver;
import com.ixuea.courses.mymusic.component.discovery.adapter.DiscoverAdapter;
import com.ixuea.courses.mymusic.component.discovery.model.ui.BannerData;
import com.ixuea.courses.mymusic.component.discovery.model.ui.SongData;
import com.ixuea.courses.mymusic.component.sheet.model.ListResponse;
import com.ixuea.courses.mymusic.component.sheet.model.Sheet;
import com.ixuea.courses.mymusic.component.song.model.Song;
import com.ixuea.courses.mymusic.databinding.FragmentDiscoveryBinding;
import com.ixuea.courses.mymusic.fregment.BaseViewModelFragment;
import com.ixuea.courses.mymusic.model.ui.BaseMultiItemEntity;
import com.ixuea.courses.mymusic.model.ui.ButtonData;
import com.ixuea.courses.mymusic.model.ui.FooterData;
import com.ixuea.courses.mymusic.model.ui.SheetData;
import com.ixuea.courses.mymusic.repository.DefaultRepository;
import com.ixuea.courses.mymusic.util.Constant;
import com.ixuea.superui.util.SuperDelayUtil;
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
    private long startTime;

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


        //刷新箭头颜色
        binding.refresh.setColorSchemeResources(R.color.primary);

        //刷新圆圈颜色
        binding.refresh.setProgressBackgroundColorSchemeResource(R.color.white);
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
    protected void initListeners() {
        super.initListeners();
//        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                loadData();
//            }
//        });

        binding.refresh.setOnRefreshListener(() -> loadData());
    }

    private void endRefresh() {
        binding.refresh.setRefreshing(false);
    }


    @Override
    protected void loadData(boolean isPlaceholder) {
        super.loadData(isPlaceholder);

        //记录开始时间，目的是始终要当前界面最低延迟1秒在显示内容
        //这样刷新效果才不至于一瞬间就没有了
        startTime = System.currentTimeMillis();

        binding.refresh.setRefreshing(true);

        datum = new ArrayList<>();

        //广告api
        Observable<ListResponse<Ad>> ads = DefaultRepository.getInstance().bannerAd();

        ads
                .to(autoDisposable(AndroidLifecycleScopeProvider.from(this)))//传入this能够自动监听当前fragment的生命周期，若fragment销毁，则会去销毁rxjava的引用防止内存泄漏
                .subscribe(new HttpObserver<ListResponse<Ad>>(this) {
                    @Override
                    public boolean onFailed(ListResponse<Ad> data, Throwable e) {
                        endRefresh();
                        return super.onFailed(data, e);
                    }

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
        Observable<ListResponse<Sheet>> api = DefaultRepository.getInstance().sheets(Constant.SIZE12);
        api.to(autoDisposable(AndroidLifecycleScopeProvider.from(this)))//传入this能够自动监听当前fragment的生命周期，若fragment销毁，则会去销毁rxjava的引用防止内存泄漏
                .subscribe(new HttpObserver<ListResponse<Sheet>>(this) {
                    @Override
                    public boolean onFailed(ListResponse<Sheet> data, Throwable e) {
                        endRefresh();
                        return super.onFailed(data, e);
                    }

                    @Override
                    public void onSucceeded(ListResponse<Sheet> data) {

                        //添加歌单数据
                        datum.add(new SheetData(data.getData().getData()));

                        loadSongData();
                    }
                });
    }

    private void loadSongData() {
        //歌单API
        Observable<ListResponse<Song>> api = DefaultRepository.getInstance().songs();
        api.to(autoDisposable(AndroidLifecycleScopeProvider.from(this)))//传入this能够自动监听当前fragment的生命周期，若fragment销毁，则会去销毁rxjava的引用防止内存泄漏
                .subscribe(new HttpObserver<ListResponse<Song>>(this) {
                    @Override
                    public boolean onFailed(ListResponse<Song> data, Throwable e) {
                        endRefresh();
                        return super.onFailed(data, e);
                    }
                    @Override
                    public void onSucceeded(ListResponse<Song> data) {
                        datum.add(new SongData(data.getData().getData()));

                        //添加尾部数据
                        datum.add(new FooterData());

                        //设置数据到适配器
                        //结束时间
                        long endTime = System.currentTimeMillis();

                        //网络请求消耗的时间
                        long consumeTime = endTime - startTime;

                        if (consumeTime < 1000) {
                            //小于1秒钟，要延迟
                            SuperDelayUtil.delay(1000 - consumeTime, () -> show());
                        } else {
                            show();
                        }
                    }
                });
    }

    private void show() {
        endRefresh();
        adapter.setNewInstance(datum);
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

    @Override
    public void onSongMoreClick() {
        Log.d(TAG, "onSongMoreClick");
    }

    @Override
    public void onSongClick(Song data) {
        Log.d(TAG, "onSongClick: " + data.getTitle());
    }

    @Override
    public void onRefreshClick() {
        binding.list.smoothScrollToPosition(0);
        //延时200毫秒，执行加载数据，目的是让列表先向上滚动到顶部
        binding.list.postDelayed(() -> loadData(), 0); //网络不好不用加延时
    }

    @Override
    public void onCustomDiscoveryClick() {

    }
}
