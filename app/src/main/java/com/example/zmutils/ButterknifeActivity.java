package com.example.zmutils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ButterknifeActivity extends AppCompatActivity {

    @BindView(R.id.getUser)
    Button mGetUser;
    @BindView(R.id.removeUser)
    Button mRemoveUser;
    @BindView(R.id.getArticle)
    Button mGetArticle;
    @BindView(R.id.removeArticle)
    Button mRemoveArticle;
    @BindView(R.id.permission)
    Button mPermission;
    @BindView(R.id.retry)
    Button mRetry;
    @BindView(R.id.safe)
    Button mSafe;
    @BindView(R.id.asyn)
    Button mAsyn;
    @BindView(R.id.scheduled)
    Button mScheduled;
    @BindView(R.id.cancelScheduled)
    Button mCancelScheduled;
    @BindView(R.id.delay)
    Button mDelay;
    @BindView(R.id.cancelDelay)
    Button mCancelDelay;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.logout)
    Button mLogout;
    @BindView(R.id.singleClick)
    Button mSingleClick;
    @BindView(R.id.singleClick1)
    Button mSingleClick1;
    @BindView(R.id.singleClick2)
    Button mSingleClick2;
    @BindView(R.id.statistic)
    Button mStatistic;
    @BindView(R.id.mainThread)
    Button mMainThread;
    @BindView(R.id.intercept)
    Button mIntercept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @butterknife.OnClick({R.id.getUser, R.id.removeUser, R.id.getArticle, R.id.removeArticle, R.id.permission, R.id.retry, R.id.safe, R.id.asyn, R.id.scheduled, R.id.cancelScheduled, R.id.delay, R.id.cancelDelay, R.id.login, R.id.logout, R.id.singleClick, R.id.singleClick1, R.id.singleClick2, R.id.statistic, R.id.mainThread, R.id.intercept})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.getUser:
                Toast.makeText(ButterknifeActivity.this, v+"222222", Toast.LENGTH_SHORT).show();
                break;
            case R.id.removeUser:
                break;
            case R.id.getArticle:
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
