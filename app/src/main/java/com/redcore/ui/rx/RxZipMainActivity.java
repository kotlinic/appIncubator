package com.redcore.ui.rx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/*
Android之RxAndroid2、RxJava2的zip应用场景及示例
https://blog.csdn.net/zhangphil/article/details/71749871
*/
public class RxZipMainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
 
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addItems();
    }
 
    private void addItems() {
        DisposableObserver disposableObserver = new DisposableObserver<DataObject>() {
            @Override
            public void onNext(@NonNull DataObject object) {
                Log.d(TAG, "#####开始#####");
                Log.d(TAG + "数据", String.valueOf(object.A));
                Log.d(TAG + "数据", String.valueOf(object.B));
                Log.d(TAG, "#####结束#####");
            }
 
            @Override
            public void onComplete() {
 
            }
 
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.toString(), e);
            }
        };
 
        mCompositeDisposable.add(
                Observable.zip(getObservableA(null), getObservableB(null),
                        new BiFunction<String, String, DataObject>() {
                            @Override
                            public DataObject apply(String a, String b)
                                    throws Exception {
 
                                DataObject object = new DataObject();
                                object.A = a;
                                object.B = b;
 
                                return object;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(disposableObserver));
    }
 
    @Override
    protected void onDestroy() {
        super.onDestroy();
 
        // 如果退出程序，就清除后台任务
        mCompositeDisposable.clear();
    }
 
    private Observable<String> getObservableA(@NonNull
    final TempObject tempObject) {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(5000); // 假设此处是耗时操作
                } catch (Exception e) {
                    e.printStackTrace();
                }
 
                return "A";
            }
        });
    }
 
    private Observable<String> getObservableB(@NonNull
    final TempObject tempObject) {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(5000); // 假设此处是耗时操作
                } catch (Exception e) {
                    e.printStackTrace();
                }
 
                return "B";
            }
        });
    }
 
    private class DataObject {
        public String A = null;
        public String B = null;
    }
 
    /**
     * 这是一个废弃无用的数据结构，用以演示如何在请求之间传递参数
     */
    private class TempObject {
        public String temp;
    }
}
