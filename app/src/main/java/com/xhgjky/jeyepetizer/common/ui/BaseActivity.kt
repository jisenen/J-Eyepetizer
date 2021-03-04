package com.xhgjky.jeyepetizer.common.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.xhgjky.jeyepetizer.extension.logD
import com.xhgjky.jeyepetizer.util.ActivityControlUtil
import org.greenrobot.eventbus.EventBus
import java.lang.ref.WeakReference


/**
 * @Created by: JSS
 * @on: 2021/3/3
 * @Description: Activity基类
 */
open class BaseActivity :AppCompatActivity(){
    /**
     * 判断当前Activity是否再前台
     */
    protected var isActive:Boolean = false

    /**
     * 当前activity的实例
     */
    protected var activity:Activity? = null

    /**
     * 当前Activity的弱引用，防止内存泄漏
     */
    private var activityWR:WeakReference<Activity>? = null

    /**
     * 日志输出标志
     */
    protected val TAG:String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD(TAG,"BaseActivity-->onCreate()")
        activity = this
        activityWR = WeakReference(activity!!)
        ActivityControlUtil.pushActivity(activityWR)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logD(TAG, "BaseActivity-->onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        logD(TAG, "BaseActivity-->onRestoreInstanceState()")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logD(TAG, "BaseActivity-->onNewIntent()")
    }

    override fun onRestart() {
        super.onRestart()
        logD(TAG, "BaseActivity-->onRestart()")
    }

    override fun onStart() {
        super.onStart()
        logD(TAG, "BaseActivity-->onStart()")
    }

    override fun onResume() {
        super.onResume()
        logD(TAG, "BaseActivity-->onResume()")
        isActive = true
    }

    override fun onPause() {
        super.onPause()
        logD(TAG, "BaseActivity-->onPause()")
        isActive = false
    }

    override fun onStop() {
        super.onStop()
        logD(TAG, "BaseActivity-->onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logD(TAG, "BaseActivity-->onDestroy()")

        activity = null
        ActivityControlUtil.removeActivity(activityWR)
        EventBus.getDefault().unregister(this)
    }

    /**
     * 设置状态栏背景色
     */
    open fun setStatusBarBackground(@ColorRes statusBarColor: Int) {
        ImmersionBar.with(this).autoStatusBarDarkModeEnable(true, 0.2f).statusBarColor(statusBarColor).fitsSystemWindows(true).init()
    }
}