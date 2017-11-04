package cn.com.tcsl.uidesign.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import cn.com.tcsl.uidesign.R
import cn.com.tcsl.uidesign.adapter.OnItemClickListener
import cn.com.tcsl.uidesign.adapter.ProductAdapter
import cn.com.tcsl.uidesign.adapter.ProductItemPaddingDecoration
import cn.com.tcsl.uidesign.databinding.ActivityMainBinding
import cn.com.tcsl.uidesign.model.creatFakeProducts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRecycler()
    }

    private fun initRecycler() {
        var products = creatFakeProducts()
        var adapter = ProductAdapter(products, object : OnItemClickListener {
            override fun onClick(position: Int) {
                OrderDialogFragment.newInstance(products[position]).show(supportFragmentManager, "OrderDialogFragment")
            }
        })
        rv_products.adapter = adapter
        rv_products.addItemDecoration(ProductItemPaddingDecoration(this))
    }
}
