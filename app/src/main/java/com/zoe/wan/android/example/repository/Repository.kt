package com.zoe.wan.android.example.repository

import com.zoe.wan.android.example.repository.data.HomeListData
import com.zoe.wan.android.http.BaseResponse
import com.zoe.wan.android.http.RetrofitClient

object Repository {

    suspend fun getHomeList(pageCount: String): HomeListData? {
        val data: BaseResponse<HomeListData?>? =
            RetrofitClient.getInstance().getDefault(ApiService::class.java).homeList(pageCount)

        if (data != null && data.getData() != null)
            return data.getData()
        return null
    }
}