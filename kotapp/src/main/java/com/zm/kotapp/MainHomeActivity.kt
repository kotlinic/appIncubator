package com.zm.kotapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home)
//        Toast.makeText(this, "每日头条" + 12, Toast.LENGTH_SHORT).show();
    }
}
