package com.betterzhang.learn.rxjava.lesson.CombineOperator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import com.betterzhang.rxjava.R;
import com.jakewharton.rxbinding2.widget.RxTextView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/01/16 上午 10:31
 * Desc   : 联合判断多个事件
 */

public class UsageDemo9 extends AppCompatActivity {

    public static final String TAG = "LearnRxJava";

    /**
     * 步骤1：设置控件变量 & 绑定
     */
    EditText name, age, job;
    Button list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_demo9);

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        job = (EditText) findViewById(R.id.job);
        list = (Button) findViewById(R.id.list);

        /**
         * 步骤2：为每个EditText设置被观察者，用于发送监听事件
         * 说明：
         * 1. 此处采用了RxBinding：RxTextView.textChanges(name) = 对对控件数据变更进行监听（功能类似TextWatcher），需要引入依赖：compile 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
         * 2. 传入EditText控件，点击任1个EditText撰写时，都会发送数据事件 = Function3（）的返回值（下面会详细说明）
         * 3. 采用skip(1)原因：跳过 一开始EditText无任何输入时的空值
         */
        Observable<CharSequence> nameObservable = RxTextView.textChanges(name).skip(1);
        Observable<CharSequence> ageObservable = RxTextView.textChanges(age).skip(1);
        Observable<CharSequence> jobObservable = RxTextView.textChanges(job).skip(1);

        /**
         * 通过combineLatest()合并事件 & 联合判断
         */
        Observable.combineLatest(nameObservable, ageObservable, jobObservable, new Function3<CharSequence, CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence sequence, CharSequence sequence2, CharSequence sequence3) throws Exception {
                /**
                 * 步骤4：规定表单信息输入不能为空
                 */
                // 1. 姓名信息
                boolean isUserNameValid = !TextUtils.isEmpty(name.getText());
                // 除了设置为空，也可设置长度限制
                // boolean isUserNameValid = !TextUtils.isEmpty(name.getText()) && (name.getText().toString().length() > 2 && name.getText().toString().length() < 9);

                // 2. 年龄信息
                boolean isUserAgeValid = !TextUtils.isEmpty(age.getText());
                // 3. 职业信息
                boolean isUserJobValid = !TextUtils.isEmpty(job.getText()) ;

                /**
                 * 步骤5：返回信息 = 联合判断，即3个信息同时已填写，"提交按钮"才可点击
                 */
                return isUserNameValid && isUserAgeValid && isUserJobValid;

            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                 /**
                 * 步骤6：返回结果 & 设置按钮可点击样式
                 */
                Log.d(TAG, "提交按钮是否可点击：" + aBoolean);
                list.setEnabled(aBoolean);
            }
        });

    }
}
