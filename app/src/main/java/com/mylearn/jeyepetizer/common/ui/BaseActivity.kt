package com.mylearn.jeyepetizer.common.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.mylearn.jeyepetizer.R
import com.mylearn.jeyepetizer.event.MessageEvent

import com.mylearn.jeyepetizer.extension.logD
import com.mylearn.jeyepetizer.util.ActivityControlUtil
import com.mylearn.jeyepetizer.util.ShareUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

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

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setStatusBarBackground(R.color.colorPrimaryDark)
        setupViews()
    }

    override fun setContentView(layoutView: View) {
        super.setContentView(layoutView)
        setStatusBarBackground(R.color.colorPrimaryDark)
        setupViews()
    }

    protected open fun setupViews() {
        val navigateBefore = findViewById<ImageView>(R.id.ivNavigateBefore)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        navigateBefore?.setOnClickListener { finish() }
        tvTitle?.isSelected = true  //获取焦点，实现跑马灯效果。

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(messageEvent: MessageEvent) {

    }

    /**
     * 设置状态栏背景色
     */
    open fun setStatusBarBackground(@ColorRes statusBarColor: Int) {
        ImmersionBar.with(this).autoStatusBarDarkModeEnable(true, 0.2f).statusBarColor(statusBarColor).fitsSystemWindows(true).init()
    }

    /**
     * 调用系统原生分享
     *
     * @param shareContent 分享内容
     * @param shareType SHARE_MORE=0，SHARE_QQ=1，SHARE_WECHAT=2，SHARE_WEIBO=3，SHARE_QQZONE=4
     */
    protected fun share(shareContent: String, shareType: Int) {
        ShareUtil.share(this, shareContent, shareType)
    }
}