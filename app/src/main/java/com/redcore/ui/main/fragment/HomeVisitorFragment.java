package com.redcore.ui.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.redcore.ui.main.adapter.DailyHeadlineCommonAdapter;
import com.redcore.ui.main.adapter.HomeUpcomingAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class HomeVisitorFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerViewNews)
    RecyclerView mRecyclerViewNews;
    @BindView(R.id.recyclerViewUpcoming)
    RecyclerView mRecyclerViewUpcoming;
    private DailyHeadlineCommonAdapter mDailyHeadlineCommonAdapter;
    private HomeUpcomingAdapter mHomeUpcomingAdapter;
    private String mType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString("type");
        }
        View rootView = inflater.inflate(R.layout.include_home_news, container, false);
        ButterKnife.bind(this, rootView);

        initView();


        return rootView;
    }

    private void initView() {
        //红芯要闻
        mRecyclerViewNews.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mDailyHeadlineCommonAdapter = new DailyHeadlineCommonAdapter();
        mDailyHeadlineCommonAdapter.setType(mType);
//        mRecyclerViewNews.addItemDecoration(new ItemTouchHelper());
        mRecyclerViewNews.setAdapter(mDailyHeadlineCommonAdapter);
        mRecyclerViewNews.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(HomeVisitorFragment.this.getActivity(), "每日头条 红芯要闻" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerViewNews.setHasFixedSize(true);
        mRecyclerViewNews.setNestedScrollingEnabled(false);
//
        mRecyclerViewUpcoming.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mHomeUpcomingAdapter = new HomeUpcomingAdapter();
        mHomeUpcomingAdapter.setType(mType);
        mRecyclerViewUpcoming.setAdapter(mHomeUpcomingAdapter);
        mRecyclerViewUpcoming.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(HomeVisitorFragment.this.getActivity(), "每日头条 红芯要闻" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerViewUpcoming.setHasFixedSize(true);
        mRecyclerViewUpcoming.setNestedScrollingEnabled(false);
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
        Toast.makeText(HomeVisitorFragment.this.getActivity(), "onRefresh ", Toast.LENGTH_SHORT).show();
    }


}
