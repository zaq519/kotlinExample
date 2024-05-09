package com.zoe.wan.android.example.fragment.personal

import android.content.Intent
import com.zoe.wan.android.example.R
import com.zoe.wan.base.BaseFragment
import com.zoe.wan.android.example.BR
import com.zoe.wan.android.example.activity.login.LoginActivity
import com.zoe.wan.android.example.databinding.FragmentPersonalBinding


class FragPersonal:BaseFragment<FragmentPersonalBinding, FragPersonalViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_personal
    }

    override fun getViewModelId(): Int {
        return BR.personalVm
    }

    override fun initViewData() {
        binding?.personalTv?.setOnClickListener{
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtra(LoginActivity.Intent_Type_Name, LoginActivity.Intent_Type_Value)
            startActivity(intent)
        }
    }
}