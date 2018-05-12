package com.example.nhyml.myapplication

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager
    lateinit var dotLayout: LinearLayout
    lateinit var myViewPagerAdapter: MyViewPagerAdapter
    lateinit var dots:Array<ImageView>
    var layouts: IntArray = intArrayOf(R.layout.welcome_slide1,R.layout.welcome_slide2,R.layout.welcome_slide3)
    private var btnLogin: Button?= null
    private var btnRegister: Button?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        myViewPagerAdapter = MyViewPagerAdapter(layouts,this)
        viewPager.adapter = myViewPagerAdapter
        createDots(0)
        viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                createDots(position)
            }

        });
        Thread({
            while(!Thread.interrupted()){
                if(viewPager.currentItem !=2){
                    Thread.sleep(2000)
                    viewPager.post(Runnable {
                        kotlin.run {
                            createDots(viewPager.currentItem+1)
                            viewPager.setCurrentItem(viewPager.currentItem+1)
                        }
                    })
                } else {
                    viewPager.post(Runnable {
                        kotlin.run {
                            createDots(0)
                            viewPager.setCurrentItem(0)
                        }
                    })
                }
            }

        }).start()
        btnRegister?.setOnClickListener{
            val intent = Intent(applicationContext,RegisterActivity::class.java)
            startActivity(intent)
        }
        btnLogin?.setOnClickListener{
            val intent = Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
        }

    }
    private fun createDots(currentPage:Int){
        if(dotLayout!=null){
            dotLayout.removeAllViews()
        }
        dots = Array(layouts.size,{i-> ImageView(this) })
        for (i in 0 .. layouts.size - 1){
            dots[i] = ImageView(this)
            if (i==currentPage){
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dot))
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.inactive_dot))
            }
            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setMargins(4,0,4,0)
            dotLayout.addView(dots[i],params)
        }

    }
    private fun init(){
        viewPager = findViewById(R.id.viewPager) as ViewPager
        dotLayout = findViewById(R.id.dotLayout) as LinearLayout
        btnLogin = findViewById(R.id.btnSelectLogin) as Button
        btnRegister = findViewById(R.id.btnSelectRegister) as Button

    }


}
