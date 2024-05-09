package com.zoe.wan.android.example.activity.login

import android.view.View
import com.zoe.wan.android.example.R
import com.zoe.wan.android.example.BR
import com.zoe.wan.android.example.databinding.ActivityLoginBinding
import com.zoe.wan.base.BaseActivity

class LoginActivity: BaseActivity<ActivityLoginBinding,LoginViewModel>() {

    companion object {
        // 0- 登录 ， 非0- 注册
        const val Intent_Type_Value = 0
        const val Intent_Type_Name = "Intent_Key_Name"

    }
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModelId(): Int {
        return BR.loginVm
    }

    override fun initViewData() {
        val type = intent.getIntExtra(Intent_Type_Name, -1)
        if (type == Intent_Type_Value) {
            binding?.inputPasswordTwice?.visibility = View.GONE
            binding?.registerBtn?.visibility = View.VISIBLE
            binding?.loginOrRegisterBtn?.text = "确认登录"
        } else {
            binding?.inputPasswordTwice?.visibility = View.VISIBLE
            binding?.registerBtn?.visibility = View.GONE
            binding?.loginOrRegisterBtn?.text = "确认注册"
        }

        binding?.loginOrRegisterBtn?.setOnClickListener {
            if (type == Intent_Type_Value) {
                login()
            } else {

            }
        }
    }

    fun login() {
        viewModel?.login()
    }
}