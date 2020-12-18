package com.zm.kotapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_home.*

class MainHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home)
//        Toast.makeText(this, "每日头条" + 12, Toast.LENGTH_SHORT).show();
        getUser.setText("好好好")
        getUser.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainHomeActivity, TabledActivity::class.java))
            }
        })
        removeUser.setText("好")
        removeUser.setOnClickListener { v ->
            Toast.makeText(this@MainHomeActivity, "登录成功!!!", Toast.LENGTH_SHORT).show()
        }
    }
}
