package cn.com.tcsl.uidesign.adapter

import android.databinding.ViewDataBinding
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.com.tcsl.uidesign.R
import cn.com.tcsl.uidesign.databinding.ItemProductBinding
import cn.com.tcsl.uidesign.model.Product

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/3 16:04
 */
class ProductAdapter(var mDatas: ArrayList<Product>, var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder? {
        var binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        var holder = ProductViewHolder(binding)
        binding.root.setOnClickListener({
            onItemClickListener.onClick(holder.layoutPosition)
        })
        return holder
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(mDatas[position])
    }

}


class ProductViewHolder(var binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(get: Product) {
        binding.bean=get
        binding.executePendingBindings()
        binding.ivProduct.background=createProductBackground(get)
        binding.ivProduct.setImageResource(get.image)
    }

    private fun createProductBackground(product: Product): GradientDrawable {
        val gradientDrawable = ContextCompat.getDrawable(
                itemView.context, R.drawable.bg_product) as GradientDrawable

        gradientDrawable.setColor(ContextCompat.getColor(
                itemView.context, product.color))

        gradientDrawable.setSize(itemView.width, getDrawableHeight())
        gradientDrawable.mutate()
        return gradientDrawable
    }

    private fun getDrawableHeight(): Int {
        val context = itemView.context

        return if (adapterPosition % 2 == 0)
            context.resources.getDimensionPixelOffset(R.dimen.product_regular_height)
        else
            context.resources.getDimensionPixelOffset(R.dimen.product_large_height)
    }
}

interface OnItemClickListener {
    fun onClick(position: Int)
}
