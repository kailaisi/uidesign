package cn.com.tcsl.material.bean

import android.databinding.BindingAdapter
import android.databinding.DataBindingComponent
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.widget.ImageView
import cn.com.tcsl.material.R
import java.io.Serializable

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/17 14:32
 */
data class Sample(var name: String, @DrawableRes var icon: Int, @ColorInt var bg: Int) : Serializable


abstract class MyBindingAdapter {
    @BindingAdapter("android:src")
    abstract fun setImgSrc(view: ImageView, @DrawableRes icon: Int)
}

class NormalBindingAdapter : MyBindingAdapter() {
    override fun setImgSrc(view: ImageView, icon: Int) {
        view.setImageResource(icon)
    }
}

class TestBindingAdapter : MyBindingAdapter() {
    override fun setImgSrc(view: ImageView, icon: Int) {
        if (icon == R.drawable.c) {
            view.setImageResource(R.drawable.test)
        } else {
            view.setImageResource(icon)
        }
    }
}

class NormalComponent : DataBindingComponent {
    override fun getMyBindingAdapter() = NormalBindingAdapter()
}

class TestComponent : DataBindingComponent {
    override fun getMyBindingAdapter() = TestBindingAdapter()
}