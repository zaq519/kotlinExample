package com.zoe.wan.android.example

import android.app.Application
import com.zoe.wan.android.example.repository.Repository
import com.zoe.wan.android.http.RetrofitClient

class WanAPP: Application() {

    override fun onCreate() {
        super.onCreate()
        //初始化三方SDK
        Repository.init(applicationContext)
        RetrofitClient.getInstance().setContext(applicationContext)
    }
}