package com.betterzhang.learn.rxjava.lesson;

import java.util.concurrent.Callable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/01/09 下午 2:25
 * Desc   : defer
 */

public class Lesson13 {

    public static Integer i = 10;

    public static void main(String[] args) {

        // 1. 第一次对 i 赋值
        // 通过 defer() 定义被观察着对象
        // 注：此时被观察者对象还没创建
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(i);
            }
        });

        // 2. 第二次对 i 赋值
        i = 15;

        // 3. 观察者开始订阅
        // 注：此时，才会调用 defer() 创建被观察者对象(Observable)
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("接收到的整数是" + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                System.out.println("对Complete事件作出响应");
            }
        });

    }

}
