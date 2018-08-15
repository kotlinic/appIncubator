package com.redcore.ui.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

public class HomeAppSection extends SectionEntity<TestBean> {
    private boolean isMore;
    public HomeAppSection(boolean isHeader, String header, boolean isMroe) {
        super(isHeader, header);
        this.isMore = isMroe;
    }

    public HomeAppSection(TestBean t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }
}
