package cn.com.tcsl.vectors.base

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/30 10:26
 */
abstract class BaseAdapter<E : BindingViewHolder<ViewDataBinding>>(var mDatas: List<Any>) : RecyclerView.Adapter<E>() {
    lateinit var binding: ViewDataBinding
    var mListener: OnItemClickListener? = null
    override fun onBindViewHolder(holder: E, position: Int) {
        holder.bind(mDatas[position])
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): E
}

interface OnItemClickListener {
    fun OnItemClick(position: Int)
}