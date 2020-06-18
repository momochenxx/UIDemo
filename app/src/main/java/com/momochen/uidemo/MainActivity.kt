package com.momochen.uidemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.momochen.uidemo.scrollview.ScrollView1Activity
import com.momochen.uidemo.scrollview.ScrollView2Activity
import com.momochen.uidemo.xxscrollview.ScrollView3Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_1.setOnClickListener {
            Intent(
                this,
                ScrollView1Activity::class.java
            ).apply { startActivity(this) }
        }

        btn_2.setOnClickListener {
            Intent(
                this,
                ScrollView2Activity::class.java
            ).apply { startActivity(this) }
        }

        btn_3.setOnClickListener {
            Intent(
                this,
                ScrollView3Activity::class.java
            ).apply { startActivity(this) }
        }
    }
}