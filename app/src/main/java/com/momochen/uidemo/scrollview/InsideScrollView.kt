package com.momochen.uidemo.scrollview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView

class InsideScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {
    private var mLastY = 0F

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        println("=========InsideScrollView  dispatchTouchEvent")
        ev ?: return super.dispatchTouchEvent(ev)

        val y = ev.y
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val detY = y - mLastY
                val isScrolledTop = detY > 0 && !canScrollVertically(-1)
                val isScrolledBottom = detY < 0 && !canScrollVertically(1)
                //根据自身是否滑动到顶部或者顶部来判断让父View拦截触摸事件
                if (isScrolledTop || isScrolledBottom) {
                    parent.requestDisallowInterceptTouchEvent(false);
                }
            }
        }
        mLastY = y
        return super.dispatchTouchEvent(ev);
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        println("=========InsideScrollView  onInterceptTouchEvent ${ev.toString()}")
//        if (ev?.actionMasked == MotionEvent.ACTION_DOWN) {
//            super.onInterceptTouchEvent(ev)
//            return false
//        }
//
//        return true

        return super.onInterceptTouchEvent(ev)
    }
}