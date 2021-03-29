package com.mylearn.jeyepetizer.ui.community.commend

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.mylearn.jeyepetizer.R
import com.mylearn.jeyepetizer.common.ui.BaseFragment
import com.mylearn.jeyepetizer.extension.dp2px
import com.mylearn.jeyepetizer.util.GlobalUtil

/**
  * @Author: JSS
  * @Time: 2021/3/29 15:19
  * @Description: 社区——推荐页面
  */
class CommendFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater.inflate(R.layout.fragment_commend2, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecycler()
    }

    private fun initRecycler(){

    }

    /**
     * 列表左or右间距
     */
    val bothSideSpace = GlobalUtil.getDimension(R.dimen.listSpaceSize)

    /**
     * 列表中间内间距，左or右。
     */
    val middleSpace = dp2px(3f)

    /**
     * 通过获取屏幕宽度来计算出每张图片最大的宽度。
     *
     * @return 计算后得出的每张图片最大的宽度。
     */
    val maxImageWidth: Int
        get() {
            val windowManager = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val metrics = DisplayMetrics()
            windowManager.defaultDisplay?.getMetrics(metrics)
            val columnWidth = metrics.widthPixels
            return (columnWidth - ((bothSideSpace * 2) + (middleSpace * 2))) / 2
        }

    companion object {
        fun newInstance() = CommendFragment()
    }
}