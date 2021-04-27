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
import com.mylearn.jeyepetizer.event.RefreshEvent
import com.mylearn.jeyepetizer.event.SwitchPagesEvent
import com.mylearn.jeyepetizer.logic.model.TabEntity
import com.mylearn.jeyepetizer.ui.community.commend.CommendFragment
import com.mylearn.jeyepetizer.ui.community.follow.FollowFragment
import com.mylearn.jeyepetizer.util.GlobalUtil
import org.greenrobot.eventbus.EventBus

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
        if (messageEvent is RefreshEvent && this::class.java == messageEvent.activityClass) {
            when (viewPager?.currentItem) {
                0 -> EventBus.getDefault().post(RefreshEvent(CommendFragment::class.java))
                1 -> EventBus.getDefault().post(RefreshEvent(FollowFragment::class.java))
                else -> {
                }
            }
        } else if (messageEvent is SwitchPagesEvent) {
            when (messageEvent.activityClass) {
                CommendFragment::class.java -> viewPager?.currentItem = 0
                else -> {
                }
            }
        }
    }

    companion object {
        fun newInstance() = CommunityFragment()
    }
}