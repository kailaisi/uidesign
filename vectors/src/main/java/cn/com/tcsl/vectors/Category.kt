package cn.com.tcsl.vectors

import android.support.annotation.ColorRes
import java.util.*

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/30 11:23
 */
object Categories {
    val categories = generateItems()

    private fun generateItems(): List<Category> {
        val rand = Random()
        val items = ArrayList<Category>()
        for (i in 0..5) {
            items.add(Category("title:" + i, getImage(rand.nextInt(4))))
        }
        return items
    }

    private fun getImage(position: Int): Int {
        return when (position) {
            0 -> R.drawable.a
            1 -> R.drawable.b
            2 -> R.drawable.c
            3 -> R.drawable.d
            else -> R.drawable.e
        }
    }

}

class Category constructor(val title: String, @ColorRes val image: Int)

