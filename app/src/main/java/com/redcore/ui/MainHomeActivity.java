package com.redcore.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.redcore.ui.main.activity.MainFeedBackActivity;
import com.redcore.ui.main.activity.MerchantCenterActivity;
import com.redcore.ui.main.activity.VisitorActivity;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainHomeActivity extends AppCompatActivity {
    static String TAG = "MainHomeActivity";

    @BindView(R.id.textview1)
    TextView mTextview1;
    @BindView(R.id.b001)
    Button mB001;
    @BindView(R.id.b002)
    Button mB002;
    @BindView(R.id.b003)
    Button mB003;
    @BindView(R.id.b004)
    Button mB004;
    @BindView(R.id.b005)
    Button mB005;
    @BindView(R.id.b0011)
    Button mB0011;
    @BindView(R.id.b0012)
    Button mB0012;
    @BindView(R.id.b0013)
    Button mB0013;
    @BindView(R.id.b0014)
    Button mB0014;
    @BindView(R.id.b0015)
    Button mB0015;
    @BindView(R.id.ba11)
    Button mBa11;
    @BindView(R.id.ba12)
    Button mBa12;
    @BindView(R.id.ba13)
    Button mBa13;
    @BindView(R.id.ba14)
    Button mBa14;
    @BindView(R.id.ba15)
    Button mBa15;
    @BindView(R.id.ba16)
    Button mBa16;
    @BindView(R.id.ba17)
    Button mBa17;
    @BindView(R.id.ba18)
    Button mBa18;
    @BindView(R.id.ba19)
    Button mBa19;
    @BindView(R.id.ba20)
    Button mBa20;
    @BindView(R.id.ba21)
    Button mBa21;
    @BindView(R.id.ba22)
    Button mBa22;
    @BindView(R.id.ba23)
    Button mBa23;
    @BindView(R.id.ba24)
    Button mBa24;
    @BindView(R.id.ba25)
    Button mBa25;
    @BindView(R.id.ba26)
    Button mBa26;
    @BindView(R.id.ba27)
    Button mBa27;
    @BindView(R.id.ba28)
    Button mBa28;
    @BindView(R.id.b11)
    Button mB11;
    @BindView(R.id.b12)
    Button mB12;
    @BindView(R.id.b13)
    Button mB13;
    @BindView(R.id.b14)
    Button mB14;
    @BindView(R.id.b15)
    Button mB15;
    @BindView(R.id.b16)
    Button mB16;
    @BindView(R.id.b17)
    Button mB17;
    @BindView(R.id.b18)
    Button mB18;
    @BindView(R.id.b19)
    Button mB19;
    @BindView(R.id.b21)
    Button mB21;
    @BindView(R.id.b22)
    Button mB22;
    @BindView(R.id.b23)
    Button mB23;
    @BindView(R.id.b24)
    Button mB24;
    @BindView(R.id.b25)
    Button mB25;
    @BindView(R.id.k4)
    Button mK4;
    @BindView(R.id.k5)
    Button mK5;
    @BindView(R.id.k7)
    Button mK7;
    @BindView(R.id.k6)
    Button mK6;
    @BindView(R.id.k8)
    Button mK8;
    @BindView(R.id.k9)
    Button mK9;
    @BindView(R.id.k10)
    Button mK10;
    @BindView(R.id.b26)
    Button mB26;
    @BindView(R.id.b27)
    Button mB27;
    @BindView(R.id.k2)
    Button mK2;
    @BindView(R.id.k3)
    Button mK3;
    @BindView(R.id.k1)
    Button mK1;
    @BindView(R.id.b28)
    Button mB28;
    @BindView(R.id.b29)
    Button mB29;
    @BindView(R.id.b31)
    Button mB31;
    @BindView(R.id.b32)
    Button mB32;
    @BindView(R.id.b33)
    Button mB33;
    @BindView(R.id.b34)
    Button mB34;
    @BindView(R.id.b35)
    Button mB35;
    @BindView(R.id.b36)
    Button mB36;
    @BindView(R.id.b37)
    Button mB37;
    @BindView(R.id.b38)
    Button mB38;
    @BindView(R.id.b39)
    Button mB39;
    @BindView(R.id.textview2)
    TextView mTextview2;
    @BindView(R.id.b311)
    Button mB311;
    @BindView(R.id.b322)
    Button mB322;
    @BindView(R.id.b333)
    Button mB333;
    @BindView(R.id.b40)
    Button mB40;
    @BindView(R.id.b41)
    Button mB41;
    @BindView(R.id.b42)
    Button mB42;
    @BindView(R.id.b43)
    Button mB43;
    @BindView(R.id.b44)
    Button mB44;
    @BindView(R.id.b45)
    Button mB45;
    @BindView(R.id.b46)
    Button mB46;
    @BindView(R.id.b47)
    Button mB47;
    @BindView(R.id.b48)
    Button mB48;
    @BindView(R.id.b49)
    Button mB49;
    @BindView(R.id.b400)
    Button mB400;
    @BindView(R.id.b401)
    Button mB401;
    @BindView(R.id.b402)
    Button mB402;
    @BindView(R.id.b403)
    Button mB403;
    @BindView(R.id.b404)
    Button mB404;
    @BindView(R.id.b405)
    Button mB405;
    @BindView(R.id.b406)
    Button mB406;
    @BindView(R.id.b407)
    Button mB407;
    @BindView(R.id.b408)
    Button mB408;
    @BindView(R.id.b409)
    Button mB409;
    @BindView(R.id.b50)
    Button mB50;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
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
                mB002.setOnClickListener(
                        view1 -> {
                            view1.setVisibility(View.VISIBLE);
                            Toast.makeText(MainHomeActivity.this, view1+"222222", Toast.LENGTH_SHORT).show();
                        }
                );
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
                        mB002.setOnClickListener(
                                view1 -> {
                                    view1.setVisibility(View.VISIBLE);
                                }
                        );
                        Toast.makeText(MainHomeActivity.this, d+"222222", Toast.LENGTH_SHORT).show();

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

//                ????
                Flowable.create((FlowableOnSubscribe<String[]>) emitter -> {
                    //getIpPortWithUrl(strUrl);
                    //这里写执行耗时操作的逻辑
//                            return new String[]{};
                }, BackpressureStrategy.BUFFER);

                break;
            case R.id.b0013:

                Flowable.fromCallable(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return null;
                    }
                });
//                Observable.startFuture
                Single.fromCallable(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return null;
                    }
                });

                /*Observable<Integer> observable = Async.startFuture(new Func0<Future<Integer>>() {
                    @Override
                    public Future<Integer> call() {
                        //函数内为异步操作
                        return future;
                    }
                });*/

                break;
            case R.id.b0014:
//                startActivity(new Intent(this, RxMainActivity.class));
                // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
                ARouter.getInstance().build("/test/activity").navigation();
                break;
            case R.id.b0015:
                mB50.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }
                );
                break;
        }
    }


}
