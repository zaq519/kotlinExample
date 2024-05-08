package com.zoe.wan.android.example.repository

import com.zoe.wan.android.example.repository.data.HomeListData
import com.zoe.wan.android.http.ApiAddress.Article_List
import com.zoe.wan.android.http.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    /*
    * 获取首页列表数据
    * */
    @GET("$Article_List{pageCount}/json")
    suspend fun homeList(@Path("pageCount") pageCount: String): BaseResponse<HomeListData?>?
}