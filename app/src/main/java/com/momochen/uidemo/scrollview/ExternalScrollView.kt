package com.momochen.uidemo.scrollview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import com.momochen.uidemo.R
import kotlinx.android.synthetic.main.activity_scrollview1.view.*

class ExternalScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {

    var mLastY = 0

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        println("=========ExternalScrollView  dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        println("=========ExternalScrollView  onInterceptTouchEvent ${ev.toString()}")
        ev ?: return super.onInterceptTouchEvent(ev)

        var isIntercept = false

        val y = ev.rawY
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                return super.onInterceptTouchEvent(ev)
            }
            MotionEvent.ACTION_MOVE -> {
                val detY = y - mLastY

                val contentView: View? =
                    findViewById(R.id.inside_scrollview) ?: return super.onInterceptTouchEvent(ev)

                //判断子ScrollView是否滑动到顶部或者顶部
                contentView?.let { view ->
                    val isChildScrolledTop = detY > 0 && !view.canScrollVertically(-1)
                    val isChildScrolledBottom = detY < 0 && !view.canScrollVertically(1)
                    isIntercept = isChildScrolledTop || isChildScrolledBottom
                }
            }
            MotionEvent.ACTION_UP -> {
                isIntercept = false
            }
        }
        return isIntercept
    }
}