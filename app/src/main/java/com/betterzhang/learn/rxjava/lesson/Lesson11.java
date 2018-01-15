package com.betterzhang.learn.rxjava.lesson;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/01/09 下午 2:07
 * Desc   : description
 */

public class Lesson11 {

    public static void main(String[] args) {

        // 设置一个集合
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // 通过fromIterable()将集合中的对象 / 数据发送出去
        Observable.fromIterable(list)
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


        System.out.println("------------------------------");

        /**
         * 集合遍历
         */
        // 1. 设置一个集合
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);

        // 2. 通过fromIterable()将集合中的对象 / 数据发送出去
        Observable.fromIterable(list2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("集合遍历");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("集合中的数据元素 = " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("遍历结束");
                    }
                });

    }

}
