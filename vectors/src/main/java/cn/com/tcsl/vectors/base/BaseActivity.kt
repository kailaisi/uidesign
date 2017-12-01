package cn.com.tcsl.vectors.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/30 10:06
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        }
        binding = DataBindingUtil.setContentView(this, getLayout())
        init()
    }

    abstract fun init()

    abstract fun getLayout(): Int
}