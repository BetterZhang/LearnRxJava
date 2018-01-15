package com.betterzhang.learn.rxjava.lesson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/01/09 下午 1:53
 * Desc   : fromArray
 */

public class Lesson10 {

    public static void main(String[] args) {

        // 设置需要传入的数组
        Integer[] items = {0, 1, 2, 3, 4};
//        Integer[] items = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        // 创建被观察者对象（Observable）时传入数组
        // 在创建后就会将该数组转换成Observable & 发送该对象中的所有数据
        Observable.fromArray(items)
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


        System.out.println("-------------------------------------");

        // 注：
        // 可发送10个以上参数
        // 若直接传递一个list集合进去，否则会直接把list当做一个数据元素发送

        // 1. 设置需要传入的数组
        Integer[] item = {0, 1, 2, 3, 4};
        // 2. 创建被观察者对象（Observable）时传入数组
        // 在创建后就会将该数组转换成Observable & 发送该对象中的所有数据
        Observable.fromArray(item)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("数组遍历");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("数组中的元素 = " + integer);
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
