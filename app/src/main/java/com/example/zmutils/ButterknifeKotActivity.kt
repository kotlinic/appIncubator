package com.example.zmutils

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder

class ButterknifeKotActivity : AppCompatActivity() {

    @BindView(R.id.getUser)
    lateinit var one: Button;
    var unbinder: Unbinder? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //注册ButterKnife
        unbinder = ButterKnife.bind(this)
    }

    @OnClick(R.id.getUser, R.id.removeUser, R.id.getArticle)
    fun onClick(view: View) {
        when (view.id) {
            R.id.getUser -> {
                Toast.makeText(this, "one ButterknifeKot", Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder!!.unbind()
    }

}
