package com.zoe.wan.android.example.fragment.personal

import android.content.Intent
import androidx.compose.ui.unit.Constraints
import com.blankj.utilcode.util.SPUtils
import com.zoe.wan.android.example.R
import com.zoe.wan.base.BaseFragment
import com.zoe.wan.android.example.BR
import com.zoe.wan.android.example.Constants
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

        binding?.personHead?.setOnClickListener{shouldLogin() }
        binding?.personalUsername?.setOnClickListener { shouldLogin() }
        binding?.personalLogout?.setOnClickListener { viewModel?.logout() }

    }

    /*
    * 非登录状态跳转到登录界面
    * */

    private fun shouldLogin() {

        if (viewModel?.showLogoutBtn?.get() == true) {
            return
        }
        val intent = Intent(context, LoginActivity::class.java)
        intent.putExtra(LoginActivity.Intent_Type_Name, LoginActivity.Intent_Type_Value)
        startActivity(intent)
    }
}