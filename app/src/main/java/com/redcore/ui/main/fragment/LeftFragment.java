package com.redcore.ui.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redcore.ui.R;

import butterknife.ButterKnife;



public class LeftFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "LeftFragment";



    private int mCurrentCounter = 0;
    private boolean isErr = false;
    private int currentPage = 1;

    private Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_merchant_seller_center, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {

    }

    public String getFragmentName() {
        return TAG;
    }

    private void describeView() {

    }

    public void initData() {

    }


    @Override
    public void onRefresh() {

    }


}

