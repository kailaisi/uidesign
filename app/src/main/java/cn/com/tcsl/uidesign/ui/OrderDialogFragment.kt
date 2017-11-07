package cn.com.tcsl.uidesign.ui

import android.databinding.BindingAdapter
import android.graphics.BitmapFactory
import android.graphics.drawable.*
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import cn.com.tcsl.uidesign.R
import cn.com.tcsl.uidesign.databinding.FragmentOrderformBinding
import cn.com.tcsl.uidesign.databinding.LayoutFormOrderStep1Binding
import cn.com.tcsl.uidesign.databinding.LayoutFormOrderStep2Binding
import cn.com.tcsl.uidesign.databinding.LayoutOrderConfirmBinding
import cn.com.tcsl.uidesign.model.Product
import cn.com.tcsl.uidesign.util.SelectedParamsFactory
import de.hdodenhof.circleimageview.CircleImageView


/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/3 13:44
 */
class OrderDialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentOrderformBinding
    lateinit var product: Product
    lateinit var selection: OrderSelection
    lateinit var selectedViewTransition: Transition
    var clonedViews = arrayListOf<View>()

    companion object {
        private val ID_SIZE_SUFFIX = "tv_size"
        private val ID_COLOR_SUFFIX = "iv_color"
        private val ID_DATE_SUFFIX = "tv_date"
        private val ID_TIME_SUFFIX = "tv_time"
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
        selectedViewTransition = TransitionInflater.from(context).inflateTransition(R.transition.transition_selected_view)
        initStepOneView(binding.layoutStep1)
    }

    private fun initStepOneView(layoutStep1: LayoutFormOrderStep1Binding?) {
        binding.btnGo.setOnClickListener {
            showDeliverForm()
        }
        layoutStep1?.listener = object : Setp1Listner {
            override fun onSizeSelected(v: View) {
                selection.size = (v as TextView).text.toString().toInt()
                v.isSelected = true
                transitionSelectedView(v)
            }

            override fun onColorSelected(v: View) {
                selection.color = ((v as ImageView).drawable as ColorDrawable).color
                v.isSelected = true
                transitionSelectedView(v)
            }

        }
    }

    private fun showDeliverForm() {
        binding.switcher.displayedChild = 1
        for (v in clonedViews) {
            binding.mainContainer.removeView(v)
        }
        initStepTwoView(binding.layoutStep2)

    }

    private fun initStepTwoView(layoutStep2: LayoutFormOrderStep2Binding?) {
        binding.txtAction.text = "Book"
        binding.btnGo.setOnClickListener { changeToConfirm() }
        layoutStep2?.listener = object : Setp2Listner {
            override fun onTimeSelected(v: View) {
                selection.time = (v as TextView).text.toString().replace("\n", " ")
                v.isSelected = true
                transitionSelectedView(v)
            }

            override fun onDateSelected(v: View) {
                selection.date = (v as TextView).text.toString().replace("\n", " ")
                v.isSelected = true
                transitionSelectedView(v)
            }
        }
    }

    private fun changeToConfirm() {
        val confirmBinding = LayoutOrderConfirmBinding.inflate(LayoutInflater.from(context), binding.mainContainer, false)
        confirmBinding.bean = product
        confirmBinding.selection = selection
        confirmBinding.executePendingBindings()
        confirmBinding.root.setBackgroundResource(product.color)
        confirmBinding.ivProduct.setImageResource(product.image)
        confirmBinding . tvColor.text= getString(R.string.txt_label_conf_color, String.format("#%06X", 0xFFFFFF and product.color))
        val sense = Scene(binding.content, confirmBinding.root as ViewGroup)
        sense.setEnterAction {
            ViewCompat.animate(confirmBinding.root)
                    .scaleX(1f).scaleY(1f)
                    .setInterpolator(OvershootInterpolator())
                    .setStartDelay(200)
                    .start()
        }
        val transition = TransitionInflater.from(context).inflateTransition(R.transition.transition_confirmation_view)
        TransitionManager.go(sense, transition)
    }

    /**
     * 根据view控件生成影像，然后进行移动
     */
    private fun transitionSelectedView(v: View) {
        val selectionView = createSelectionView(v)
        binding.mainContainer.addView(selectionView)
        clonedViews.add(selectionView)
        startAnimationView(selectionView, getTargetView(v))
    }

    private fun getTargetView(v: View): View {
        val name = resources.getResourceEntryName(v.id)
        return if (name.startsWith(ID_SIZE_SUFFIX) || name.startsWith(ID_DATE_SUFFIX))
            binding.txtLabelSize else binding.txtLabelColour
    }

    private fun startAnimationView(view: View, targetView: View) {
        view.post {
            TransitionManager.beginDelayedTransition(binding.root as ViewGroup, selectedViewTransition)
            view.layoutParams = SelectedParamsFactory.endParams(view, targetView)
        }
    }


    private fun createSelectionView(v: View): View {
        var resourceName = resources.getResourceEntryName(v.id)
        return if (resourceName.startsWith("img_color")) createColorView(v as ImageView) else
            createTextView(v)
    }

    private fun createTextView(v: View): View {
        var fakeView = TextView(context, null, 0)
        fakeView.text = (v as TextView).text
        fakeView.layoutParams = SelectedParamsFactory.startTextParams(v)
        return fakeView
    }

    private fun createColorView(imageView: ImageView): View {
        var fakeView = CircleImageView(context, null, 0)
        fakeView.setImageDrawable(imageView.drawable)
        fakeView.layoutParams = SelectedParamsFactory.startColorParams(imageView)
        return fakeView
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
    fun onDateSelected(v: View)

    fun onTimeSelected(v: View)
}

data class OrderSelection(var size: Int = 0, var color: Int = 0, var date: String = "", var time: String = "")