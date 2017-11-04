package cn.com.tcsl.uidesign.model

import cn.com.tcsl.uidesign.R
import java.io.Serializable

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/3 15:44
 */
data class Product(var name: String, var price: String, var image: Int, var color: Int):Serializable


fun creatFakeProducts(): ArrayList<Product> {
    return arrayListOf(Product("Shooting Stars", "$ 45", R.drawable.img_sneaker, R.color.product_yellow),
            Product("Pictures in Sky", "$ 575", R.drawable.img_sandal, R.color.product_green),
            Product("The basics of buying a telescope", "$ 892", R.drawable.img_shoe, R.color.product_blue),
            Product("The skyrider", "$ 23", R.drawable.img_ice_skate, R.color.product_purple))
}