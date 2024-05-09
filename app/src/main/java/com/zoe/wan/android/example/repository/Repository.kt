package com.zoe.wan.android.example.repository

import com.zoe.wan.android.example.repository.data.HomeBannerData
import com.zoe.wan.android.example.repository.data.HomeListData
import com.zoe.wan.android.example.repository.data.UserData
import com.zoe.wan.android.http.BaseResponse
import com.zoe.wan.android.http.RetrofitClient

object Repository {

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

    private fun getDefault(): ApiService {
        return RetrofitClient.getInstance().getDefault(ApiService::class.java)
    }
}