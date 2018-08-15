package com.redcore.ui.main.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.redcore.ui.R;
import com.redcore.ui.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

public class DailyHeadlineCommonAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    private String mType;

    public DailyHeadlineCommonAdapter() {
        super(R.layout.item_daily_headline, getSampleData(10));
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
        helper.setText(R.id.time, item.getText() + " 2017-1-22");

    }

    public static List<TestBean> getSampleData(int lenth) {
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            TestBean status = new TestBean();
            status.setText("这个宝贝不错哦 " + i);
            list.add(status);
        }
        return list;
    }
}