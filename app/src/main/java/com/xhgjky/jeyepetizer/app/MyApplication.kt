package com.xhgjky.jeyepetizer.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex


/**
 * @Created by: JSS
 * @on: 2021/3/2
 * @Description: 自定义Application，在这里进行全局的初始化操作
 */
class MyApplication :Application(){
    init {

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        context = this

    }

    companion object{
        lateinit var context: Context
    }
}