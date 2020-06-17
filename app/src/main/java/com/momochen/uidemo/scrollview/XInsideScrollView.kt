package com.momochen.uidemo.scrollview

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.ScrollView
import androidx.core.view.NestedScrollingChild
import androidx.core.view.NestedScrollingChild3
import androidx.core.view.ViewParentCompat

class XInsideScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val result = super.dispatchTouchEvent(ev)
        println("====XInsideScrollView>${ev?.actionMasked}   $result")
        return result
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        val reslut = super.onTouchEvent(ev)
        println("======XInsideScrollView onTouchEvent>$reslut ${ev?.actionMasked}")
        return reslut
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val reslut = super.onInterceptTouchEvent(ev)
        println("======XInsideScrollView onInterceptTouchEvent>$reslut   ${ev?.actionMasked}")
        return reslut
    }
}