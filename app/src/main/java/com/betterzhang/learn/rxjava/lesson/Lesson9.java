package com.betterzhang.learn.rxjava.lesson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/01/09 下午 1:38
 * Desc   : just
 */

public class Lesson9 {

    public static void main(String[] args) {

        // 最多只能发送10个参数
        Observable.just(1, 2, 3, 4/*, 5, 6, 7, 8, 9, 10*/)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("接收到了事件" + integer);
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
