package com.example.nhyml.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    private val TIME_DELAY: Long  = 1000;
    private var mDelayHandler: Handler? = null

    internal val mRunnable: Runnable = Runnable {
        if(!isFinishing){
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable,TIME_DELAY)

    }

    override fun onDestroy() {
        if(mDelayHandler!=null){
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

}
