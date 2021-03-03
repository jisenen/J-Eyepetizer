package com.xhgjky.jeyepetizer.util

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
    fun pushActivity(activity:WeakReference<Activity>?){
        activityStack.push(activity)
    }

    fun removeActivity(activity:WeakReference<Activity>?){
        activityStack.remove(activity)
    }
}