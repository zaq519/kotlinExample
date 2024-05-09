package com.zoe.wan.android.example.activity.login

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.compose.ui.unit.Constraints
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.zoe.wan.android.example.R
import com.zoe.wan.android.example.BR
import com.zoe.wan.android.example.Constants
import com.zoe.wan.android.example.activity.tab.TabActivity
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
                register()
            }
        }

        //进入注册页面
        binding?.registerBtn?.setOnClickListener {
            startIntent(LoginActivity::class.java, false)
        }
    }

    private fun login() {
        //登录成功后跳转到首页
        viewModel?.login {username ->
            SPUtils.getInstance().put(Constants.SP_USER_NAME, username)
            startIntent(TabActivity::class.java, false)
        }
    }

    private fun register() {
        //注册成功后跳转到登陆页面开始登录
        viewModel?.register { username ->
            startIntent(LoginActivity::class.java, true)
        }
    }

    fun startIntent(clazz: Class<*>, hasIntent: Boolean) {
        finish()
        val intent = Intent(this, clazz)
        if (hasIntent) {
            intent.putExtra(Intent_Type_Name, Intent_Type_Value)
        }
        startActivity(intent)
    }
}