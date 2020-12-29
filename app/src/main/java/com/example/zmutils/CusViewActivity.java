package com.example.zmutils;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import cusview.CustomView;

public class CusViewActivity extends AppCompatActivity {

    LinearLayout llRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_view);

        llRoot = (LinearLayout) findViewById(R.id.main_root_ll);
        llRoot.addView(new CustomView(this));
    }
}
