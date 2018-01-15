package com.betterzhang.learn.rxjava.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.betterzhang.rxjava.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2017/07/25 下午 3:57
 * Desc   : description
 */

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "LearnRxJava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_next = (TextView) findViewById(R.id.tv_next);
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RxJavafixRxjava.class);
                startActivity(intent);
            }
        });

        Log.d(TAG, "Current Thread: " + Thread.currentThread().getName());

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "Observable thread is: " + Thread.currentThread().getName());
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "Observer thread is: " + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + integer);
            }
        };

//        observable.subscribe(consumer);

//        observable.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(consumer);

        /**
            多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
            多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
         */
//        observable.subscribeOn(Schedulers.newThread())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .observeOn(Schedulers.io())
//                .subscribe(consumer);


        observable.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "After observeOn(mainThread), current thread is: " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "After observeOn(io), current thread is: " + Thread.currentThread().getName());
                    }
                })
                .subscribe(consumer);


        Log.d(TAG, "-------------------------");

//        Observable.timer(2, TimeUnit.SECONDS)
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "开始采用subscribe连接");
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        Log.d(TAG, "接收到了事件" + aLong);
//                        Log.d(TAG, "Current thread is: " + Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//                });


        Log.d(TAG, "-------------------------");


        // 参数说明:
        // 参数 1 = 第一次延迟时间;
        // 参数 2 = 间隔时间数字;
        // 参数 3 = 时间单位;
//        Observable.interval(3, 1, TimeUnit.SECONDS)
//                // 该例子发送的事件序列特点：延迟3s后发送事件，每隔1s产生一个数字（从0开始递增1，无限个）
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "开始采用subscribe连接");
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        Log.d(TAG, "接收到了事件" + aLong);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//                });

        // 注: interval默认在computation调度器上执行
        // 也可自定义指定线程调度器(第3个参数): interval(long, TimeUnit, Scheduler)


        // 参数说明:
        // 参数 1 = 事件序列起始点;
        // 参数 2 = 事件数量;
        // 参数 3 = 第1次事件延迟发送时间;
        // 参数 4 = 间隔时间数字;
        // 参数 5 = 时间单位;
//        Observable.intervalRange(3, 10, 2, 1, TimeUnit.SECONDS)
//                // 该例子发送的事件序列特点：
//                // 1. 从3开始，一共发送10个事件;
//                // 2. 第1次延迟2s发送，之后每隔1s产生1个数字（从0开始递增1，无限个）
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "开始采用subscribe连接");
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        Log.d(TAG, "接收到了事件" + aLong);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//                });


        // 参数说明:
        // 参数 1 = 事件序列起始点;
        // 参数 2 = 事件数量;
        // 注：若设置为负数，则会抛出异常;
//        Observable.range(3, 10)
//                // 该例子发送的事件序列特点：从3开始发送，每次发送事件递增1，一共发送10个事件
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "开始采用subscribe连接");
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.d(TAG, "接收到了事件" + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//                });


        // 类似于range(), 区别在于该方法支持数据类型 = Long
        Observable.rangeLong(10, 10)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "接收到了事件" + aLong);
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
}
