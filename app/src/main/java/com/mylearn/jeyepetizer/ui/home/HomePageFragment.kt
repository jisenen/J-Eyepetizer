package com.mylearn.jeyepetizer.ui.home

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
import com.mylearn.jeyepetizer.ui.home.commend.CommendFragment
import com.mylearn.jeyepetizer.ui.home.daily.DailyFragment
import com.mylearn.jeyepetizer.ui.home.discovery.DiscoveryFragment
import com.mylearn.jeyepetizer.util.GlobalUtil
import kotlinx.android.synthetic.main.layout_main_page_title_bar.*
import org.greenrobot.eventbus.EventBus

/**
 * @Author: JSS
 * @Time: 2021/3/17 21:22
 * @Description:
 */
class HomePageFragment : BaseViewPagerFragment() {

    override val createTitles = ArrayList<CustomTabEntity>().apply {
        add(TabEntity(GlobalUtil.getString(R.string.discovery)))
        add(TabEntity(GlobalUtil.getString(R.string.commend)))
        add(TabEntity(GlobalUtil.getString(R.string.daily)))
    }

    override val createFragments: Array<Fragment> = arrayOf(
        DiscoveryFragment.newInstance(),
        CommendFragment.newInstance(),
        DailyFragment.newInstance()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateView(inflater.inflate(R.layout.fragment_home_page, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ivCalendar.visibility = View.VISIBLE
        viewPager?.currentItem = 1
    }

    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)
        if (messageEvent is RefreshEvent && this::class.java == messageEvent.activityClass) {
            when (viewPager?.currentItem) {
                0 -> EventBus.getDefault().post(RefreshEvent(DiscoveryFragment::class.java))
                1 -> EventBus.getDefault().post(RefreshEvent(CommendFragment::class.java))
                2 -> EventBus.getDefault().post(RefreshEvent(DailyFragment::class.java))
                else -> {
                }
            }
        } else if (messageEvent is SwitchPagesEvent) {
            when (messageEvent.activityClass) {
                DiscoveryFragment::class.java -> viewPager?.currentItem = 0
                CommendFragment::class.java -> viewPager?.currentItem = 1
                DailyFragment::class.java -> viewPager?.currentItem = 2
                else -> {
                }
            }
        }
    }

    companion object {
        fun newInstance() = HomePageFragment()
    }
}