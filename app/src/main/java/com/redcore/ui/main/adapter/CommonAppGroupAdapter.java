package com.redcore.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.redcore.ui.MainHomeActivity;
import com.redcore.ui.R;
import com.redcore.ui.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

public class CommonAppGroupAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    private String mType;

    public CommonAppGroupAdapter() {
        super(R.layout.item_home_common_app_group, getSampleData(4));
    }


    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
//        helper.getConvertView().setBackgroundResource(R.drawable.card_click);
//        helper.setText(R.id.tweetName, item.getUserName())
//                .setText(R.id.tweetText, item.getText())
//                .setText(R.id.tweetDate, item.getCreatedAt())
//                .setVisible(R.id.tweetRT, item.isRetweet())
//                .addOnClickListener(R.id.tweetAvatar)
//                .addOnClickListener(R.id.tweetName)
//                .addOnLongClickListener(R.id.tweetText)
//
        helper.setText(R.id.header, item.getText());
        Context context=helper.itemView.getContext();
        RecyclerView mRecyclerViewApps=helper.itemView.findViewById(R.id.recyclerViewApps);
        mRecyclerViewApps.setLayoutManager(new GridLayoutManager(context,4));
        mRecyclerViewApps.setAdapter(new CommonAppAdapter());
        mRecyclerViewApps.addOnItemTouchListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mRecyclerViewApps.getContext(), "每日头条" + position, Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, MainHomeActivity.class));
            }
        });
        mRecyclerViewApps.setHasFixedSize(true);
        mRecyclerViewApps.setNestedScrollingEnabled(false);
    }

    public static List<TestBean> getSampleData(int lenth) {
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            TestBean status = new TestBean();
            status.setText("新建事" + i);
            list.add(status);
        }
        return list;
    }
}