package cn.com.tcsl.sun;

import android.animation.TypeEvaluator;

public class CircleTypeEvaluator implements TypeEvaluator<CircleInfo> {
    private final CircleInfo mCircleInfo;

    public CircleTypeEvaluator(CircleInfo circleInfo) {
        this.mCircleInfo=circleInfo;
    }

    @Override
    public CircleInfo evaluate(float fraction, CircleInfo startValue, CircleInfo endValue) {
        float y = startValue.getY() + fraction * (endValue.getY() - startValue.getY());
        float radius = startValue.getRadius() + fraction * (endValue.getRadius()-startValue.getRadius());
        mCircleInfo.setCircleInfo(mCircleInfo.getX(),y,radius);
        return mCircleInfo;
    }
}