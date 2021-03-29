package com.mylearn.jeyepetizer.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flyco.tablayout.listener.CustomTabEntity
import com.mylearn.jeyepetizer.R
import com.mylearn.jeyepetizer.common.ui.BaseViewPagerFragment
import com.mylearn.jeyepetizer.event.MessageEvent
import com.mylearn.jeyepetizer.logic.model.TabEntity
import com.mylearn.jeyepetizer.ui.community.commend.CommendFragment
import com.mylearn.jeyepetizer.ui.community.follow.FollowFragment
import com.mylearn.jeyepetizer.util.GlobalUtil

/**
 * @Author: JSS
 * @Time: 2021/3/29 14:22
 * @Description: 社区主页面
 */
class CommunityFragment : BaseViewPagerFragment() {

    override val createTitles = ArrayList<CustomTabEntity>().apply {
        add(TabEntity(GlobalUtil.getString(R.string.commend)))
        add(TabEntity(GlobalUtil.getString(R.string.follow)))
    }

    override val createFragments: Array<Fragment> = arrayOf(CommendFragment.newInstance(),FollowFragment.newInstance())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater.inflate(R.layout.fragment_community, container, false))
    }

    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)
    }

    companion object {
        fun newInstance() = CommunityFragment()
    }
}