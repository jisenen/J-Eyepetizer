package com.mylearn.jeyepetizer.ui

import android.Manifest
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.mylearn.jeyepetizer.MainActivity
import com.permissionx.guolindev.PermissionX
import com.tencent.mmkv.MMKV
import com.mylearn.jeyepetizer.R
import com.mylearn.jeyepetizer.common.ui.BaseActivity
import com.mylearn.jeyepetizer.util.GlobalUtil
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Author: JSS
 * @Time: 2021/3/6 21:54
 * @Description: 启动页
 */
class SplashActivity : BaseActivity() {

    private val job by lazy { Job() }
    private val splashDuration = 3 * 1000L

    /**
     * 透明度变化的Animation
     */
    private val alphaAnimation by lazy {
        AlphaAnimation(0.5f, 1.0f).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    /**
     * 缩放变化的Animation
     */
    private val scaleAnimation by lazy {
        ScaleAnimation(
            1f,
            1.05f,
            1f,
            1.05f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWriteExternalStoragePermission()
    }

    override fun setupViews() {
        super.setupViews()
        setStatusBarBackground(R.color.black)
        ivSplashBG.startAnimation(scaleAnimation)
        ivLogo.startAnimation(alphaAnimation)
        CoroutineScope(job).launch {
            delay(splashDuration)
            MainActivity.start(this@SplashActivity)
            finish()
        }
        isFirstEntryApp = true
    }

    /**
     * 请求读写权限
     */
    private fun requestWriteExternalStoragePermission() {
        PermissionX.init(this@SplashActivity)
            .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .onExplainRequestReason { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_picture_processing)
                scope.showRequestReasonDialog(
                    deniedList,
                    message,
                    GlobalUtil.getString(R.string.ok),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .onForwardToSettings { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_picture_processing)
                scope.showForwardToSettingsDialog(
                    deniedList,
                    message,
                    GlobalUtil.getString(R.string.settings),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .request { _, _, _ ->
                requestReadPhoneStatePermission()
            }
    }

    /**
     * 请求读手机状态权限
     */
    private fun requestReadPhoneStatePermission() {
        PermissionX.init(this@SplashActivity).permissions(Manifest.permission.READ_PHONE_STATE)
            .onExplainRequestReason { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_access_phone_info)
                scope.showRequestReasonDialog(
                    deniedList,
                    message,
                    GlobalUtil.getString(R.string.ok),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .onForwardToSettings { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_access_phone_info)
                scope.showForwardToSettingsDialog(
                    deniedList,
                    message,
                    GlobalUtil.getString(R.string.settings),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .request { _, _, _ ->
                setContentView(R.layout.activity_splash)
            }
    }

    companion object {
        var isFirstEntryApp: Boolean
            get() = MMKV.defaultMMKV()!!.decodeBool("is_first_entry_app", true)
            set(value) {
                MMKV.defaultMMKV()!!.encode("is_first_entry_app", value)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}