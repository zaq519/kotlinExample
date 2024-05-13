package com.zoe.wan.android.example.activity.webview

import com.zoe.wan.android.example.databinding.ActivityWebViewBinding
import com.zoe.wan.base.BaseActivity
import com.zoe.wan.android.example.BR
import com.zoe.wan.android.example.R


class WebActivity: BaseActivity<ActivityWebViewBinding, WebViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_web_view
    }

    override fun getViewModelId(): Int {
        return BR.webVm
    }

    override fun initViewData() {
        TODO("Not yet implemented")
    }
}