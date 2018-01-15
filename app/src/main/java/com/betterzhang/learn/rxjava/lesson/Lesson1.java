package com.betterzhang.learn.rxjava.lesson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2017/07/24 下午 2:00
 * Desc   : description
 */

public class Lesson1 {

    public static void main(String[] args) {

        // 创建一个上游 Observable
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        // 创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("subscribe");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        };

        // 建立连接
        observable.subscribe(observer);

        System.out.println("--------------------------------");

        // 链式写法
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("subscribe");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });
    }

}
