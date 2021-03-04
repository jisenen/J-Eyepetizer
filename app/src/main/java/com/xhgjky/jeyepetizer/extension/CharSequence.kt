/*
 * Copyright (c) 2020. vipyinzhiwei <vipyinzhiwei@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xhgjky.jeyepetizer.extension

import android.widget.Toast
import com.xhgjky.jeyepetizer.app.MyApplication


/**
 * 弹出Toast提示。(短)
 *
 */
fun CharSequence.showShortToast() {
    Toast.makeText(MyApplication.context, this, Toast.LENGTH_SHORT).show()
}

/**
 * 弹出Toast提示。(长)
 *
 */
fun CharSequence.showLongToast() {
    Toast.makeText(MyApplication.context, this, Toast.LENGTH_LONG).show()
}

///**
// * VasSonic预加载session。
// *
// * @param CharSequence 预加载url
// */
//fun CharSequence.preCreateSession(): Boolean {
//    if (!SonicEngine.isGetInstanceAllowed()) {
//        SonicEngine.createInstance(SonicRuntimeImpl(EyepetizerApplication.context), SonicConfig.Builder().build())
//    }
//    val sessionConfigBuilder = SonicSessionConfig.Builder().apply { setSupportLocalServer(true) }
//    val preloadSuccess = SonicEngine.getInstance().preCreateSession(this.toString(), sessionConfigBuilder.build())
//    logD("preCreateSession()", "${this}\t:${if (preloadSuccess) "Preload start up success!" else "Preload start up fail!"}")
//    return preloadSuccess
//}