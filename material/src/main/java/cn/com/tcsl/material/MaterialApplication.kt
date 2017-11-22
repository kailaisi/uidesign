package cn.com.tcsl.material

import android.app.Application
import android.databinding.DataBindingUtil
import cn.com.tcsl.material.bean.NormalComponent
import cn.com.tcsl.material.bean.TestComponent

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/22 17:51
 */
class MaterialApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            DataBindingUtil.setDefaultComponent(TestComponent())
        } else {
            DataBindingUtil.setDefaultComponent(NormalComponent())
        }
    }
}