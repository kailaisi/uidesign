package cn.com.tcsl.vectors.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/30 10:18
 */
abstract class BindingViewHolder<T: ViewDataBinding>(binding: T) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(inflater: LayoutInflater, layout: Int, parent: ViewGroup): ViewDataBinding {
            return DataBindingUtil.inflate(inflater, layout, parent, false)
        }
    }

    abstract fun bind(t: Any)
}