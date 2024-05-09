package com.zoe.wan.android.example.repository

import com.zoe.wan.android.example.repository.data.HomeBannerData
import com.zoe.wan.android.example.repository.data.HomeListData
import com.zoe.wan.android.http.BaseResponse
import com.zoe.wan.android.http.RetrofitClient

object Repository {

    suspend fun getHomeList(pageCount: String): HomeListData? {
        val data: BaseResponse<HomeListData?>? = getDefault().homeList(pageCount)

        if (data != null && data.getData() != null)
            return data.getData()
        return null
    }

    suspend fun homeBanner():HomeBannerData? {
        val data: BaseResponse<HomeBannerData?>? = getDefault().homeBanner()

        if (data != null && data.getData() != null)
            return data.getData()
        return null
    }

    suspend fun login(username: String, password: String) {
        val data = getDefault().login(username, password)
    }

    private fun getDefault(): ApiService {
        return RetrofitClient.getInstance().getDefault(ApiService::class.java)
    }
}