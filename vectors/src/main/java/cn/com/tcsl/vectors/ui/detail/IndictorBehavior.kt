package cn.com.tcsl.vectors.ui.detail

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/12/5 13:39
 */
class IndictorBehavior : CoordinatorLayout.Behavior<IconPageIndicator> {
    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: IconPageIndicator?, dependency: View?): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: IconPageIndicator, dependency: View): Boolean {
        var systemWindowInsetTop = 0
        var bottom = dependency.bottom
        var center = (bottom - systemWindowInsetTop) / 2f
        var halfChild = child.height / 2f
        ViewCompat.offsetTopAndBottom(dependency, (center-halfChild).toInt())
        return true
    }
}