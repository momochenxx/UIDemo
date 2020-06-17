package com.momochen.uidemo.scrollview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import com.momochen.uidemo.R
import kotlinx.android.synthetic.main.activity_scrollview1.view.*

class XExternalScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {

    private var isFirstIntercept = true

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val result = super.dispatchTouchEvent(ev)
        println("======dispatchTouchEvent>${ev?.actionMasked}   $result")
        return result
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {

        if (ev?.actionMasked == MotionEvent.ACTION_DOWN) {
            isFirstIntercept = true
        }

        val result = super.onInterceptTouchEvent(ev)
        println("====onInterceptTouchEvent>${ev?.actionMasked}    $result")

        if (result && isFirstIntercept) {
            isFirstIntercept = false
            return false
        }

        return result
    }
}