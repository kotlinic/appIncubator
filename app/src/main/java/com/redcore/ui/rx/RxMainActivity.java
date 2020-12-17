package com.redcore.ui.rx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.redcore.ui.R;

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = "/test/activity")
public class RxMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_arouter);
    }

    public void getArticle(View view) {
        view.setVisibility(View.VISIBLE);


// 2. 跳转并携带参数
        ARouter.getInstance().build("/test/abc")
                .withLong("key1", 666L)
                .withString("key3", "888")
                .withInt("key4", 123)
                .navigation();
    }
}
