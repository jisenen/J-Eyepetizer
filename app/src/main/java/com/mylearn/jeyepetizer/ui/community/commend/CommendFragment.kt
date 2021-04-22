package com.mylearn.jeyepetizer.ui.community.commend

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mylearn.jeyepetizer.R
import com.mylearn.jeyepetizer.common.ui.BaseFragment
import com.mylearn.jeyepetizer.extension.dp2px
import com.mylearn.jeyepetizer.util.GlobalUtil
import com.mylearn.jeyepetizer.util.InjectorUtil
import kotlinx.android.synthetic.main.fragment_commend.*

/**
 * @Author: JSS
 * @Time: 2021/3/29 15:19
 * @Description: 社区——推荐页面
 */
class CommendFragment : BaseFragment() {

private val viewModel by lazy { ViewModelProvider(this,InjectorUtil.getCommunityCommendViewModelFactory()).get(CommendViewModel::class.java) }

    private lateinit var adapter: CommendAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater.inflate(R.layout.fragment_commend2, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecycler()
        observe()
    }

    /**
     * 监听列表数据变化
     */
    private fun observe(){

    }

    private fun initRecycler() {
        adapter = CommendAdapter(this,viewModel.dataList)
        val mainLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mainLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recyclerView.layoutManager = mainLayoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(CommendAdapter.ItemDecoration(this))
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = null
        refreshLayout.setOnRefreshListener { viewModel.onRefresh() }
        refreshLayout.setOnLoadMoreListener { viewModel.onLoadMore() }
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