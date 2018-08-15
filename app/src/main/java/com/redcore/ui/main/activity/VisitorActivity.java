package com.redcore.ui.main.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redcore.ui.R;
import com.redcore.ui.main.fragment.DailyHeadlineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 访客记录
 */
public class VisitorActivity extends FragmentActivity {

    @BindView(R.id.fragment_container)
    RelativeLayout mFragmentContainer;
    @BindView(R.id.tv_header_center)
    TextView mTvHeaderCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_headline);
        ButterKnife.bind(this);
        mTvHeaderCenter.setText("访客记录");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        DailyHeadlineFragment shareEarnFragment = new DailyHeadlineFragment();
        fragmentTransaction.add(R.id.fragment_container, new DailyHeadlineFragment());
        fragmentTransaction.commit();
    }

    @OnClick({R.id.iv_back, R.id.tv_header_center, R.id.tv_header_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_header_center:
                break;
            case R.id.tv_header_right:
                break;
        }
    }
}
