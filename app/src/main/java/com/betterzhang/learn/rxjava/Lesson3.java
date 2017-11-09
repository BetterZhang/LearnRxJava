package com.betterzhang.learn.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2017/07/24 下午 2:45
 * Desc   : description
 */

public class Lesson3 {

    public static void main(String[] args) {

        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                System.out.println("emit 1");
                emitter.onNext(1);
                System.out.println("emit 2");
                emitter.onNext(2);
                System.out.println("emit 3");
                emitter.onNext(3);
                System.out.println("emit complete");
                emitter.onComplete();
                System.out.println("emit 4");
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {

            private Disposable mDisposable;
            private int i;

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("subscribe");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext: " + integer);
                i++;
                if (i == 2) {
                    System.out.println("dispose");
                    mDisposable.dispose();
                    System.out.println("isDisposed: " + mDisposable.isDisposed());
                }
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
