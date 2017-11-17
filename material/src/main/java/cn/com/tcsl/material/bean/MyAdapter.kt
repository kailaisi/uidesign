package cn.com.tcsl.material.bean

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.com.tcsl.material.databinding.ItemInfoBinding

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/17 14:38
 */
class MyAdapter(var mDatas: ArrayList<Sample>) : RecyclerView.Adapter<SamplesViewHolder>() {
    var mListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SamplesViewHolder {
        var binding = ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        var holder = SamplesViewHolder(binding)
        binding.root.setOnClickListener {
            mListener?.OnItemClick(holder.layoutPosition)
        }
        return holder
    }

    override fun onBindViewHolder(holder: SamplesViewHolder, position: Int) {
        holder.bind(mDatas[position])
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }
}

class SamplesViewHolder(var binding: ItemInfoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(sample: Sample) {
        binding.bean = sample
        binding.executePendingBindings()
    }
}

interface OnItemClickListener {
    fun OnItemClick(position: Int)
}