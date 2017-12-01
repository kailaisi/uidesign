package cn.com.tcsl.vectors.ui.detail

import cn.com.tcsl.vectors.R
import cn.com.tcsl.vectors.base.BaseActivity
import cn.com.tcsl.vectors.databinding.ActivityDetailBinding

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/30 15:54
 */
class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    override fun getLayout() = R.layout.activity_detail

    override fun init() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.vp.adapter = VpAdapter(this, supportFragmentManager)
        binding.indicator.setViewPager(binding.vp)
    }
}