package com.mylearn.jeyepetizer.ui.home.commend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mylearn.jeyepetizer.R
import com.mylearn.jeyepetizer.common.ui.BaseFragment

/**
 * @Author: JSS
 * @Time: 2021/3/26 22:08
 * @Description: 首页-推荐列表界面
 */
class CommendFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater.inflate(R.layout.fragment_commend, container, false))
    }

    companion object {
        fun newInstance() = CommendFragment()
    }
}