package com.mylearn.jeyepetizer.util

import android.app.Activity
import java.lang.ref.WeakReference
import java.util.*


/**
 * @Created by: JSS
 * @on: 2021/3/2
 * @Description: 管理应用中所有Activity(单例类)
 */
object ActivityControlUtil {
    private val activityStack = Stack<WeakReference<Activity>>()

    /**
     * 将Activity压栈
     */
    fun pushActivity(activity: WeakReference<Activity>?) {
        activityStack.push(activity)
    }

    /**
     * Activity出栈
     */
    fun removeActivity(activity: WeakReference<Activity>?) {
        activityStack.remove(activity)
    }

    /**
     * 指定位置的Activity出栈
     */
    fun removeActivity(activityIndex: Int) {
        if (activityStack.size > activityIndex) activityStack.removeAt(activityIndex)
    }

    /**
     * 将栈中Activity移除至栈顶
     */
    fun removeToTop() {
        val end = activityStack.size
        val start = 1
        for (i in end - 1 downTo start) {
            val mActivity = activityStack[i].get()
            if (null != mActivity && !mActivity.isFinishing) {
                mActivity.finish()
            }
        }
    }

    /**
     * 移除全部的Activity(用于整个应用退出)
     */
    fun removeAll() {
        for (activity in activityStack) {
            val mActivity = activity.get()
            if (null != mActivity && !mActivity.isFinishing) {
                mActivity.finish()
            }
        }
    }
}