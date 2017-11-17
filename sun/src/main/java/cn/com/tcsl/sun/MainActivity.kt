package cn.com.tcsl.sun

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.transitionseverywhere.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var visi = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener {
            var bounds = ChangeBounds()
            bounds.duration = 300
            bounds.pathMotion = ArcMotion()
            TransitionManager.beginDelayedTransition(action_container, TransitionSet()
                    .addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform())
                    .addTransition(Slide(Gravity.RIGHT))
                    .addTransition(bounds)
                    .addTransition(ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN))
                    .addTransition(Rotate()))
            visi = !visi
            textView.visibility = if (visi) View.VISIBLE else View.GONE
            var param: ViewGroup.LayoutParams = imageView.layoutParams
            param.height = if (visi) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
            imageView.layoutParams = param
            setGravity(textView2, visi)
            textView2.text = if (visi) "Left" else "RIGHT"
            imageView.rotation=if(visi) 130f else 0f
            TransitionManager.go()
        }
    }

    private fun setGravity(view: View, visi: Boolean) {
        var params = view.layoutParams as ConstraintLayout.LayoutParams
        if (visi) {
            params.topToTop = (view.parent as ViewGroup).id
            params.leftToLeft = (view.parent as ViewGroup).id

        } else {
            params.topToTop = btn.id
            params.leftToRight = btn.id
        }
        view.layoutParams = params
    }
}
