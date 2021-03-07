package com.mylearn.jeyepetizer.ui.login

import android.os.Bundle
import com.mylearn.jeyepetizer.R
import com.mylearn.jeyepetizer.common.ui.BaseActivity

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setStatusBarBackground(R.color.black)
    }
}