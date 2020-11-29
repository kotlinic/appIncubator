package com.redcore.ui.rx;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RxDemo {

    static String TAG = "rx";

    public static Flowable<String> getClientIP() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.ip.sb/ip").get().addHeader("cache-control", "no-cache").build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body() + "";
            Log.d("getClientIP", result);
            return Flowable.just(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("CheckResult")
    public static void test() {
        Observable.just(1, 2, 9)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        System.out.println("接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        System.out.println("hi");

        Observable<Integer> odds = Observable.just(1, 3, 5).subscribeOn(io.reactivex.schedulers.Schedulers.io());
        Observable<Integer> evens = Observable.just(2, 4, 6);
        odds.subscribe(integer -> {
            System.err.println(integer + " ---");
        });
        Observable.merge(odds, evens).subscribe(integer -> {
            System.err.println(integer);
        });
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
