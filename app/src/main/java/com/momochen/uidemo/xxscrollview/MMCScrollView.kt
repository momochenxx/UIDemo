package com.momochen.uidemo.xxscrollview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.core.view.NestedScrollingChild3
import androidx.core.view.NestedScrollingChildHelper
import androidx.core.view.ViewCompat


class MMCScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), NestedScrollingChild3 {

    private var mNestedScrollingChildHelper: NestedScrollingChildHelper =
        NestedScrollingChildHelper(this)

    private val mScrollOffset = IntArray(2)
    private val mScrollConsumed = IntArray(2)
    private var mLastMotionY = 0

    init {
        isNestedScrollingEnabled = true
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        mNestedScrollingChildHelper.isNestedScrollingEnabled = enabled
    }

    override fun isNestedScrollingEnabled(): Boolean =
        mNestedScrollingChildHelper.isNestedScrollingEnabled ?: false

    override fun startNestedScroll(axes: Int, type: Int): Boolean =
        mNestedScrollingChildHelper.startNestedScroll(axes)

    override fun stopNestedScroll() = stopNestedScroll(ViewCompat.TYPE_TOUCH)

    override fun stopNestedScroll(type: Int) =
        mNestedScrollingChildHelper.stopNestedScroll(type)

    override fun hasNestedScrollingParent(): Boolean =
        hasNestedScrollingParent(ViewCompat.TYPE_TOUCH)

    override fun hasNestedScrollingParent(type: Int): Boolean =
        mNestedScrollingChildHelper.hasNestedScrollingParent()

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int,
        consumed: IntArray
    ) {
        mNestedScrollingChildHelper.dispatchNestedScroll(
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            offsetInWindow,
            type,
            consumed
        )
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean = mNestedScrollingChildHelper.dispatchNestedScroll(
        dxConsumed,
        dyConsumed,
        dxUnconsumed,
        dyUnconsumed,
        offsetInWindow,
        type
    )

    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean = mNestedScrollingChildHelper.dispatchNestedPreScroll(
        dx,
        dy,
        consumed,
        offsetInWindow,
        type
    )

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return super.onTouchEvent(event)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                mLastMotionY = event.y.toInt()

                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, ViewCompat.TYPE_TOUCH)
            }
            MotionEvent.ACTION_MOVE -> {
                val y = event.y.toInt()
                var deltaY = mLastMotionY - y
                mLastMotionY = y

                if (dispatchNestedPreScroll(
                        0,
                        deltaY,
                        mScrollConsumed,
                        mScrollOffset,
                        ViewCompat.TYPE_TOUCH
                    )
                ) {
                    deltaY -= mScrollConsumed[1]

                    println("=====>$deltaY")
                }

                scrollBy(0, deltaY)
            }
        }
        return true
    }

    override fun scrollTo(_x: Int, _y: Int) {
        var y = _y
        if (y < 0) {
            y = 0
        }
        super.scrollTo(_x, y)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}