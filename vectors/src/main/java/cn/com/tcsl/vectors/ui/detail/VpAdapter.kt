package cn.com.tcsl.vectors.ui.detail

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import cn.com.tcsl.vectors.Categories
import com.viewpagerindicator.IconPagerAdapter

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/12/1 15:39
 */
class VpAdapter(var context: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm), IconPagerAdapter {
    override fun getIconResId(index: Int) = Categories.categories[index].image

    override fun getItem(position: Int): Fragment {
        return DetailPageFragment()
    }

    override fun getCount() = Categories.categories.size
}