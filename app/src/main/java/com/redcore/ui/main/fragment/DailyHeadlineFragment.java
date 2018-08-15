package com.redcore.ui.main.fragment;

import android.content.Intent;
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
import com.redcore.ui.MainHomeActivity;
import com.redcore.ui.R;
import com.redcore.ui.main.adapter.DailyHeadlineCommonAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class DailyHeadlineFragment extends Fragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.myRecyclerView)
    RecyclerView mRecyclerView;
    private DailyHeadlineCommonAdapter mDailyHeadlineCommonAdapter;
    private String mType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString("type");
        }
        View rootView = inflater.inflate(R.layout.fragment_tab_shareearn, container, false);
        ButterKnife.bind(this, rootView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mDailyHeadlineCommonAdapter = new DailyHeadlineCommonAdapter();
        mDailyHeadlineCommonAdapter.setType(mType);
        mDailyHeadlineCommonAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        mDailyHeadlineCommonAdapter.setOnLoadMoreListener(this,mRecyclerView);
//        mRecyclerView.addItemDecoration(new ItemTouchHelper());
        mRecyclerView.setAdapter(mDailyHeadlineCommonAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(DailyHeadlineFragment.this.getActivity(), "每日头条详情H5" + position , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DailyHeadlineFragment.this.getActivity(), MainHomeActivity.class));
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                switch (view.getId()) {
                    case R.id.center_vertical:
                        Toast.makeText(DailyHeadlineFragment.this.getActivity(), "The " + Integer.toString(position) + " tweetAvatar  is clicked", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }

            }
        });
        return rootView;
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
        Toast.makeText(DailyHeadlineFragment.this.getActivity(), "onRefresh ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreRequested() {
        Toast.makeText(DailyHeadlineFragment.this.getActivity(), "onLoadMoreRequested ", Toast.LENGTH_SHORT).show();
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
//                mDailyHeadlineCommonAdapter.loadMoreEnd(true);
                mDailyHeadlineCommonAdapter.loadMoreComplete();
            }

        }, 100);

//        mDailyHeadlineCommonAdapter.loadMoreEnd(true);
//        mDailyHeadlineCommonAdapter.loadMoreComplete();
    }
}
