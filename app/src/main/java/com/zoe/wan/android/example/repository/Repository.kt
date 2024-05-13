package com.zoe.wan.android.example.repository

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.zoe.wan.android.example.activity.login.LoginActivity
import com.zoe.wan.android.example.repository.data.HomeBannerData
import com.zoe.wan.android.example.repository.data.HomeListData
import com.zoe.wan.android.example.repository.data.HomeTopListData
import com.zoe.wan.android.example.repository.data.UserData
import com.zoe.wan.android.http.BaseResponse
import com.zoe.wan.android.http.RetrofitClient
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.lang.ref.WeakReference

object Repository {

    private const val SUCCESS_CODE = 0
    private const val NEED_LOGIN_CODE = -1001
    private var mContext: WeakReference<Context>? = null

    /*
    * 初始化
    * */
    fun init (context: Context){
        mContext = WeakReference(context)
    }

    suspend fun getHomeList(pageCount: String): HomeListData? {
        val data: BaseResponse<HomeListData?>? = getDefault().homeList(pageCount)

        if (data?.getData() != null)
            return data.getData()
        return null
    }

    suspend fun getHomeTopList(): HomeTopListData? {
        val data: BaseResponse<HomeTopListData?>? = getDefault().topHomeList()
        return responseCall(data)
    }

    suspend fun homeBanner():HomeBannerData? {
        val data: BaseResponse<HomeBannerData?>? = getDefault().homeBanner()
        return responseCall(data)
    }

    suspend fun login(username: String, password: String): UserData? {
        val data: BaseResponse<UserData?>? = getDefault().login(username, password)
        return responseCall(data)
    }

    suspend fun register(username: String, password: String, repassword: String): UserData? {
        val data: BaseResponse<UserData?>? = getDefault().login(username, password, repassword)
        return responseCall(data)
    }

    /*
    * 1. code = 0 返回业务数据
    * 2. code = -1001 跳转到登录页
    * */
    suspend fun logout(): Boolean {
        val data = getDefault().logout()
        return responseNoDataCall(data)

    }

    /*
    * 收藏
    * */
    suspend fun collect(id: String): Boolean  {
        val data = getDefault().collect(id)
        return responseNoDataCall(data)

    }

    /*
    * 取消收藏
    * */
    suspend fun cancelCollect(id: String): Boolean  {
        val data = getDefault().cancelCollect(id)
        return responseNoDataCall(data)
    }


    private fun showToast(msg: String?) {
        GlobalScope.launch (Dispatchers.Main){
            ToastUtils.showShort(msg ?: "请求异常")
        }
    }

    private fun startToLogin() {
        mContext?.get()?.let {
            val intent = Intent(it, LoginActivity::class.java)
            intent.putExtra(LoginActivity.Intent_Type_Name, LoginActivity.Intent_Type_Value)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            it.startActivity(intent)
        }
    }

    private fun getDefault(): ApiService {
        return RetrofitClient.getInstance().getDefault(ApiService::class.java)
    }


    private fun <T> responseCall(response: BaseResponse<T?>?): T? {
        if (response == null) {
            showToast("请求异常")
            return null
        }
        if (response.getErrCode() == SUCCESS_CODE) {
            return response.getData()
        } else if(response.getErrCode() == NEED_LOGIN_CODE) {
            startToLogin()
            return null
        } else {
            showToast(response.getErrMsg())
            return null
        }
    }

    private fun responseNoDataCall(response: BaseResponse<Any?>?): Boolean {
        if (response == null) {
            showToast("请求异常")
            return false
        }
        if (response.getErrCode() == SUCCESS_CODE) {
            return true
        } else if(response.getErrCode() == NEED_LOGIN_CODE) {
            startToLogin()
            return false
        } else {
            showToast(response.getErrMsg())
            return false
        }
    }
}