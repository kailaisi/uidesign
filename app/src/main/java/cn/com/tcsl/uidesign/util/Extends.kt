package cn.com.tcsl.uidesign.util

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/11/3 16:56
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}