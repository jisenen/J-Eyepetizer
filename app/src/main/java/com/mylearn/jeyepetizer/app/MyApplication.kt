package com.mylearn.jeyepetizer.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.mylearn.jeyepetizer.R
import com.mylearn.jeyepetizer.common.view.NoStatusFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV


/**
 * @Created by: JSS
 * @on: 2021/3/2
 * @Description: 自定义Application，在这里进行全局的初始化操作
 */
class MyApplication :Application(){
    init {
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
            layout.setEnableLoadMore(true)
            layout.setEnableLoadMoreWhenContentNotFull(true)
        }

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setEnableHeaderTranslationContent(true)
            MaterialHeader(context).setColorSchemeResources(R.color.blue, R.color.blue, R.color.blue)
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            layout.setEnableFooterFollowWhenNoMoreData(true)
            layout.setEnableFooterTranslationContent(true)
            layout.setFooterHeight(153f)
            layout.setFooterTriggerRate(0.6f)
            NoStatusFooter.REFRESH_FOOTER_NOTHING = "- The End -"
            NoStatusFooter(context).apply {
                setAccentColorId(R.color.colorTextPrimary)
                setTextTitleSize(16f)
            }
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        //初始化MMKV(可以自定义MMKV的根目录)
        MMKV.initialize(this)
    }

    companion object{
        lateinit var context: Context
    }
}