package cn.com.tcsl.vectors.ui.main;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

import cn.com.tcsl.vectors.ui.detail.IconPageIndicator;

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2018/2/2 13:50
 */
public class IndictorBehavior extends CoordinatorLayout.Behavior<IconPageIndicator> {
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, IconPageIndicator child, View dependency) {
        return  dependency instanceof AppBarLayout;
    }
}
