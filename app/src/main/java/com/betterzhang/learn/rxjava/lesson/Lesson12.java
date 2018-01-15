package com.betterzhang.learn.rxjava.lesson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/01/09 下午 2:17
 * Desc   : description
 */

public class Lesson12 {

    public static void main(String[] args) {

        // 下列方法一般用于测试使用

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        // <-- empty()  -->
        // 该方法创建的被观察者对象发送事件的特点：仅发送Complete事件，直接通知完成
        // 即观察者接收后会直接调用onCompleted()
        Observable observable1 = Observable.empty();
        observable1.subscribe(observer);

        System.out.println("----------");


        // <-- error()  -->
        // 该方法创建的被观察者对象发送事件的特点：仅发送Error事件，直接通知异常
        // 可自定义异常
        // 即观察者接收后会直接调用onError()
        Observable observable2 = Observable.error(new RuntimeException());
        observable2.subscribe(observer);

        System.out.println("----------");

        // <-- never()  -->
        // 该方法创建的被观察者对象发送事件的特点：不发送任何事件
        // 即观察者接收后什么都不调用
        Observable observable3 = Observable.never();
        observable3.subscribe(observer);
    }

}
