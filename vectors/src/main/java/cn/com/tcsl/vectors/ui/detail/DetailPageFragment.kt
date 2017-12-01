package cn.com.tcsl.vectors.ui.detail

import cn.com.tcsl.vectors.R
import cn.com.tcsl.vectors.base.BaseFragment
import cn.com.tcsl.vectors.databinding.PageDetailBinding

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/12/1 14:17
 */
class DetailPageFragment : BaseFragment<PageDetailBinding>() {
    override fun init() {
        var list:MutableList<Item> = arrayListOf(Item("Shire&Lee2", "50", "30"),
                Item("Shire&Lee", "50", "60"),
                Item("Shire&Lee", "50", "40"),
                Item("Shire&Lee", "50", "20")
                )
        for(i in 1..100){
            list.add(Item("Shire&Lee"+i, "50", i.toString()))
        }
        var adapter = DetailAdapter(list)
        binding.rv.adapter = adapter
    }

    override fun getLayout() = R.layout.page_detail
}

class Item(var name: String, var time: String, var money: String)