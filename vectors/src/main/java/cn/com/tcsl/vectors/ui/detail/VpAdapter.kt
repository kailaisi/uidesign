package cn.com.tcsl.vectors.ui.detail

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import cn.com.tcsl.vectors.Categories

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/12/1 15:39
 */
class VpAdapter(context: Context, var fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        var fragment = DetailPageFragment::class.java.newInstance()
        return fragment
    }

    override fun getCount() = Categories.categories.size
}