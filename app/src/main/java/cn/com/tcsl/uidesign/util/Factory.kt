package cn.com.tcsl.uidesign.util

import android.support.constraint.ConstraintLayout
import android.view.View
import android.view.ViewGroup
import cn.com.tcsl.uidesign.R


/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/6 11:04
 */
object SelectedParamsFactory {
    fun startColorParams(selectedView: View): ConstraintLayout.LayoutParams {
        val colorSize = selectedView.context.resources
                .getDimensionPixelOffset(R.dimen.product_color_size)

        val layoutParams = ConstraintLayout.LayoutParams(colorSize, colorSize)

        setStartState(selectedView, layoutParams)
        return layoutParams
    }

    fun startTextParams(selectedView: View): ConstraintLayout.LayoutParams {
        val layoutParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

        setStartState(selectedView, layoutParams)
        return layoutParams
    }

    fun setStartState(selectedView: View, layoutParams: ConstraintLayout.LayoutParams) {
        layoutParams.topToTop = (selectedView.parent.parent as ViewGroup).id
        layoutParams.leftToLeft = (selectedView.parent.parent as ViewGroup).id
        layoutParams.setMargins(selectedView.x.toInt() , selectedView.y.toInt(), 0, 0)
    }

    fun endParams(v: View, targetView: View): ConstraintLayout.LayoutParams {
        val layoutParams = v.layoutParams as ConstraintLayout.LayoutParams

        val marginLeft = v.context.resources
                .getDimensionPixelOffset(R.dimen.spacing_medium)

        layoutParams.setMargins(marginLeft, 0, 0, 0)
        layoutParams.topToTop = targetView.id
        layoutParams.startToEnd = targetView.id
        layoutParams.bottomToBottom = targetView.id

        return layoutParams
    }
}