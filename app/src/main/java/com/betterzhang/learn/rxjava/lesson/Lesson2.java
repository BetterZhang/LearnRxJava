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
 * Time   : 2017/07/24 下午 2:19
 * Desc   : description
 */

public class Lesson2 {

    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
//                emitter.onError(new Throwable("exception"));
//                emitter.onError(new Throwable("exception 2"));
                System.out.println("emit 4");
                emitter.onNext(4);
                System.out.println("emit 5");
                emitter.onNext(5);
                System.out.println("emit complete");
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
                System.out.println("error: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });
    }

}
