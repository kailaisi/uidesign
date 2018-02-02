package cn.com.tcsl.vectors.ui.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import cn.com.tcsl.vectors.R
import cn.com.tcsl.vectors.base.BaseFragment
import cn.com.tcsl.vectors.databinding.PageBinding

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/30 14:54
 */
class PageFragment : BaseFragment<PageBinding>() {
    companion object {
        fun newInstance(bg: Int): PageFragment {
            var fragment = PageFragment()
            var bundle = Bundle()
            bundle.putInt("bg", bg)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun init() {
        binding.root.background = ContextCompat.getDrawable(context!!, arguments!!.getInt("bg"))
    }

    override fun getLayout() = R.layout.page
}