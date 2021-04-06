package com.mylearn.jeyepetizer.ui.community.commend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mylearn.jeyepetizer.R
import com.mylearn.jeyepetizer.common.Const
import com.mylearn.jeyepetizer.common.holder.EmptyViewHolder
import com.mylearn.jeyepetizer.logic.model.CommunityRecommend

/**
 * @Author: JSS
 * @Time: 2021/3/29 15:29
 * @Description: 社区——推荐页面列表适配器
 */
class CommendAdapter(val fragment: CommendFragment, var dataList: List<CommunityRecommend.Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = dataList.size

    override fun getItemViewType(position: Int): Int {
        val item = dataList[position]
        return when (item.type) {
            STR_HORIZONTAL_SCROLLCARD_TYPE -> {
                when (item.data.dataType) {
                    STR_ITEM_COLLECTION_DATA_TYPE -> HORIZONTAL_SCROLLCARD_ITEM_COLLECTION_TYPE
                    STR_HORIZONTAL_SCROLLCARD_DATA_TYPE -> HORIZONTAL_SCROLLCARD_TYPE
                    else -> Const.ItemViewType.UNKNOWN
                }
            }
            STR_COMMUNITY_COLUMNS_CARD -> {
                if (item.data.dataType == STR_FOLLOW_CARD_DATA_TYPE) FOLLOW_CARD_TYPE
                else Const.ItemViewType.UNKNOWN
            }
            else -> Const.ItemViewType.UNKNOWN
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        HORIZONTAL_SCROLLCARD_ITEM_COLLECTION_TYPE -> {
            HorizontalScrollcardItemCollectionViewHolder(
                R.layout.item_community_horizontal_scrollcard_item_collection_type.inflate(
                    parent
                )
            )
        }
        HORIZONTAL_SCROLLCARD_TYPE -> {
            HorizontalScrollcardViewHolder(
                R.layout.item_community_horizontal_scrollcard_type.inflate(
                    parent
                )
            )
        }
        FOLLOW_CARD_TYPE -> {
            FollowCardViewHolder(
                R.layout.item_community_columns_card_follow_card_type.inflate(
                    parent
                )
            )
        }
        else -> {
            EmptyViewHolder(View(parent.context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    /**
     * 主题创作广场+话题讨论大厅
     */
    inner class HorizontalScrollcardItemCollectionViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
    }

    /**
     * 主题创作广场+话题讨论大厅 所处列表适配器
     */
    inner class SquareCardOfCommunityContentAdapter(
        val fragment: CommendFragment,
        var dataList: List<CommunityRecommend.ItemX>
    ) :
        RecyclerView.Adapter<SquareCardOfCommunityContentAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val ivBgPicture: ImageView = view.findViewById(R.id.ivBgPicture)
            val tvTitle: TextView = view.findViewById(R.id.tvTitle)
            val tvSubTitle: TextView = view.findViewById(R.id.tvSubTitle)
        }

        override fun getItemCount() = dataList.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_community_horizontal_scroll_card_itemcollection_item_type, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = dataList[position]
            holder.ivBgPicture.layoutParams.width = fragment.maxImageWidth
            holder.ivBgPicture.load(item.data.bgPicture)
            holder.tvTitle.text = item.data.title
            holder.tvSubTitle.text = item.data.subTitle
            holder.itemView.setOnClickListener { ActionUrlUtil.process(fragment, item.data.actionUrl, item.data.title) }
        }

    }


    companion object {
        const val TAG = "CommendAdapter"

        const val STR_HORIZONTAL_SCROLLCARD_TYPE = "horizontalScrollCard"
        const val STR_COMMUNITY_COLUMNS_CARD = "communityColumnsCard"

        const val STR_HORIZONTAL_SCROLLCARD_DATA_TYPE = "HorizontalScrollCard"
        const val STR_ITEM_COLLECTION_DATA_TYPE = "ItemCollection"
        const val STR_FOLLOW_CARD_DATA_TYPE = "FollowCard"

        const val STR_VIDEO_TYPE = "video"
        const val STR_UGC_PICTURE_TYPE = "ugcPicture"

        const val HORIZONTAL_SCROLLCARD_ITEM_COLLECTION_TYPE =
            1   //type:horizontalScrollCard -> dataType:ItemCollection
        const val HORIZONTAL_SCROLLCARD_TYPE =
            2                   //type:horizontalScrollCard -> dataType:HorizontalScrollCard
        const val FOLLOW_CARD_TYPE =
            3                             //type:communityColumnsCard -> dataType:FollowCard
    }
}