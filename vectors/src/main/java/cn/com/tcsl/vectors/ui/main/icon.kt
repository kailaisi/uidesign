package cn.com.tcsl.vectors.ui.main

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import cn.com.tcsl.vectors.R
import com.viewpagerindicator.IconPagerAdapter
import com.viewpagerindicator.PageIndicator

//modified version of https://github.com/JakeWharton/ViewPagerIndicator/blob/master/library/src/com/viewpagerindicator/IconPageIndicator.java
class IconPageIndicator : HorizontalScrollView, PageIndicator {
    private var mIconsLayout: LinearLayout? = null

    private var mViewPager: ViewPager? = null
    private var mListener: ViewPager.OnPageChangeListener? = null
    private var mIconSelector: Runnable? = null
    private var mSelectedIndex: Int = 0
    private var percentExpanded: Float = 0.toFloat()

    constructor(context: Context) : super(context, null) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        isHorizontalScrollBarEnabled = false
        mIconsLayout = LinearLayout(context)
        mIconsLayout!!.clipChildren = false
        addView(mIconsLayout, FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
    }

    private fun animateToIcon(position: Int) {
        if (mIconSelector != null) {
            removeCallbacks(mIconSelector)
        }
        mIconSelector = Runnable { updateScroll() }
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
            mViewPager!!.removeOnPageChangeListener(this)
        }
        val adapter = view.adapter ?: throw IllegalStateException("ViewPager does not have adapter instance.")
        mViewPager = view
        view.addOnPageChangeListener(this)
        notifyDataSetChanged()
    }


    override fun setViewPager(view: ViewPager, initialPosition: Int) {
        setViewPager(view)
        setCurrentItem(initialPosition)
    }

    override fun notifyDataSetChanged() {
        mIconsLayout!!.removeAllViews()
        var changePadding = true
        val iconAdapter = mViewPager!!.adapter as IconPagerAdapter?
        val count = iconAdapter!!.count
        val inflater = LayoutInflater.from(context)
        for (i in 0 until count) {
            val parent = inflater.inflate(R.layout.indicator, mIconsLayout, false)
            val view = parent.findViewById<ImageView>(R.id.icon)
            //// TODO: 25/04/2017 Use ViewCompat to support pre-lollipop
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.transitionName = "tab_" + i
            }
            view.setImageResource(iconAdapter.getIconResId(i))
            if (changePadding) {
                //assuming that every indicator will have same size
                changePadding = false
                parent.getViewTreeObserver().addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        parent.getViewTreeObserver().removeOnPreDrawListener(this)
                        val parentWidth = width
                        val width = parent.getWidth()
                        val leftRightPadding = (parentWidth - width) / 2
                        setPadding(leftRightPadding, paddingTop, leftRightPadding, paddingBottom)
                        return true
                    }
                })
            }
            mIconsLayout!!.addView(parent)

            parent.setOnClickListener(OnClickListener { mViewPager!!.currentItem = mIconsLayout!!.indexOfChild(parent) })
        }
        if (mSelectedIndex > count) {
            mSelectedIndex = count - 1
        }
        setCurrentItem(mSelectedIndex)
        requestLayout()
    }

    override fun setCurrentItem(item: Int) {
        if (mViewPager == null) {
            throw IllegalStateException("ViewPager has not been bound.")
        }
        mSelectedIndex = item
        mViewPager!!.currentItem = item

        val tabCount = mIconsLayout!!.childCount
        for (i in 0 until tabCount) {
            val child = mIconsLayout!!.getChildAt(i)
            val isSelected = i == item
            child.isSelected = isSelected
            val foreground = child.findViewById<ImageView>(R.id.foreground)
            if (isSelected) {
                animateToIcon(item)
                // foreground.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.fg_white))
            } else {
                //   foreground.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.fg_gray))
            }
        }
    }

    fun collapse(top: Float, total: Float) {

        //do not scale to 0
        val newTop = top / 1.2f
        val scale = (total - newTop) / total.toFloat()
        ViewCompat.setScaleX(this, scale)
        ViewCompat.setScaleY(this, scale)
        val tabCount = mIconsLayout!!.childCount

        //alpha can be zero
        percentExpanded = (total - top) / total.toFloat()
        val alpha = 1 - percentExpanded
        for (i in 0 until tabCount) {
            val parent = mIconsLayout!!.getChildAt(i)
            val child = parent.findViewById<ImageView>(R.id.foreground)
            ViewCompat.setAlpha(child, alpha)
        }
        updateScroll()
    }

    private fun updateScroll() {
        val x = mIconsLayout!!.width / 2
        val scrollTo = (x * (1 - percentExpanded) + percentExpanded * mIconsLayout!!.getChildAt(mSelectedIndex).left).toInt()
        smoothScrollTo(scrollTo, 0)
    }

    override fun setOnPageChangeListener(listener: ViewPager.OnPageChangeListener) {
        mListener = listener
    }
}
