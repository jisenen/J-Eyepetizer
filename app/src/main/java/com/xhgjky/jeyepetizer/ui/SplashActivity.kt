package com.xhgjky.jeyepetizer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xhgjky.jeyepetizer.R
import com.xhgjky.jeyepetizer.common.ui.BaseActivity

/**
 * @Author: JSS
 * @Time: 2021/3/6 21:54
 * @Description: 启动页
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}