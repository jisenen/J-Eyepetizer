package com.mylearn.jeyepetizer.event

/**
 * 通知fragment刷新UI的消息事件
 */
open class RefreshEvent(
    var activityClass: Class<*>? = null
) : MessageEvent()