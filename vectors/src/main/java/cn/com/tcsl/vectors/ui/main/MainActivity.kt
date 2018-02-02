package cn.com.tcsl.vectors.ui.main

import android.content.Intent
import android.os.Build
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.util.Pair
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.view.View
import cn.com.tcsl.vectors.*
import cn.com.tcsl.vectors.base.BaseActivity
import cn.com.tcsl.vectors.base.OnItemClickListener
import cn.com.tcsl.vectors.databinding.ActivityMainBinding
import cn.com.tcsl.vectors.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_title.view.*

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayout() = R.layout.activity_main

    override fun init() {
        val manager = GridLayoutManager(this, 3)
        binding.recyclerView.layoutManager = manager
        val mAdapter = MyAdapter(Categories.categories)
        binding.recyclerView.adapter = mAdapter
        mAdapter.mListener = object : OnItemClickListener {
            override fun OnItemClick(position: Int) {
                var intent = Intent(this@MainActivity, DetailActivity::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()
                    val lastVisibleItemPosition = manager.findLastVisibleItemPosition()
                    val list = ArrayList<Pair<View, String>>()
                    for (i in firstVisibleItemPosition..lastVisibleItemPosition) {
                        var holder = binding.recyclerView.findViewHolderForLayoutPosition(i) as MyViewHolder
                        var imageView = holder.binding.root.iv_head
                        list.add(Pair(imageView, "tab_" + i))
                    }
                    intent.putExtra("position", position)
                    var arrayOfPairs = arrayOfNulls<Pair<View, String>>(list.size)
                    list.toArray(arrayOfPairs)
                    var bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, *arrayOfPairs).toBundle()
                    startActivity(intent, bundle)
                } else {
                    startActivity(intent)
                }
            }
        }

        binding.vp.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                val bg = when (position) {1 -> R.drawable.bg_blue
                    2 -> R.drawable.bg_red
                    else -> R.drawable.bg_yellow
                }
                return PageFragment.newInstance(bg)
            }

            override fun getCount() = 3
        }
        binding.vp.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {

        })
    }
}