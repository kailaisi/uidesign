package cn.com.tcsl.sun

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/13 19:40
 */
class LoadingView : View {
    var mPaint = Paint()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    private val ringColor: Int = Color.BLUE
    private val ringWidth = 200f
    private var minRingCenterWidth = 0f
    fun drawZoomRing(canvas: Canvas) {
        mPaint.shader = null
        mPaint.strokeWidth = 0f
        mPaint.style = Paint.Style.FILL
        mPaint.color = ringColor
        //大圆
        canvas.drawCircle(measuredWidth / 2f, measuredHeight / 2f, ringWidth / 2, mPaint)
        mPaint.color = Color.WHITE
        //小圆
        canvas.drawCircle(measuredWidth / 2f, measuredHeight / 2f, minRingCenterWidth / 2, mPaint)
    }

    private val mRectCenterArc: RectF?

    private val centerArcEndAngle=180f

    private var centerArcAngle=0f

    private val outSideArcAngle=180f

    private val outSideArcStartAngle=0f

    private val mRectOutArc: RectF?

    fun drawArcLine(canvas: Canvas){
        mPaint.color=ringColor
        mPaint.style=Paint.Style.STROKE
        mPaint.strokeCap=Paint.Cap.ROUND
        mPaint.strokeWidth=measuredWidth/40f
        canvas.drawArc(mRectCenterArc,centerArcEndAngle-centerArcAngle,centerArcAngle,false,mPaint)
        mPaint.strokeWidth=measuredWidth/25f
        canvas.drawArc(mRectOutArc,outSideArcStartAngle,outSideArcAngle,false,mPaint)
    }
}