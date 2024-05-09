package com.zoe.wan.android.example.activity.login

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ToastUtils
import com.zoe.wan.android.example.repository.Repository
import com.zoe.wan.base.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): BaseViewModel(application) {

    val usernameInput = ObservableField<String>()
    val passwordInput = ObservableField<String>()
    val passwordTwiceInput = ObservableField<String>()


    fun login() {
        val name = usernameInput.get()
        val pwd = passwordInput.get()
        if (name.isNullOrEmpty() || pwd.isNullOrEmpty()) {
            ToastUtils.showShort("输入不能为空")
            return
        }
        viewModelScope.launch {
            Repository.login(name, pwd)
        }
    }
}