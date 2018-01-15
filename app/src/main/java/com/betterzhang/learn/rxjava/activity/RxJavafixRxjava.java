package com.betterzhang.learn.rxjava.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.betterzhang.learn.rxjava.api.GetRequest_Interface;
import com.betterzhang.learn.rxjava.domain.Translation;
import com.betterzhang.rxjava.R;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/01/15 下午 2:36
 * Desc   : description
 */

public class RxJavafixRxjava extends AppCompatActivity {

    public static final String TAG = "LearnRxJava";

    Retrofit retrofit = null;
    GetRequest_Interface request = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        initRetrofit();

        // c. 采用Observable<...>形式 对 网络请求 进行封装
        Observable.interval(2, 1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "第 " + aLong + " 次轮询" );
                        Observable<Translation> observable = request.getCall();
                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<Translation>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Translation translation) {
                                        // e.接收服务器返回的数据
                                        translation.show();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.d(TAG, "请求失败");
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    private void initRetrofit() {
        // a. 创建Retrofit对象
        retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")                            // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create())         // 设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  // 支持RxJava
                .build();

        // b. 创建 网络请求接口 的实例
        request = retrofit.create(GetRequest_Interface.class);
    }
}
