package com.zoe.wan.android.example.activity.webview

import com.zoe.wan.android.example.databinding.ActivityWebViewBinding
import com.zoe.wan.base.BaseActivity
import com.zoe.wan.android.example.BR
import com.zoe.wan.android.example.R


class WebActivity: BaseActivity<ActivityWebViewBinding, WebViewModel>() {

    companion object {
        const val INTENT_WEB_TITLE_KEY = "INTENT_WEB_TITLE_KEY"
        const val INTENT_WEB_URL_KEY = "INTENT_WEB_URL_KEY"

    }
    override fun getLayoutId(): Int {
        return R.layout.activity_web_view
    }

    override fun getViewModelId(): Int {
        return BR.webVm
    }

    override fun initViewData() {
        val title = intent.getStringExtra(INTENT_WEB_TITLE_KEY)
        val url = intent.getStringExtra(INTENT_WEB_URL_KEY)

        viewModel?.webTitle?.set(title)

        binding?.webView?.loadUrl(url ?: "")
    }
}