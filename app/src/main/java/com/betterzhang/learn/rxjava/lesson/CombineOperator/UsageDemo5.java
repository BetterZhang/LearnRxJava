package com.betterzhang.learn.rxjava.lesson.CombineOperator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.betterzhang.rxjava.R;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/01/16 上午 10:31
 * Desc   : 合并多个事件
 */

public class UsageDemo5 extends AppCompatActivity {

    public static final String TAG = "LearnRxJava";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * combineLatest()
         */
//        Observable.combineLatest(
//                Observable.just(1L, 2L, 3L),    // 第1个发送数据事件的Observable
//                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS), // 第2个发送数据事件的Observable：从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
//                new BiFunction<Long, Long, Long>() {
//                    @Override
//                    public Long apply(Long aLong, Long aLong2) throws Exception {
//                        // o1 = 第1个Observable发送的最新（最后）1个数据
//                        // o2 = 第2个Observable发送的每1个数据
//                        Log.d(TAG, "合并的数据是： " + aLong + " " + aLong2);
//                        return aLong + aLong2;
//                        // 合并的逻辑 = 相加
//                        // 即第1个Observable发送的最后1个数据 与 第2个Observable发送的每1个数据进行相加
//                    }
//                }).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                Log.d(TAG, "合并的结果是： " + aLong);
//            }
//        });


        /**
         * reduce()
         * 聚合的逻辑根据需求撰写，但本质都是前2个数据聚合，然后与后1个数据继续进行聚合，依次类推
         */
//        Observable.just(1, 3, 7, 4)
//                .reduce(new BiFunction<Integer, Integer, Integer>() {
//                    @Override
//                    public Integer apply(Integer integer, Integer integer2) throws Exception {
////                        Log.d(TAG, "本次计算的数据是：" + integer + "乘" + integer2);
////                        return integer * integer2;
//                        Log.d(TAG, "本次计算的数据是：" + integer + "加" + integer2);
//                        return integer + integer2;
//                        // 本次聚合的逻辑是：全部数据相乘起来
//                        // 原理：第1次取前2个数据相乘，之后每次获取的数据 = 返回的数据 × 原始下一个数据
//                    }
//                }).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.d(TAG, "最终的计算结果是：" + integer);
//            }
//        });


        /**
         * collect()
         * 将被观察着Observable发送的数据事件收集到一个数据结构里
         */
//        Observable.just(1, 2, 3, 4, 5, 6)
//                .collect(
//                        // 1. 创建数据结构（容器），用于收集被观察着发送的数据
//                        new Callable<ArrayList<Integer>>() {
//                            @Override
//                            public ArrayList<Integer> call() throws Exception {
//                                return new ArrayList<>();
//                            }
//                        },
//                        // 2. 对发送的数据进行收集
//                        new BiConsumer<ArrayList<Integer>, Integer>() {
//                            @Override
//                            public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
//                                // 参数说明：integers = 容器，integer = 后者数据
//                                // 对发送的数据进行收集
//                                integers.add(integer);
//                            }
//                        }
//                ).subscribe(new Consumer<ArrayList<Integer>>() {
//                    @Override
//                    public void accept(ArrayList<Integer> integers) throws Exception {
//                        Log.d(TAG, "本次发送的数据是：" + integers);
//                    }
//        });


        /**
         * startWith()
         * 在一个被观察者发送数据之前，追加发送一些数据/一个新的被观察者
         */
        // <-- 在一个被观察者发送事件前，追加发送一些数据 -->
        // 追加数据顺序 = 后调用先追加
//        Observable.just(4, 5, 6)
//                .startWith(0)               //追加单个数据 = startWith()
//                .startWithArray(1, 2, 3)    // 追加多个数据 = startWithArray()
//        .subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "接收到了事件" + integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "对Error事件作出响应");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "对Complete事件作出响应");
//            }
//        });



        // <-- 在一个被观察者发送事件前，追加发送被观察者 & 发送数据 -->
        // 注：追加数据顺序 = 后调用先追加
//        Observable.just(4, 5, 6)
//                .startWith(Observable.just(1, 2, 3))
//                .startWith(0)
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
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


        /**
         * count()
         * 统计被观察者发送事件的数量
         * 注：返回结果 = Long类型
         */
        Observable.just(1, 2, 3, 4)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "发送事件的数量 = " + aLong);
                    }
                });

    }
}
