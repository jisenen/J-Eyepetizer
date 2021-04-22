package com.mylearn.jeyepetizer.ui.community.commend

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.eyepetizer.android.logic.network.api.MainPageService
import com.mylearn.jeyepetizer.logic.MainPageRepository
import com.mylearn.jeyepetizer.logic.model.CommunityRecommend
import java.lang.Exception

/**
 * @Author: JSS
 * @Time: 2021/4/2213:50
 * @Description:
 */
class CommendViewModel(repository: MainPageRepository):ViewModel() {
    var dataList = ArrayList<CommunityRecommend.Item>()

    private var requestParamLiveData = MutableLiveData<String>()

    var nextPageUrl:String? = null

    val dataListLiveData = Transformations.switchMap(requestParamLiveData){ url->
        liveData {
            val result = try {
                val recommend = repository.refreshCommunityRecommend(url)
                Result.success(recommend)
            }catch (e:Exception){
                Result.failure<CommunityRecommend>(e)
            }
            emit(result)
        }
    }

    fun onRefresh() {
        requestParamLiveData.value = MainPageService.COMMUNITY_RECOMMEND_URL
    }

    fun onLoadMore() {
        requestParamLiveData.value = nextPageUrl ?: ""
    }
}