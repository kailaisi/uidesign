package cn.com.tcsl.vectors.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/30 10:06
 */
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    lateinit var binding: T
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        init()
        return binding.root
    }

    abstract fun init()

    abstract fun getLayout(): Int
}