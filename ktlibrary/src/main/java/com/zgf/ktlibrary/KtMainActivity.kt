package com.zgf.ktlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class KtMainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt_main)

        initView()
    }

    private fun initView() {
        val tvTest = findViewById<TextView>(R.id.kt_tv_test)
        tvTest.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.kt_tv_test -> {
                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
            }
        }
    }
}