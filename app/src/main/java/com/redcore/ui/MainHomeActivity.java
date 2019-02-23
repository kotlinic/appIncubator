package com.redcore.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.redcore.ui.main.activity.MainFeedBackActivity;
import com.redcore.ui.main.activity.MerchantCenterActivity;
import com.redcore.ui.main.activity.VisitorActivity;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainHomeActivity extends AppCompatActivity {
    static String TAG = "MainHomeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.b001, R.id.b002, R.id.b003, R.id.b004, R.id.b005, R.id.b0011, R.id.b0012, R.id.b0013, R.id.b0014, R.id.b0015})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.b001:
                startActivity(new Intent(this, MerchantCenterActivity.class));
                break;
            case R.id.b002:
                startActivity(new Intent(this, MainFeedBackActivity.class));
                break;
            case R.id.b003:
                startActivity(new Intent(this, VisitorActivity.class));
                break;
            case R.id.b004:
                //EventLog upload service
                Observer observer=new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                };
                Observable.just(1, 2, 3)
                        .delay(3, TimeUnit.SECONDS) // 延迟3s再发送，由于使用类似，所以此处不作全部展示
                        .subscribe(observer);
                break;
            case R.id.b005:
                Observable<Integer> odds = Observable.just(1, 3, 5).subscribeOn(io.reactivex.schedulers.Schedulers.io());
                Observable<Integer> evens = Observable.just(2, 4, 6);
                Observable.merge(odds, evens, odds).subscribe(integer -> {
                    System.err.println(integer);
                });

                Observable.merge(odds, evens, odds).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();


                //--------------
                Observable.just(1)
                        .map(new Function<Integer, Integer>() {
                            @Override
                            public Integer apply(@NonNull Integer integer) throws Exception {
                                Log.i(TAG, "map-1:" + Thread.currentThread().getName());
                                return integer;
                            }
                        })
                        .subscribeOn(Schedulers.newThread())
                        .map(new Function<Integer, Integer>() {
                            @Override
                            public Integer apply(@NonNull Integer integer) throws Exception {
                                Log.i(TAG, "map-2:" + Thread.currentThread().getName());
                                return integer;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .map(new Function<Integer, Integer>() {
                            @Override
                            public Integer apply(@NonNull Integer integer) throws Exception {
                                Log.i(TAG, "map-3:" + Thread.currentThread().getName());
                                return integer;
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(integer -> Log.i(TAG, "subscribe:" + Thread.currentThread().getName()));

                //
                Disposable subscribe = Flowable.just(0, 1).flatMap(new Function<Integer, Publisher<?>>() {
                    @Override
                    public Flowable<Integer> apply(Integer integer) throws Exception {
                        return Flowable.just(integer);
                    }
                }).subscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object integer) throws Exception {
                                Log.i(TAG, "subscribe:" + Thread.currentThread().getName());
                            }
                        });
                break;
            case R.id.b0011:
                Flowable.just(1,2,3).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer r) throws Exception {
                        Log.d("getClientIP", r + "");
                    }
                });
                break;
            case R.id.b0012:
                /*RxJava异步请求 异步线程的另类写法
                这里一开始的发射源是""，后来通过map操作符，请求数据，把String类型的Observable转换成List<ChoosePicBean>类型的Observable。
                结果是对的，但是不知道为什么总感觉这个不是正确的写法，希望有前辈指点下。*/
                Observable.just("").map(new Function<String, List<String>>() {
                    @Override
                    public List<String> apply(String s) throws Exception {
                        List<String> list = new ArrayList<>();
                        //这里写执行耗时操作的逻辑，list是你返回的结果集
                        return list;
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<String>>() {
                            @Override
                            public void accept(List<String> r) throws Exception {

                            }
                        });
                break;
            case R.id.b0013:
                break;
            case R.id.b0014:
                break;
            case R.id.b0015:
                break;
        }
    }


}
