package cn.com.tcsl.material.bean

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/21 17:57
 */
abstract class BindingViewHolder<T : Any,D:ViewDataBinding>(binding: D) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(from: LayoutInflater, parent: ViewGroup, b: Int): ViewDataBinding {
            return DataBindingUtil.inflate(from, b, parent, false)
        }
    }

    abstract fun bind(bean: T)
}