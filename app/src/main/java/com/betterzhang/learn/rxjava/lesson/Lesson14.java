package com.betterzhang.learn.rxjava.lesson;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/01/09 下午 3:12
 * Desc   : timer
 */

public class Lesson14 {

    public static void main(String[] args) {

        // 该例子 = 延迟2s后，发送一个long类型数值
        // 注：timer操作符默认运行在一个新线程上(在java类无法测试出效果，需要到activity中测试，请看MainActivity.java)
        // 也可自定义线程调度器（第3个参数）：timer(long, TimeUnit, Scheduler)
        Observable.timer(2, TimeUnit.SECONDS)
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

        // 注: timer操作符默认运行在一个新线程上
        // 也可自定义线程调度器(第3个参数): timer(long, TimeUnit, Scheduler)

    }

}
