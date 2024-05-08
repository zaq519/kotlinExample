package com.zoe.wan.android.example.fragment.hotkey

import com.zoe.wan.android.example.R
import com.zoe.wan.base.BaseFragment
import com.zoe.wan.android.example.BR
import com.zoe.wan.android.example.databinding.FragmentHotKeyBinding


class FragHotkey:BaseFragment<FragmentHotKeyBinding, FragHotkeyViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_hot_key
    }

    override fun getViewModelId(): Int {
        return BR.hotkeyVm
    }

    override fun initViewData() {

    }
}