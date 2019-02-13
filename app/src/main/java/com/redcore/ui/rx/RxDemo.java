package com.redcore.ui.rx;

import android.annotation.SuppressLint;
import android.util.Log;

import org.reactivestreams.Publisher;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxDemo {

    static String TAG = "rx";

    @SuppressLint("CheckResult")
    public static void test() {
        Observable.just(1, 2, 3)
                .delay(3, TimeUnit.SECONDS) // 延迟3s再发送，由于使用类似，所以此处不作全部展示
                .subscribe(new Observer<Integer>() {
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
                });

        System.out.println("hi");

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
        Flowable.just(0, 1).flatMap(new Function<Integer, Publisher<?>>() {
            @Override
            public Flowable<Integer> apply(Integer integer) throws Exception {
                return Flowable.just(integer);
            }
        }).subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Log.i(TAG, "subscribe:" + Thread.currentThread().getName()));
    }

    public static void main(String[] args) {
        test();
        String[] res = getIpPortWithUrl("https://blog.csdn.net:3006/u013072976/article/details/79074687");
        System.out.println(res[0] + res[1]);
        System.out.println(parseHostGetIPAddress("blog.csdn.net").toString());
    }

    /**
     * @param strUrl
     * @return
     */
    public static String[] getIpPortWithUrl(String strUrl) {
        URL url;
        String ipAddress = "", host;
        int port = 0;
        InetAddress returnStr1;
        try {
            url = new URL(strUrl);
            host = url.getHost();
            port = url.getPort() == -1 ? url.getDefaultPort() : url.getPort();
            returnStr1 = java.net.InetAddress.getByName(host);
            ipAddress = returnStr1.getHostAddress();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return new String[]{ipAddress, port + ""};
    }

    /**
     * 解析域名获取IP数组
     *
     * @param host
     * @return
     */
    public static String[] parseHostGetIPAddress(String host) {
        String[] ipAddressArr = null;
        try {
            InetAddress[] inetAddressArr = InetAddress.getAllByName(host);
            if (inetAddressArr != null && inetAddressArr.length > 0) {
                ipAddressArr = new String[inetAddressArr.length];
                for (int i = 0; i < inetAddressArr.length; i++) {
                    ipAddressArr[i] = inetAddressArr[i].getHostAddress();
                    System.out.println(ipAddressArr[i]);
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
        return ipAddressArr;
    }

}
