package com.momochen.uidemo.scrollview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView

class ExternalScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}