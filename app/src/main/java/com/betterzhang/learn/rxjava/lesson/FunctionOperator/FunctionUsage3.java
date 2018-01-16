package com.betterzhang.learn.rxjava.lesson.FunctionOperator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.betterzhang.rxjava.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/01/16 下午 4:36
 * Desc   : description
 */

public class FunctionUsage3 extends AppCompatActivity {

    public static final String TAG = "LearnRxJava";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * onErrorReturn()
         * 遇到错误时，发送1个特殊事件 & 正常终止
         * 可捕获在它之前发生的异常
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onError(new Throwable("发生错误了"));
//            }
//        }).onErrorReturn(new Function<Throwable, Integer>() {
//            @Override
//            public Integer apply(Throwable throwable) throws Exception {
//                // 捕捉错误异常
//                Log.e(TAG, "在onErrorReturn处理了错误：" + throwable.toString());
//                // 发生错误事件后，发送一个"666"事件，最终正常结束
//                return 666;
//            }
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "接收到了事件" + integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "对Error事件作出响应");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "对Complete事件作出响应");
//            }
//        });

        /**
         * onErrorResumeNext()
         * 遇到错误时，发送1个新的Observable
         * onErrorResumeNext（）拦截的错误 = Throwable；若需拦截Exception请用onExceptionResumeNext（）
         * 若onErrorResumeNext（）拦截的错误 = Exception，则会将错误传递给观察者的onError方法
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onError(new Throwable("发生错误了"));
//            }
//        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
//            @Override
//            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {
//                // 1. 捕捉错误异常
//                Log.e(TAG, "在onErrorResumeNext处理了错误: " + throwable.toString());
//                // 2. 发生错误事件后，发送一个新的被观察者 & 发送事件序列
//                return Observable.just(11, 22);
//            }
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "接收到了事件" + integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "对Error事件作出响应");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "对Complete事件作出响应");
//            }
//        });


        /**
         * onExceptionResumeNext()
         * 遇到错误时，发送1个新的Observable
         * onExceptionResumeNext（）拦截的错误 = Exception；若需拦截Throwable请用onErrorResumeNext（）
         * 若onExceptionResumeNext（）拦截的错误 = Throwable，则会将错误传递给观察者的onError方法
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Exception("发生错误了"));
            }
        }).onExceptionResumeNext(new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                observer.onNext(11);
                observer.onNext(22);
                observer.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "接收到了事件" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        });

    }
}
