package com.mylearn.jeyepetizer.ui.ugcdetail

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mylearn.jeyepetizer.R
import com.mylearn.jeyepetizer.common.ui.BaseActivity
import com.mylearn.jeyepetizer.logic.model.CommunityRecommend

/**
  * @Author: JSS
  * @Time: 2021/4/15 13:43
  * @Description: 社区-推荐  详情页
  */
class UgcDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ugc_detail)
    }


    companion object {
        const val TAG = "UgcDetailActivity"
        const val EXTRA_RECOMMEND_ITEM_LIST_JSON = "recommend_item_list"
        const val EXTRA_RECOMMEND_ITEM_JSON = "recommend_item"

        fun start(context: Activity, dataList: List<CommunityRecommend.Item>, currentItem: CommunityRecommend.Item) {
            IntentDataHolderUtil.setData(EXTRA_RECOMMEND_ITEM_LIST_JSON, dataList)
            IntentDataHolderUtil.setData(EXTRA_RECOMMEND_ITEM_JSON, currentItem)
            val starter = Intent(context, UgcDetailActivity::class.java)
            context.startActivity(starter)
            context.overridePendingTransition(R.anim.anl_push_bottom_in, R.anim.anl_push_up_out)
        }
    }
}