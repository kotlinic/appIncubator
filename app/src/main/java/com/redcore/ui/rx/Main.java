package com.redcore.ui.rx;


import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.Observable;

public class Main {
    static String TAG = "Main";
    @SuppressLint("CheckResult")
    public static void test() {



        /*Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("a");
                        subscriber.onNext("b");

                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String integer) {
                        System.out.println(integer);
                    }
                });*/
    }
    public static void main(String[] args) {
        test();
        Observable.fromCallable(() -> Log.d("",""));
    }

    @SuppressLint("CheckResult")
    public static void testLogEvent() {
        /*mDisposable.add(RxBus.getDefault().toFlowable(Event.AppResponse.class).observeOn(AndroidSchedulers.mainThread()).subscribe(obj -> {
            test(new Gson().toJson(obj).toString());
        }));
        Event.AppResponse appResponse = new Event.AppResponse();
        appResponse.setEventResult(200);
        appResponse.setResponseDuration(200);
        appResponse.setStrategyResult(1);
        appResponse.setUrl(appInfo.appUrl);
        RxBus.getDefault().post(appResponse);


        LogEventItem eventItem = new LogEventItem();
        eventItem.setEventType("navigator");
        eventItem.setAppType(appInfo.type);//应用类型 1:在线应用; 2: 适配应用; 3:小程序; 4: 邮箱  5.
        eventItem.setApplicationId(appInfo.appId);
        eventItem.setStrategyId(mViewModel.getUserProfile().strategy.activeId);
        eventItem.setEventStartTime(System.currentTimeMillis() + "");
        eventItem.setEventEndTime("");//
        eventItem.setUrl(appInfo.appUrl);// 请求地址
        eventItem.setResponseDuration(100);//todo (响应时间 单位 毫秒)  ------
        eventItem.setReferer("referer");
        eventItem.setStrategyResult(200);//todo 网关定义--------
        eventItem.setEventResult(1);//todo 请求结果，成功1 失败0 -------

        LogEventItem.ServerLocInfo serverLocInfo = new LogEventItem.ServerLocInfo();
        serverLocInfo.setCity("北京");
        serverLocInfo.setLocation("中国 北京 东城");
        serverLocInfo.setLatitude(39.9288);
        serverLocInfo.setLongitude(116.3889);
        serverLocInfo.setIp("219.143.153.50");
        serverLocInfo.setPort(80);
        eventItem.setServerLocInfo(serverLocInfo);
        ArrayList<LogEventItem> eventList = new ArrayList<>();
        eventList.add(eventItem);
        String events = new Gson().toJson(eventList);
        Log.e("log"+this.getClass(), events);
        EventLogViewModel eventLogViewModel = new EventLogViewModel(this.getActivity().getApplication(), null);
        String deviceInfo = new DeviceInfo().allInfo(this.getActivity().getApplication().getApplicationContext());

        String clientLocation = "{\n" +
                "      \"city\": \"北京\",\n" +
                "      \"latitude\": 39.9288,\n" +
                "      \"longitude\": 116.3889,\n" +
                "      \"ip\": \"219.143.153.55\",\n" +
                "\"port\": 80\n" +
                "    }";

        eventLogViewModel.uploadEventLog(mViewModel.getUserProfile().company.companyId, mViewModel.getUserProfile().user.userID, deviceInfo, clientLocation,
                events).doOnError(
                e -> {
                    e.printStackTrace();
                    Log.e("uploadEventLog exc", String.valueOf(e));
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<EventLogResp>() {
            @Override
            public void accept(EventLogResp eventLogResp) throws Exception {
                Log.e("home eventLogResp", String.valueOf(eventLogResp));
            }
        });
        EventLogTool.uploadLog(appInfo, this.getActivity().getApplication(), mViewModel.getUserProfile());*/
    }
}
