package com.zoe.wan.android.example.repository

import android.content.Context
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.zoe.wan.android.example.repository.data.HomeBannerData
import com.zoe.wan.android.example.repository.data.HomeListData
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

    suspend fun homeBanner():HomeBannerData? {
        val data: BaseResponse<HomeBannerData?>? = getDefault().homeBanner()

        if (data?.getData() != null)
            return data.getData()
        return null
    }

    suspend fun login(username: String, password: String): UserData? {
        val data: BaseResponse<UserData?>? = getDefault().login(username, password)

        if (data?.getData() != null)
            return data.getData()
        return null
    }

    suspend fun register(username: String, password: String, repassword: String): UserData? {
        val data: BaseResponse<UserData?>? = getDefault().login(username, password, repassword)

        if (data?.getData() != null)
            return data.getData()
        return null
    }

    /*
    * 1. code = 0 返回业务数据
    * 2. code = -1001 跳转到登录页
    * */
    private fun getDefault(): ApiService {
        return RetrofitClient.getInstance().getDefault(ApiService::class.java)
    }


    private fun <T> responseCall(response: BaseResponse<T?>?): T? {
        if (response == null) {
            GlobalScope.launch (Dispatchers.Main){
                ToastUtils.showShort("请求异常")
            }
            return null
        }
        if (response.getErrCode() == SUCCESS_CODE) {
            return response.getData()
        } else if(response.getErrCode() == NEED_LOGIN_CODE) {
            mContext?.get()?.applicationContext?.let {
                it.startActivity()
            }
        }
    }
}