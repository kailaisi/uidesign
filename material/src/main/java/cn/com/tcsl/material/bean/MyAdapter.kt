package cn.com.tcsl.material.bean

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.com.tcsl.material.R
import com.android.databinding.library.baseAdapters.BR

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/17 14:38
 */
class MyAdapter(var mDatas: ArrayList<Sample>) : RecyclerView.Adapter<SamplesViewHolder>() {
    var mListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SamplesViewHolder {
        var binding = SamplesViewHolder.creat(LayoutInflater.from(parent.context), parent, viewType)
        var holder = SamplesViewHolder(binding)
        binding.root.setOnClickListener {
            mListener?.OnItemClick(holder.adapterPosition)
        }
        return holder
    }

    override fun onBindViewHolder(holder: SamplesViewHolder, position: Int) {
        holder.bind(mDatas[position])
    }

    override fun getItemViewType(position: Int) = R.layout.item_info


    override fun getItemCount(): Int {
        return mDatas.size
    }
}

class SamplesViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun creat(from: LayoutInflater, parent: ViewGroup, b: Int): ViewDataBinding {
            return DataBindingUtil.inflate(from, b, parent, false)
        }
    }

    fun bind(sample: Sample) {
        binding.setVariable(BR.bean, sample)
        binding.executePendingBindings()
    }
}

interface OnItemClickListener {
    fun OnItemClick(position: Int)
}