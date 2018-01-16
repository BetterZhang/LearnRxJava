package com.betterzhang.learn.rxjava.lesson.CombineOperator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.betterzhang.rxjava.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/01/16 上午 10:31
 * Desc   : 实际应用讲解：合并数据源 & 同时展示
 */

public class UsageDemo6 extends AppCompatActivity {

    public static final String TAG = "LearnRxJava";

    // 用于存放最终展示的数据
    String result = "数据来源自 = ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 设置第1个Observable;通过网络获取数据
         * 此处仅做网络请求的模拟
         */
        Observable<String> network = Observable.just("网络");

        /**
         * 设置第2个Observable;通过本地文件获取数据
         * 此处仅做本地文件请求的模拟
         */
        Observable<String> file = Observable.just("本地文件");

        /**
         * 通过merge()合并事件 & 同时发送事件
         */
        Observable.merge(network, file)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "数据源有：" + s);
                        result += s + "+";
                     }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "获取数据完成");
                        Log.d(TAG, result);
                    }
                });
    }
}
