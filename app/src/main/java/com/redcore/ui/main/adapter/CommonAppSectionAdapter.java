package com.redcore.ui.main.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.redcore.ui.R;
import com.redcore.ui.bean.HomeAppSection;
import com.redcore.ui.bean.TestBean;

import java.util.ArrayList;
import java.util.List;


public class CommonAppSectionAdapter extends BaseSectionQuickAdapter<HomeAppSection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public CommonAppSectionAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data==null?getSampleData():data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final HomeAppSection item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
    }


    @Override
    protected void convert(BaseViewHolder helper, HomeAppSection item) {
        TestBean video = (TestBean) item.t;
//        switch (helper.getLayoutPosition() %
//                2) {
//            case 0:
//                helper.setImageResource(R.id.iv, R.drawable.good);
//                break;
//            case 1:
//                helper.setImageResource(R.id.iv, R.drawable.close);
//                break;
//
//        }
        helper.setText(R.id.title, video.getText()+helper.getPosition());
    }

    public static List<HomeAppSection> getSampleData() {
        List<HomeAppSection> list = new ArrayList<>();
        list.add(new HomeAppSection(true, "常用应用1", false));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(true, "常用应用2", false));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(true, "常用应用3", false));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(true, "常用应用4", false));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(true, "常用应用5", false));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        list.add(new HomeAppSection(new TestBean("新建事", "icon")));
        return list;
    }
}
