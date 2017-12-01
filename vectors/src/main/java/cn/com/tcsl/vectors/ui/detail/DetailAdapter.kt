package cn.com.tcsl.vectors.ui.detail

import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.com.tcsl.vectors.BR
import cn.com.tcsl.vectors.R
import cn.com.tcsl.vectors.base.BaseAdapter
import cn.com.tcsl.vectors.base.BindingViewHolder

/**
 * 描述:详情页下面的recyclerview的adapter
 * <p/>作者：wjx
 * <p/>创建时间: 2017/12/1 14:54
 */
class DetailAdapter(mData: List<Item>) : BaseAdapter<ViewHolder>(mData) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = BindingViewHolder.create(LayoutInflater.from(parent.context), R.layout.item_page_detail, parent)
        return ViewHolder(binding)
    }
}

class ViewHolder(var binding: ViewDataBinding) : BindingViewHolder<ViewDataBinding>(binding) {
    override fun bind(t: Any) {
        binding.setVariable(BR.bean, t)
    }
}