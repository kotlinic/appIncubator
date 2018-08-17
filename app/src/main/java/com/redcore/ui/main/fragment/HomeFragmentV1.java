package com.redcore.ui.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.redcore.ui.R;
import com.redcore.ui.bean.GlideImageLoader;
import com.redcore.ui.main.adapter.CommonAppGroupAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class HomeFragmentV1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerViewApps)
    RecyclerView mRecyclerViewApps;
    @BindView(R.id.banner)
    Banner mBanner;
//    @BindView(R.id.swipeRefreshLayout)
//    SwipeRefreshLayout mSwipeRefreshLayout;

    private CommonAppGroupAdapter mCommonAppAdapter;
    private String mType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString("type");
        }
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);

        initView();
        return rootView;
    }

    private void initView() {
        //设置图片加载器
        List<String> images = new ArrayList<>();
        images.add("http://img.zcool.cn/community/01700557a7f42f0000018c1bd6eb23.jpg");
        images.add("http://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png");
        images.add("http://img.zcool.cn/community/01ae5656e1427f6ac72531cb72bac5.jpg");
        mBanner.setImageLoader(new GlideImageLoader()).setImages(images).isAutoPlay(true).setDelayTime(1500).setIndicatorGravity(BannerConfig.CENTER).start();

//        mSwipeRefreshLayout.setOnRefreshListener(this);
//        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerViewApps.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mCommonAppAdapter = new CommonAppGroupAdapter();
        mRecyclerViewApps.setAdapter(mCommonAppAdapter);
        mRecyclerViewApps.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        mRecyclerViewApps.setHasFixedSize(true);
        mRecyclerViewApps.setNestedScrollingEnabled(false);
        initVisitorView();
    }

    private void initVisitorView() {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new HomeVisitorFragment());
        fragmentTransaction.commit();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onRefresh() {
        Toast.makeText(HomeFragmentV1.this.getActivity(), "onRefresh ", Toast.LENGTH_SHORT).show();
    }


}
