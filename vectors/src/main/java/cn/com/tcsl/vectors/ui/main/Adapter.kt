package cn.com.tcsl.vectors.ui.main

import android.databinding.BindingAdapter
import android.databinding.ViewDataBinding
import android.support.annotation.DrawableRes
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import cn.com.tcsl.vectors.BR
import cn.com.tcsl.vectors.Category
import cn.com.tcsl.vectors.R
import cn.com.tcsl.vectors.base.BaseAdapter
import cn.com.tcsl.vectors.base.BindingViewHolder

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/30 10:24
 */
class MyAdapter(mData: List<Category>) : BaseAdapter<MyViewHolder>(mData) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = BindingViewHolder.create(LayoutInflater.from(parent.context), R.layout.item_title, parent)
        var holder = MyViewHolder(binding)
        binding.root.setOnClickListener {
            var position = holder.layoutPosition
            mListener?.OnItemClick(position)
        }
        return holder
    }
}

class MyViewHolder(var binding: ViewDataBinding) : BindingViewHolder<ViewDataBinding>(binding) {
    override fun bind(t: Any) {
        binding.setVariable(BR.bean, t)
        binding.executePendingBindings()
    }
}
@BindingAdapter("android:src")
fun setImgSrc(view: ImageView,@DrawableRes icon: Int) {
    view.setImageResource(icon)
}