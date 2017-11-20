package cn.com.tcsl.material.bean

import android.databinding.BindingAdapter
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.widget.ImageView
import android.widget.TextView
import java.io.Serializable

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/17 14:32
 */
data class Sample(var name: String, @DrawableRes var icon: Int, @ColorRes var bg: Int) : Serializable

@BindingAdapter("android:src")
fun setImgSrc(view: ImageView, @DrawableRes icon: Int) {
    view.setImageResource(icon)
}

@BindingAdapter("android:textColor")
fun setTextColor(view: TextView, @ColorRes color: Int) {
    view.setTextColor(view.resources.getColor(color))
}