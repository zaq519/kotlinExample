package com.zoe.wan.android.example.repository

import com.zoe.wan.android.example.repository.data.HomeBannerData
import com.zoe.wan.android.example.repository.data.HomeListData
import com.zoe.wan.android.example.repository.data.UserData
import com.zoe.wan.android.http.ApiAddress.Article_List
import com.zoe.wan.android.http.ApiAddress.Home_Banner
import com.zoe.wan.android.http.ApiAddress.Login
import com.zoe.wan.android.http.ApiAddress.Register
import com.zoe.wan.android.http.BaseResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    /*
    * 获取首页列表数据
    * */
    @GET("$Article_List{pageCount}/json")
    suspend fun homeList(@Path("pageCount") pageCount: String): BaseResponse<HomeListData?>?

    /*
    * 获取banner数据
    * */
    @GET(Home_Banner)
    suspend fun homeBanner(): BaseResponse<HomeBannerData?>?

    @POST(Login)
    @FormUrlEncoded
    suspend fun login(@Field("username") username: String, @Field("password") password: String)
    : BaseResponse<UserData?>

    @POST(Register)
    @FormUrlEncoded
    suspend fun login(@Field("username") username: String,
                      @Field("password") password: String,
                      @Field("repassword") repassword: String
    ): BaseResponse<UserData?>
}