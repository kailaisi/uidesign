/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2012 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.tcsl.vectors.ui.detail

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v4.view.ViewPager
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import cn.com.tcsl.vectors.R
import com.viewpagerindicator.IconPagerAdapter
import com.viewpagerindicator.PageIndicator
import kotlinx.android.synthetic.main.indicator.view.*


/**
 * This widget implements the dynamic action bar tab behavior that can change
 * across different configurations or circumstances.
 */
class IconPageIndicator : HorizontalScrollView, PageIndicator {
    private val mIconsLayout: LinearLayout

    private var mViewPager: ViewPager? = null
    private var mListener: OnPageChangeListener? = null
    private var mIconSelector: Runnable? = null
    private var mSelectedIndex: Int = 0
    private var isFirst = true

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        isHorizontalScrollBarEnabled = false
        mIconsLayout = LinearLayout(context)
        mIconsLayout.clipChildren = false
        addView(mIconsLayout, FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
    }

    private fun animateToIcon(position: Int) {
        val iconView = mIconsLayout.getChildAt(position)
        if (mIconSelector != null) {
            removeCallbacks(mIconSelector)
        }
        mIconSelector = Runnable {
            //将选中的图标移动到中间位置
            var scrollPos = iconView.width * position
            smoothScrollTo(scrollPos, 0)
            mIconSelector = null
        }
        post(mIconSelector)
    }

    public override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (mIconSelector != null) {
            // Re-post the selector we saved
            post(mIconSelector)
        }
    }

    public override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (mIconSelector != null) {
            removeCallbacks(mIconSelector)
        }
    }

    override fun onPageScrollStateChanged(arg0: Int) {
        if (mListener != null) {
            mListener!!.onPageScrollStateChanged(arg0)
        }
    }

    override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {
        if (mListener != null) {
            mListener!!.onPageScrolled(arg0, arg1, arg2)
        }
    }

    override fun onPageSelected(arg0: Int) {
        setCurrentItem(arg0)
        if (mListener != null) {
            mListener!!.onPageSelected(arg0)
        }
    }

    override fun setViewPager(view: ViewPager) {
        if (mViewPager === view) {
            return
        }
        if (mViewPager != null) {
            mViewPager!!.setOnPageChangeListener(null)
        }
        val adapter = view.adapter ?: throw IllegalStateException("ViewPager does not have adapter instance.")
        mViewPager = view
        view.setOnPageChangeListener(this)
        notifyDataSetChanged()
    }

    override fun notifyDataSetChanged() {
        mIconsLayout.removeAllViews()
        isFirst = true
        val iconAdapter = mViewPager!!.adapter as IconPagerAdapter?
        val count = iconAdapter!!.count
        val inflater = LayoutInflater.from(context)
        for (i in 0 until count) {
            val root = inflater.inflate(R.layout.indicator, mIconsLayout, false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                root.icon.transitionName = "tab_" + i
            }
            root.icon.setImageResource(iconAdapter.getIconResId(i))
            if (isFirst) {
                isFirst = false
                root.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        root.viewTreeObserver.removeOnPreDrawListener(this)
                        val parentWidth = width
                        val indicatorWidth = root.width
                        val padding = (parentWidth - indicatorWidth) / 2
                        setPadding(padding, paddingTop, padding, paddingBottom)
                        return true
                    }
                })
            }
            mIconsLayout.addView(root)
            root.setOnClickListener { view -> mViewPager!!.currentItem = mIconsLayout.indexOfChild(view) }
        }
        if (mSelectedIndex > count) {
            mSelectedIndex = count - 1
        }
        setCurrentItem(mSelectedIndex)
        requestLayout()
    }

    override fun setViewPager(view: ViewPager, initialPosition: Int) {
        setViewPager(view)
        setCurrentItem(initialPosition)
    }

    override fun setCurrentItem(item: Int) {
        if (mViewPager == null) {
            throw IllegalStateException("ViewPager has not been bound.")
        }
        mSelectedIndex = item
        mViewPager!!.currentItem = item

        val tabCount = mIconsLayout.childCount
        for (i in 0 until tabCount) {
            val child = mIconsLayout.getChildAt(i)
            val isSelected = i == item
            child.isSelected = isSelected
            val foreground = child.findViewById<View>(R.id.foreground)
            if (isSelected) {
                animateToIcon(item)
                foreground.setBackgroundColor(Color.BLUE)
            } else {
                foreground.setBackgroundColor(Color.TRANSPARENT)
            }

        }
    }

    override fun setOnPageChangeListener(listener: OnPageChangeListener) {
        mListener = listener
    }
}
