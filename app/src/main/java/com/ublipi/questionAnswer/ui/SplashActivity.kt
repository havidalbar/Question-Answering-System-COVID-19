package com.ublipi.questionAnswer.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.ublipi.questionAnswer.R

class SplashActivity : Activity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, QaActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}