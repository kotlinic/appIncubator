package com.example.zmutils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityOld extends AppCompatActivity implements View.OnClickListener {

    /**
     * kotlinic
     */
    private Button mGetUser;
    /**
     * 移除用户
     */
    private Button mRemoveUser;
    /**
     * 获取文章
     */
    private Button mGetArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mGetUser = (Button) findViewById(R.id.getUser);
        mGetUser.setOnClickListener(this);
        mRemoveUser = (Button) findViewById(R.id.removeUser);
        mRemoveUser.setOnClickListener(this);
        mGetArticle = (Button) findViewById(R.id.getArticle);
        mGetArticle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.getUser:
                break;
            case R.id.removeUser:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.getArticle:
                break;
        }
    }
}
