package com.betterzhang.learn.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/01/15 上午 10:12
 * Desc   : description
 */

public class Lesson15 {

    public static void main(String[] args) {

        // 参数说明
        // 参数 1 = 第一次延迟时间;
        // 参数 2 = 间隔时间数字;
        // 参数 3 = 时间单位;
        Observable.interval(3, 1, TimeUnit.SECONDS)
                // 该例子发送的事件序列特点：延迟3s后发送事件，每隔1s产生一个数字（从0开始递增1，无限个）
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("接收到了事件" + aLong);
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

        // 注: interval默认在computation调度器上执行
        // 也可自定义指定线程调度器(第3个参数): interval(long, TimeUnit, Scheduler)

    }

}
