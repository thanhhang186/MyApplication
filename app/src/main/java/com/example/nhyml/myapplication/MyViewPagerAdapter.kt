package com.example.nhyml.myapplication

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MyViewPagerAdapter:PagerAdapter{
    lateinit var layouts: IntArray
    lateinit var inflater: LayoutInflater
    lateinit var context:Context

    constructor(layouts: IntArray, context: Context) : super() {
        this.layouts = layouts
        this.context = context
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return layouts.size
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        var view: View = inflater.inflate(layouts[position],container,false)
        container!!.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        var view: View = `object` as View
        container!!.removeView(view)
    }
}