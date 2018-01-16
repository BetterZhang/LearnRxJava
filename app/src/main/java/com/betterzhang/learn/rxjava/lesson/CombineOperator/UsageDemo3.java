package com.betterzhang.learn.rxjava.lesson.CombineOperator;

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
 * Time   : 2018/01/16 上午 10:31
 * Desc   : description
 */

public class UsageDemo3 extends AppCompatActivity {

    public static final String TAG = "LearnRxJava";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 无使用concatDelayError()的情况
//        Observable.concat(
//                Observable.create(new ObservableOnSubscribe<Integer>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                        emitter.onNext(1);
//                        emitter.onNext(2);
//                        emitter.onNext(3);
//                        // 发送Error事件，因为无使用concatDelayError，所以第2个Observable将不会发送事件
//                        emitter.onError(new NullPointerException());
//                        emitter.onComplete();
//                    }
//                }),
//                Observable.just(4, 5, 6))
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.d(TAG, "接收到了事件" + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//                });

        // concatArrayDelayError（）的情况
//        Observable.concatArrayDelayError(
//                Observable.create(new ObservableOnSubscribe<Integer>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                        emitter.onNext(1);
//                        emitter.onNext(2);
//                        emitter.onNext(3);
//                        // 发送Error事件，因为使用了concatArrayDelayError，所以第2个Observable将会发送事件，等发送完毕后，再发送错误事件
//                        emitter.onError(new NullPointerException());
//                        emitter.onComplete();
//                    }
//                }),
//                Observable.just(4, 5, 6))
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.d(TAG, "接收到了事件" + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//                });

        // mergeDelayError（）操作符同理，此处不作过多演示
//        Observable.merge(
//                Observable.create(new ObservableOnSubscribe<Integer>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                        emitter.onNext(1);
//                        emitter.onNext(2);
//                        emitter.onNext(3);
//                        // 发送Error事件，因为无使用concatDelayError，所以第2个Observable将不会发送事件
//                        emitter.onError(new NullPointerException());
//                        emitter.onComplete();
//                    }
//                }),
//                Observable.just(4, 5, 6))
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.d(TAG, "接收到了事件" + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//                });

        Observable.mergeArrayDelayError(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                        // 发送Error事件，因为无使用concatDelayError，所以第2个Observable将不会发送事件
                        emitter.onError(new NullPointerException());
                        emitter.onComplete();
                    }
                }),
                Observable.just(4, 5, 6))
                .subscribe(new Observer<Integer>() {
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
