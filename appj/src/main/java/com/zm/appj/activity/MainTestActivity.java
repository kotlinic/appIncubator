package com.zm.appj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zm.appj.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainTestActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * kotlinic
     */
    private Button mGetUser;
    /**
     * 移除用户
     */
    private Button mRemoveUser;
    /**
     * 获取文章
     */
    private Button mGetArticle;
    /**
     * 移除文章
     */
    private Button mRemoveArticle;
    /**
     * 请求权限
     */
    private Button mPermission;
    /**
     * 重试
     */
    private Button mRetry;
    /**
     * 铺获异常
     */
    private Button mSafe;
    /**
     * 异步
     */
    private Button mAsyn;
    /**
     * 定时任务
     */
    private Button mScheduled;
    /**
     * 取消定时
     */
    private Button mCancelScheduled;
    /**
     * 延迟任务
     */
    private Button mDelay;
    /**
     * 取消延迟
     */
    private Button mCancelDelay;
    /**
     * 登录
     */
    private Button mLogin;
    /**
     * 登出
     */
    private Button mLogout;
    /**
     * 防抖
     */
    private Button mSingleClick;
    /**
     * 不防抖
     */
    private Button mSingleClick1;
    /**
     * 防抖2
     */
    private Button mSingleClick2;
    /**
     * 统计
     */
    private Button mStatistic;
    /**
     * 主线程
     */
    private Button mMainThread;
    /**
     * 拦截
     */
    private Button mIntercept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        initView();
    }

    private void initView() {
        mGetUser = (Button) findViewById(R.id.getUser);
        mGetUser.setOnClickListener(this);
        mRemoveUser = (Button) findViewById(R.id.removeUser);
        mRemoveUser.setOnClickListener(this);
        mGetArticle = (Button) findViewById(R.id.getArticle);
        mGetArticle.setOnClickListener(this);
        mRemoveArticle = (Button) findViewById(R.id.removeArticle);
        mRemoveArticle.setOnClickListener(this);
        mPermission = (Button) findViewById(R.id.permission);
        mPermission.setOnClickListener(this);
        mRetry = (Button) findViewById(R.id.retry);
        mRetry.setOnClickListener(this);
        mSafe = (Button) findViewById(R.id.safe);
        mSafe.setOnClickListener(this);
        mAsyn = (Button) findViewById(R.id.asyn);
        mAsyn.setOnClickListener(this);
        mScheduled = (Button) findViewById(R.id.scheduled);
        mScheduled.setOnClickListener(this);
        mCancelScheduled = (Button) findViewById(R.id.cancelScheduled);
        mCancelScheduled.setOnClickListener(this);
        mDelay = (Button) findViewById(R.id.delay);
        mDelay.setOnClickListener(this);
        mCancelDelay = (Button) findViewById(R.id.cancelDelay);
        mCancelDelay.setOnClickListener(this);
        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        mLogout = (Button) findViewById(R.id.logout);
        mLogout.setOnClickListener(this);
        mSingleClick = (Button) findViewById(R.id.singleClick);
        mSingleClick.setOnClickListener(this);
        mSingleClick1 = (Button) findViewById(R.id.singleClick1);
        mSingleClick1.setOnClickListener(this);
        mSingleClick2 = (Button) findViewById(R.id.singleClick2);
        mSingleClick2.setOnClickListener(this);
        mStatistic = (Button) findViewById(R.id.statistic);
        mStatistic.setOnClickListener(this);
        mMainThread = (Button) findViewById(R.id.mainThread);
        mMainThread.setOnClickListener(this);
        mIntercept = (Button) findViewById(R.id.intercept);
        mIntercept.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.getUser:
                Toast.makeText(MainTestActivity.this, "getUser成功!!!", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.removeUser:
                Toast.makeText(MainTestActivity.this, "getUser成功!!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MethodTraceManActivity.class));
                break;
            case R.id.getArticle:

                Toast.makeText(this, "已执行随机耗时方法", Toast.LENGTH_SHORT).show();
                SystemClock.sleep(3000);
                break;
            case R.id.removeArticle:
                break;
            case R.id.permission:
                break;
            case R.id.retry:
                break;
            case R.id.safe:
                break;
            case R.id.asyn:
                break;
            case R.id.scheduled:
                break;
            case R.id.cancelScheduled:
                break;
            case R.id.delay:
                break;
            case R.id.cancelDelay:
                break;
            case R.id.login:
                break;
            case R.id.logout:
                break;
            case R.id.singleClick:
                break;
            case R.id.singleClick1:
                break;
            case R.id.singleClick2:
                break;
            case R.id.statistic:
                break;
            case R.id.mainThread:
                break;
            case R.id.intercept:
                break;
        }
    }
}
