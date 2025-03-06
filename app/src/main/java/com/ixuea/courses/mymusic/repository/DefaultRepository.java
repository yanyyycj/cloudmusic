package com.ixuea.courses.mymusic.repository;

import com.ixuea.courses.mymusic.component.ad.model.Ad;
import com.ixuea.courses.mymusic.component.api.DefaultService;
import com.ixuea.courses.mymusic.component.api.NetworkModule;
import com.ixuea.courses.mymusic.component.sheet.model.ListResponse;
import com.ixuea.courses.mymusic.util.Constant;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 本项目默认仓库
 * 主要是从网络，数据库获取数据
 * 目前项目中大部分操作都在这里
 * <p>
 * 如果项目每个模块之间有明显的区别，例如：有商城，有歌单，那可以放到对应模块的Repository
 */
public class DefaultRepository {
    private static DefaultRepository instance;
    private final DefaultService service;

    public DefaultRepository() {
        //虽然当前类是单例设计模式，但因为直接调用provideRetrofit这样的方法
        //所以虽然代码是和MVVM架构模块那边（商城）复用了，但他们不是一个单例对象
        service = NetworkModule.provideRetrofit(NetworkModule.provideOkHttpClient()).create(DefaultService.class);
    }

    /**
     * 返回当前对象的唯一实例
     * <p>
     * 单例设计模式
     * 由于移动端很少有高并发
     * 所以这个就是简单判断
     *
     * @return
     */
    public synchronized static DefaultRepository getInstance() {
        if (instance == null) {
            instance = new DefaultRepository();
        }
        return instance;
    }

    /**
     * 广告列表
     *
     * @return
     */
    public Observable<ListResponse<Ad>> ads(int position) {
        return service.ads(position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 首页banner界面广告
     *
     * @return
     */
    public Observable<ListResponse<Ad>> bannerAd() {
        return ads(Constant.VALUE0);
    }


}
