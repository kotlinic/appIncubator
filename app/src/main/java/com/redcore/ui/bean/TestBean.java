package com.redcore.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class TestBean implements Serializable {
    String text;
    String icon;

    String desc;
    public TestBean() {
    }

    public TestBean(String text, String icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static List<TestBean> getDataList() {
        List<TestBean> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(new TestBean("TestBean.this", ""));
        }
        return list;
    }

    public static List<String> getImages() {
        List<String> images = new ArrayList<>();
        images.add("http://dmall.jixiangkeji.com/data/upload/shop/store/goods/1/1_05428877086907215.jpg");
        images.add("http://dmall.jixiangkeji.com/data/upload/shop/store/goods/1/1_05428877086907215.jpg");
        images.add("http://dmall.jixiangkeji.com/data/upload/shop/store/goods/1/1_05428877086907215.jpg");
        images.add("http://dmall.jixiangkeji.com/data/upload/shop/store/goods/1/1_05459133099729172.jpg");
        images.add("http://dmall.jixiangkeji.com/data/upload/shop/store/goods/1/1_05459133099729172.jpg");
        return images;

    }
}
