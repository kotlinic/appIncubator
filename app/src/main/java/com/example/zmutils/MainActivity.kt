package com.example.zmutils

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    /**
     * kotlinic
     */
    private var mGetUser: Button? = null

    /**
     * 移除用户
     */
    private var mRemoveUser: Button? = null

    /**
     * 获取文章
     */
    private var mGetArticle: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        mGetUser = findViewById<View>(R.id.getUser) as Button
        mGetUser!!.setOnClickListener(this)
        mRemoveUser = findViewById<View>(R.id.removeUser) as Button
        mRemoveUser!!.setOnClickListener(this)
        mGetArticle = findViewById<View>(R.id.getArticle) as Button
        mGetArticle!!.setOnClickListener(this)
        removeArticle!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.getUser -> {
                Toast.makeText(this@MainActivity, "getUser成功!!!", Toast.LENGTH_SHORT).show()
                Thread.sleep(3000)
                startActivity(Intent(this@MainActivity, MainActivityOld::class.java))
            }
            R.id.removeUser -> {
                removeUser.setText("Butterknife")
                startActivity(Intent(this@MainActivity, ButterknifeActivity::class.java))
            }
            R.id.getArticle -> {
                startActivity(Intent(this@MainActivity, ButterknifeKotActivity::class.java))
            }
            R.id.removeArticle -> {
                Toast.makeText(this@MainActivity, "成功!!!", Toast.LENGTH_SHORT).show()
//                Thread.sleep(2000)
            }
            else -> {
            }
        }
    }
}