package cn.com.tcsl.material.bean

import android.arch.lifecycle.LiveData
import java.math.BigDecimal

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/21 9:13
 */
class StoreLiveData(var s:String):LiveData<BigDecimal>() {
    val mListener=object :SimplePriceListener{
        override fun onPriceChanged(bigDecimal: BigDecimal) {

        }
    }
    init {

    }

    override fun onActive() {
        super.onActive()

    }
}

interface SimplePriceListener {
    fun onPriceChanged(bigDecimal: BigDecimal)
}
