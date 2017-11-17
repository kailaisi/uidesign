package cn.com.tcsl.sun

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.support.annotation.RequiresApi
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/13 19:40
 */
class LoadingView : View {
    private val ringColor: Int = Color.BLUE
    /**
     *大圆半径
     */
    private var ringWidth = 0f
    /**
     * 小圆半径
     */
    private var minRingCenterWidth = 0f
    /**
     * 画笔
     */
    lateinit var mPaint: Paint
    /**
     * 内圆 结束角度
     */
    private var centerArcEndAngle = 180f
    /**
     * 内圆弧起始角度，会变化
     */
    private var centerArcAngle = 0f
    /**
     * 外圆弧结束
     */
    private var outSideArcAngle = 180f
    /**
     * 外圆弧开始
     */
    private var outSideArcStartAngle = 0f
    /**
     * 太阳角度
     */
    private var sunRotateAngle = 0f
    /**
     *
     */
    private val sunWidth = 100f

    private lateinit var mRectCenterArc: RectF
    private lateinit var mRectOutArc: RectF

    private lateinit var mRectSunFlower: RectF
    private lateinit var mRectSunShade: RectF

    private val sunShadeColor = Color.GRAY

    private var sunShadowWidth = 0f

    private val sunShadowHeight = 20f

    constructor(context: Context?) : this(context, null)


    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        mPaint = Paint()
        mPaint = Paint()
        mPaint.isDither = true
        mPaint.isAntiAlias = true
        mRectCenterArc = RectF()
        mRectOutArc = RectF()
        mRectSunFlower = RectF()
        mRectSunShade = RectF()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = measuredWidth
        setMeasuredDimension(width, (1.4f * width).toInt())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawZoomRing(canvas)
    }

    //绘制两个闪亮的圆
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

    fun drawArcLine(canvas: Canvas) {
        mPaint.color = ringColor
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = measuredWidth / 40f
        canvas.drawArc(mRectCenterArc, centerArcEndAngle - centerArcAngle, centerArcAngle, false, mPaint)
        mPaint.strokeWidth = measuredWidth / 25f
        canvas.drawArc(mRectOutArc, outSideArcStartAngle, outSideArcAngle, false, mPaint)
    }

    fun drawSun(canvas: Canvas) {
        mPaint.strokeWidth = 0f
        mPaint.style = Paint.Style.FILL
        mPaint.color = ringColor
        canvas.save()
        canvas.rotate(sunRotateAngle, measuredWidth / 2f, measuredHeight / 2f)
        canvas.drawRect(mRectSunFlower, mPaint)
        canvas.rotate(45f, measuredWidth / 2f, measuredHeight / 2f)
        canvas.drawRect(mRectSunFlower, mPaint)
        canvas.restore()
        canvas.drawCircle(measuredWidth / 2f, measuredHeight / 2f, sunWidth / 2f, mPaint)
    }

    fun drawShadow(canvas: Canvas) {
        mPaint.color = sunShadeColor
        mPaint.style = Paint.Style.FILL
        mRectSunShade.set(measuredWidth / 2f - sunShadowWidth / 2f, measuredHeight - sunShadowHeight,
                measuredWidth / 2f + sunShadowWidth / 2f, measuredHeight.toFloat())
        canvas.drawOval(mRectSunShade, mPaint)
    }

    /**
     * 启动动画
     */
    fun startAnim() {
        initValues()
        startInvaliadateAnim()
    }

    private fun startInvaliadateAnim() {
        var ringAnimator = ValueAnimator.ofFloat(ringWidth, measuredWidth * 0.8f)
        ringAnimator.interpolator = LinearInterpolator()
        ringAnimator.duration = 300
        ringAnimator.repeatCount
        ringAnimator.addUpdateListener({ animation ->
            ringWidth = animation.animatedValue as Float
        })
        ringAnimator.start()
    }

    /**
     * 初始化相关数据
     */
    private fun initValues() {
        //圆环部分
        minRingCenterWidth = 10f
        ringWidth = 3 * minRingCenterWidth
        //圆弧部分
        centerArcAngle = 2f
        centerArcEndAngle = 270 + centerArcAngle
        outSideArcStartAngle = 180f
        outSideArcAngle = 90f
        mRectCenterArc.set((measuredWidth / 2 - measuredWidth / 6).toFloat(), (measuredHeight / 2 - measuredWidth / 6).toFloat(), (measuredWidth / 2 + measuredWidth / 6).toFloat(), (measuredHeight / 2 + measuredWidth / 6).toFloat())
        mRectOutArc.set((measuredWidth / 2 - measuredWidth / 4).toFloat(), (measuredHeight / 2 - measuredWidth / 4).toFloat(), (measuredWidth / 2 + measuredWidth / 4).toFloat(), (measuredHeight / 2 + measuredWidth / 4).toFloat())
    }
}