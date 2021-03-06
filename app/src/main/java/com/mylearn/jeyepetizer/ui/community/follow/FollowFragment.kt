package com.mylearn.jeyepetizer.ui.community.follow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mylearn.jeyepetizer.R

 /**
  * @Author: JSS
  * @Time: 2021/4/21 15:31
  * @Description: 社区——关注页面
  */
class FollowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follow, container, false)
    }

    companion object {
        fun newInstance() = FollowFragment()
    }
}