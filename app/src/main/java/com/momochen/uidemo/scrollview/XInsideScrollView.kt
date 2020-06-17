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
    private var mLastY = 0F

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        println("=========InsideScrollView  dispatchTouchEvent")
        ev ?: return super.dispatchTouchEvent(ev)

        val y = ev.y
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> parent.requestDisallowInterceptTouchEvent(true)
            MotionEvent.ACTION_MOVE -> {
                val detY = y - mLastY
                val isScrolledTop = detY > 0 && !canScrollVertically(-1)
                val isScrolledBottom = detY < 0 && !canScrollVertically(1)
                //根据自身是否滑动到顶部或者顶部来判断让父View拦截触摸事件
                if (isScrolledTop || isScrolledBottom) {
                    parent.requestDisallowInterceptTouchEvent(false)
                }
            }
        }
        mLastY = y
        return super.dispatchTouchEvent(ev);
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