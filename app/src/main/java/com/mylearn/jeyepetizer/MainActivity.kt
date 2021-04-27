package com.mylearn.jeyepetizer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mylearn.jeyepetizer.common.ui.BaseActivity
import com.mylearn.jeyepetizer.event.MessageEvent
import com.mylearn.jeyepetizer.event.RefreshEvent
import com.mylearn.jeyepetizer.extension.showShortToast
import com.mylearn.jeyepetizer.ui.community.CommunityFragment
import com.mylearn.jeyepetizer.ui.home.HomePageFragment
import com.mylearn.jeyepetizer.ui.mine.MineFragment
import com.mylearn.jeyepetizer.ui.notification.NotificationFragment
import com.mylearn.jeyepetizer.util.GlobalUtil
import kotlinx.android.synthetic.main.layout_bottom_navigation_bar.*
import org.greenrobot.eventbus.EventBus
import java.security.AccessControlContext

class MainActivity : BaseActivity() , View.OnClickListener{

    private var backPressTime = 0L //返回按钮的时间
    private var homePageFragment: HomePageFragment?=null
    private var communityFragment: CommunityFragment?=null
    private var notificationFragment :NotificationFragment?=null
    private var mineFragment: MineFragment?=null

    private val fragmentManager:FragmentManager by lazy { supportFragmentManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnHomePage.setOnClickListener(this)
        btnCommunity.setOnClickListener(this)
        btnNotification.setOnClickListener(this)
        btnMine.setOnClickListener(this)
    }

    override fun setupViews() {
        setTabSelection(0)
    }

    override fun onClick(p0: View?) {
        when(p0){
            btnHomePage->{
                notifyUiRefresh(0)
                setTabSelection(0)
            }
            btnCommunity->{
                notifyUiRefresh(1)
                setTabSelection(1)
            }
            btnNotification->{
                notifyUiRefresh(2)
                setTabSelection(2)
            }
            btnMine->{
                notifyUiRefresh(3)
                setTabSelection(3)
            }
        }
    }

    /**
     * tab页选择
     */
    private fun setTabSelection(index:Int){
        clearAllSelected()
        fragmentManager.beginTransaction().apply {
            //设置切换的淡入淡出动画
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            hideFragments(this)
            when(index){
                0->{
                    ivHomePage.isSelected =true
                    tvHomePage.isSelected =true
                    if(homePageFragment == null){
                        homePageFragment = HomePageFragment.newInstance()
                        add(R.id.homeActivityFragContainer, homePageFragment!!)
                    }else{
                        show(homePageFragment!!)
                    }
                }
                1->{
                    ivCommunity.isSelected =true
                    tvCommunity.isSelected =true
                    if(communityFragment == null){
                        communityFragment = CommunityFragment.newInstance()
                        add(R.id.homeActivityFragContainer, communityFragment!!)
                    }else{
                        show(communityFragment!!)
                    }
                }
                2->{
                    ivNotification.isSelected =true
                    tvNotification.isSelected =true
                    if(notificationFragment == null){
                        notificationFragment = NotificationFragment.newInstance()
                        add(R.id.homeActivityFragContainer, notificationFragment!!)
                    }else{
                        show(notificationFragment!!)
                    }
                }
                3->{
                    ivMine.isSelected =true
                    tvMine.isSelected =true
                    if(mineFragment == null){
                        mineFragment = MineFragment.newInstance()
                        add(R.id.homeActivityFragContainer, mineFragment!!)
                    }else{
                        show(mineFragment!!)
                    }
                }
            }
        }.commitAllowingStateLoss()
    }

    private fun hideFragments(transaction: FragmentTransaction){
        transaction.run {
            if (homePageFragment != null) hide(homePageFragment!!)
            if (communityFragment != null) hide(communityFragment!!)
            if (notificationFragment != null) hide(notificationFragment!!)
            if (mineFragment != null) hide(mineFragment!!)
        }
    }

    /**
     * 通知界面UI刷新
     */
    private fun notifyUiRefresh(selectionIndex:Int){
        when(selectionIndex){
            0->{
               if(ivHomePage.isSelected) EventBus.getDefault().post(RefreshEvent(HomePageFragment::class.java))
            }
            1->{
                if (ivCommunity.isSelected) EventBus.getDefault().post(RefreshEvent(CommunityFragment::class.java))
            }
            2 -> {
                if (ivNotification.isSelected) EventBus.getDefault().post(RefreshEvent(NotificationFragment::class.java))
            }
            3 -> {
                if (ivMine.isSelected) EventBus.getDefault().post(RefreshEvent(MineFragment::class.java))
            }
        }
    }

    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            processBackPressed()
        }
    }

    private fun processBackPressed() {
        val now = System.currentTimeMillis()
        if (now - backPressTime > 2000) {
            String.format(GlobalUtil.getString(R.string.press_again_to_exit), GlobalUtil.appName).showShortToast()
            backPressTime = now
        } else {
            super.onBackPressed()
        }
    }


    /**
     * 清楚所有选择的状态
     */
    private fun clearAllSelected(){
        ivHomePage.isSelected = false
        tvHomePage.isSelected = false
        ivCommunity.isSelected = false
        tvCommunity.isSelected = false
        ivNotification.isSelected = false
        tvNotification.isSelected = false
        ivMine.isSelected = false
        tvMine.isSelected = false
    }


    companion object{
        fun start(context: Context){
            context.startActivity(Intent(context,MainActivity::class.java))
        }
    }
}