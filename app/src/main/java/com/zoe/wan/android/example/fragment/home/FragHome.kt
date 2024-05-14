package com.zoe.wan.android.example.fragment.home

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.zoe.wan.android.example.R
import com.zoe.wan.android.example.databinding.FragmentHomeBinding
import com.zoe.wan.base.BaseFragment
import com.zoe.wan.android.example.BR
import com.zoe.wan.android.example.activity.webview.WebActivity
import com.zoe.wan.android.example.common.adapter.HomeListAdapter


class FragHome:BaseFragment<FragmentHomeBinding, FragHomeViewModel>() {

    private val adapter = HomeListAdapter()
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun getViewModelId(): Int {
        return BR.homeVm
    }

    override fun initViewData() {
        binding?.homeListView?.layoutManager = LinearLayoutManager(context)
        binding?.homeListView?.adapter = adapter

        viewModel?.list?.observe(viewLifecycleOwner) { list->
            if (!list.isNullOrEmpty())
                adapter.setData(list)
        }

        viewModel?.bannerData?.observe(viewLifecycleOwner) {data->
            adapter.setBannerData(data)

        }

        //item 点击回调
        adapter.registerItemListener(object: HomeListAdapter.HomeItemClickListener {
            override fun itemCollect(id: String, pos: Int, collect: Boolean) {
                if(collect == true) {
                    //取消收藏
                    viewModel?.collect(id) {
                        //取消成功，此方法回调，更改图标
                        adapter.setCollect(false, pos)
                    }
                } else {
                    //调用收藏接口
                    viewModel?.collect(id) {
                        //收藏成功，此方法回调，更改图标
                        adapter.setCollect(true, pos)
                    }
                }

            }

            override fun itemClick(title: String?, link: String?) {
                jumpToWeb(title, link)
            }

            override fun bannerClick(title: String?, link: String?) {
                jumpToWeb(title, link)
            }

        })
    }

    private fun jumpToWeb(title: String?, link: String?) {
        val intent = Intent(context, WebActivity::class.java)
        intent.putExtra(WebActivity.INTENT_WEB_TITLE_KEY, title)
        intent.putExtra(WebActivity.INTENT_WEB_URL_KEY, link)
        startActivity(intent)
    }
}