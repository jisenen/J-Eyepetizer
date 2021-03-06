package com.xhgjky.jeyepetizer.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xhgjky.jeyepetizer.R
import com.xhgjky.jeyepetizer.common.ui.BaseActivity

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setStatusBarBackground(R.color.black)
    }
}