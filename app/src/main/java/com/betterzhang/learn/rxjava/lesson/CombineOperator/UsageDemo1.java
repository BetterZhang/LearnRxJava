package com.betterzhang.learn.rxjava.lesson.CombineOperator;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/01/16 上午 9:57
 * Desc   : description
 */

public class UsageDemo1 {

    public static void main(String[] args) {

        /**
         * concat()：组合多个被观察者（≤4个）一起发送数据，串行执行
         */
        Observable.concat(Observable.just(1, 2, 3),
                            Observable.just(4, 5, 6),
                            Observable.just(7, 8, 9),
                            Observable.just(10, 11, 12))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

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

        System.out.println("-----------------------");


        /**
         * concatArray()：组合多个被观察者一起发送数据（可＞4个），串行执行
         */
        Observable.concatArray(Observable.just(1, 2, 3),
                                Observable.just(4, 5, 6),
                                Observable.just(7, 8, 9),
                                Observable.just(10, 11, 12),
                                Observable.just(13, 14, 15))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

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
