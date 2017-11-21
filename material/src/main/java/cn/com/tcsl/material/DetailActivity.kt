package cn.com.tcsl.material

import android.arch.lifecycle.LiveData
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionSet
import android.view.ViewAnimationUtils
import cn.com.tcsl.material.bean.Sample
import cn.com.tcsl.material.databinding.ActivityDetailBinding
import com.safframework.log.e
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/17 16:41
 */
class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var txt:LiveData<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        val sample = intent.extras.getSerializable("sample") as Sample
        binding.bean = sample
        binding.executePendingBindings()
        setWindowsAnimations()
        tv_des.text = resources.getString(R.string.des)
        for(i in 1..100){

        }
    }

    private fun setWindowsAnimations() {
        val transition = window.sharedElementEnterTransition
    }

    /**
     * 扩展颜色
     */
    private fun performCircleReveal() {
        var animator = ViewAnimationUtils.createCircularReveal(root, coll.measuredWidth / 2, coll.measuredHeight / 2, getCenter(), getRadus())
        animator.duration = 1000
        animator.start()
    }

    /**
     * 获取延伸的中心
     */
    private fun getCenter(): Float {
        return Math.hypot(coll.measuredHeight / 2.toDouble(), coll.measuredWidth / 2.toDouble()).toFloat()
    }

    private fun getRadus(): Float {
        return Math.hypot(root.measuredHeight.toDouble(), root.measuredWidth.toDouble()).toFloat()
    }
}