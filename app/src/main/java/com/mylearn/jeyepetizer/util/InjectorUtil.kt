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

package com.mylearn.jeyepetizer.util

import com.mylearn.jeyepetizer.logic.MainPageRepository
import com.mylearn.jeyepetizer.logic.VideoRepository
import com.mylearn.jeyepetizer.logic.dao.EyepetizerDatabase
import com.mylearn.jeyepetizer.logic.network.EyepetizerNetwork


/**
 * 应用程序逻辑控制管理类。
 *
 * @author vipyinzhiwei
 * @since  2020/5/2
 */
object InjectorUtil {

    private fun getMainPageRepository() = MainPageRepository.getInstance(EyepetizerDatabase.getMainPageDao(), EyepetizerNetwork.getInstance())

    private fun getViedoRepository() = VideoRepository.getInstance(EyepetizerDatabase.getVideoDao(), EyepetizerNetwork.getInstance())

//    fun getDiscoveryViewModelFactory() = DiscoveryViewModelFactory(getMainPageRepository())

    fun getHomePageCommendViewModelFactory() = com.mylearn.jeyepetizer.ui.home.commend.CommendViewModelFactory(getMainPageRepository())

//    fun getDailyViewModelFactory() = DailyViewModelFactory(getMainPageRepository())

    fun getCommunityCommendViewModelFactory() = com.mylearn.jeyepetizer.ui.community.commend.CommendViewModelFactory(getMainPageRepository())

//    fun getFollowViewModelFactory() = FollowViewModelFactory(getMainPageRepository())
//
//    fun getPushViewModelFactory() = PushViewModelFactory(getMainPageRepository())
//
//    fun getSearchViewModelFactory() = SearchViewModelFactory(getMainPageRepository())
//
//    fun getNewDetailViewModelFactory() = NewDetailViewModelFactory(getViedoRepository())

}