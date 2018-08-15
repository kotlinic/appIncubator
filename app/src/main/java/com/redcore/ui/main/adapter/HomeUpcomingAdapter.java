package com.redcore.ui.main.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.redcore.ui.R;
import com.redcore.ui.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 待办列表
 *
 */
public class HomeUpcomingAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    private String mType;

    public HomeUpcomingAdapter() {
        super(R.layout.item_home_upcoming, getSampleData(6));
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
        helper.setText(R.id.time, item.getText() + "下午 18:36");

    }

    public static List<TestBean> getSampleData(int lenth) {
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            TestBean status = new TestBean();
            status.setText(" " );
            list.add(status);
        }
        return list;
    }
}