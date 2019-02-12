package com.redcore.ui.rx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.redcore.ui.R;


/*retrofit rx 一次封装多次调用统一处理
        https://blog.csdn.net/sgn5200/article/details/71551472*/
public class RxConActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_con);
    }

    View.OnClickListener mOnClickListener= view -> {
    view.setVisibility(View.VISIBLE);
    };

    View.OnClickListener mOnClickListener1=new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}
