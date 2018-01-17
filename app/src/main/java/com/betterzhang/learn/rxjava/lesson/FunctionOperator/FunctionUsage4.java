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
import io.reactivex.functions.Predicate;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/01/16 下午 4:36
 * Desc   : retry
 */

public class FunctionUsage4 extends AppCompatActivity {

    public static final String TAG = "LearnRxJava";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * retry()
         * 作用：出现错误时，让被观察者重新发送数据
         * 注：若一直错误，则一直重新发送
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onError(new Exception("发生错误了"));
//                emitter.onNext(3);
//            }
//        })
//        // 遇到错误时，让被观察者重新发射数据（若一直错误，则一直重新发送
//        .retry()
//        .subscribe(new Observer<Integer>() {
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
         * retry（long time）
         * 作用：出现错误时，让被观察者重新发送数据（具备重试次数限制）
         * 参数 = 重试次数
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onError(new Exception("发生错误了"));
//                emitter.onNext(3);
//            }
//        })
//        // 设置重试次数 = 3次
//        .retry(3)
//        .subscribe(new Observer<Integer>() {
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
         * retry（Predicate predicate）
         * 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送& 持续遇到错误，则持续重试）
         * 参数 = 判断逻辑
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onError(new Exception("发生错误了"));
//                emitter.onNext(3);
//            }
//        })
//        // 拦截错误后，判断是否需要重新发送请求
//        .retry(new Predicate<Throwable>() {
//            @Override
//            public boolean test(Throwable throwable) throws Exception {
//                // 捕获异常
//                Log.e(TAG, "retry错误" + throwable.toString());
//                // 返回false = 不重新重新发送数据 & 调用观察者的onError结束
//                // 返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
//                return false;
//            }
//        })
//        .subscribe(new Observer<Integer>() {
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
         * retry（new BiPredicate<Integer, Throwable>）
         * 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试）
         * 参数 =  判断逻辑（传入当前重试次数 & 异常错误信息）
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onError(new Exception("发生错误了"));
//                emitter.onNext(3);
//            }
//        })
//        // 拦截错误后，判断是否需要重新发送请求
//        .retry(new BiPredicate<Integer, Throwable>() {
//            @Override
//            public boolean test(Integer integer, Throwable throwable) throws Exception {
//                // 捕获异常
//                Log.e(TAG, "异常错误 = " + throwable.toString());
//
//                // 获取当前重试次数
//                Log.e(TAG, "当前重试次数 = " + integer);
//                // 返回false = 不重新重新发送数据 & 调用观察者的onError结束
//                // 返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
//                return false;
//            }
//        })
//        .subscribe(new Observer<Integer>() {
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
         * retry（long time,Predicate predicate）
         * 作用：出现错误后，判断是否需要重新发送数据（具备重试次数限制）
         * 参数 = 设置重试次数 & 判断逻辑
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Exception("发生错误了"));
                emitter.onNext(3);
            }
        })
        // 拦截错误后，判断是否需要重新发送请求
        .retry(3, new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                // 捕获异常
                Log.e(TAG, "retry错误: " + throwable.toString());
                // 返回false = 不重新重新发送数据 & 调用观察者的onError（）结束
                // 返回true = 重新发送请求（最多重新发送3次）
                return true;
            }
        })
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
