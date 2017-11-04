package cn.com.tcsl.uidesign.ui

import android.databinding.BindingAdapter
import android.graphics.BitmapFactory
import android.graphics.drawable.*
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.content.ContextCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cn.com.tcsl.uidesign.R
import cn.com.tcsl.uidesign.databinding.FragmentOrderformBinding
import cn.com.tcsl.uidesign.databinding.LayoutFormOrderStep1Binding
import cn.com.tcsl.uidesign.model.Product


/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/3 13:44
 */
class OrderDialogFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentOrderformBinding
    lateinit var product: Product
    lateinit var selection: OrderSelection

    companion object {
        private val ARG_PRODUCT = "ARG_PRODUCT"
        open fun newInstance(product: Product): OrderDialogFragment {
            val args = Bundle()
            args.putSerializable(ARG_PRODUCT, product)
            val orderFragment = OrderDialogFragment()
            orderFragment.arguments = args
            return orderFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOrderformBinding.inflate(inflater, container, false)
        product = arguments.get(ARG_PRODUCT) as Product
        binding.product = product
        binding.executePendingBindings()
        binding.imgProduct.setImageDrawable(createProductImageDrawable(product))
        binding.btnGo.background = ColorDrawable(ContextCompat.getColor(context, product.color))
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selection = OrderSelection()
        initStepOneView(binding.layoutStep1)
    }

    private fun initStepOneView(layoutStep1: LayoutFormOrderStep1Binding?) {
        binding.btnGo.setOnClickListener {
            binding.txtAction.text = "Book"

        }
        layoutStep1?.listener = object : Setp1Listner {
            override fun onSizeSelected(v: View) {
                if (selection.size==0){
                    selection.size=(v as TextView).text.toString().toInt()
                    v.isSelected=true
                    transitionSelectedView(v)
                }
            }

            override fun onColorSelected(v: View) {

                if (selection.color==0){
                    selection.color=((v as ImageView).drawable as ColorDrawable).color
                    v.isSelected=true
                    transitionSelectedView(v)
                }
            }

        }
    }

    private fun transitionSelectedView(v: View) {
    }

    private fun createProductImageDrawable(product: Product): Drawable? {
        var background = ShapeDrawable()
        background.shape = OvalShape()
        background.paint.color = ContextCompat.getColor(context, product.color)
        var bitmapDrawable = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, product.image))
        var layerDrawable = LayerDrawable(arrayOf(background, bitmapDrawable))
        val padding = resources.getDimension(R.dimen.spacing_huge).toInt()
        layerDrawable.setLayerInset(1, padding, padding, padding, padding)
        return layerDrawable
    }
}

@BindingAdapter("app:spanOffset")
fun setItemSpan(v: View, spanOffset: Int) {
    val itemText = (v as TextView).text.toString()
    val sString = SpannableString(itemText)

    sString.setSpan(RelativeSizeSpan(1.75f), itemText.length - spanOffset, itemText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    v.text = sString
}

interface Setp1Listner {
    fun onSizeSelected(v: View)

    fun onColorSelected(v: View)
}

interface Setp2Listner {
    fun onSizeSelected(v: View)

    fun onColorSelected(v: View)
}

data class OrderSelection(var size: Int = 0, var color: Int = 0, var data: String = "", var time: String = "")