package cn.com.tcsl.material

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.transition.Slide
import android.view.Gravity
import cn.com.tcsl.material.bean.MyAdapter
import cn.com.tcsl.material.bean.OnItemClickListener
import cn.com.tcsl.material.bean.Sample
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setWindowsAnim()
        setToolBar()
        setRv()
    }

    private fun setWindowsAnim() {
        var slide = Slide()
        slide.duration = 500
        slide.slideEdge = Gravity.RIGHT
        window.reenterTransition = slide
        window.exitTransition = slide
        window.enterTransition = slide
    }

    private fun setRv() {
        var list = arrayListOf(Sample("武雷", R.drawable.a, R.color.sample_blue),
                Sample("测试", R.drawable.b, R.color.sample_green),
                Sample("张磊", R.drawable.c, R.color.sample_red),
                Sample("徐峥", R.drawable.d, R.color.sample_yellow),
                Sample("黄伯伯", R.drawable.e, R.color.colorPrimary))
        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv.layoutManager = manager
        mAdapter = MyAdapter(list)
        rv.adapter = mAdapter
        mAdapter.mListener = object : OnItemClickListener {
            override fun OnItemClick(position: Int) {
                var intent = Intent(this@MainActivity, TransitionActivity1::class.java)
                var compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity)
                startActivity(intent, compat.toBundle())
            }
        }
    }

    private fun setToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}
