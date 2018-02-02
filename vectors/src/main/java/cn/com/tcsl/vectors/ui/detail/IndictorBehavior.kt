package cn.com.tcsl.vectors.ui.detail

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/12/5 13:39
 */
class IndictorBehavior : CoordinatorLayout.Behavior<IconPageIndicator> {
    val TAG = javaClass.simpleName

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    override fun layoutDependsOn(parent: CoordinatorLayout?, child: IconPageIndicator?, dependency: View?): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: IconPageIndicator, dependency: View): Boolean {
        val systemWindowInsetTop = 0
        val bottom = dependency.bottom
        val center = (bottom - systemWindowInsetTop) / 2f
        val halfChild = child.height / 2f
        Log.e(TAG, "${dependency.top},${dependency.bottom},${dependency.left},${dependency.right},${child.height}")
        //图标移动
        child.y = center - halfChild
        //图标变小
        if (dependency is AppBarLayout) {
            val totalScrollRange = dependency.totalScrollRange.toFloat()
            child.collapse((-dependency.top).toFloat(), totalScrollRange)
        }
        return true
    }
}