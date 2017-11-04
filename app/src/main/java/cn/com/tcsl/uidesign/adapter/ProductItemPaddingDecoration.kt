package cn.com.tcsl.uidesign.adapter

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import cn.com.tcsl.uidesign.R


/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/3 16:33
 */
class ProductItemPaddingDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val paddingRect: Rect

    init {
        val padding = context.resources.getDimension(R.dimen.product_margin).toInt()
        paddingRect = Rect(padding, padding, padding, padding)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(paddingRect)
    }
}