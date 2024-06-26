package com.zoe.wan.android.example.repository.data

/*
* 用户信息数据
* */
data class UserData(
    val admin: Boolean?,
    val chapterTops: List<Any?>?,
    val coinCount: Int?,
    val collectIds: List<Int?>?,
    val email: String?,
    val icon: String?,
    val id: Int?,
    val nickname: String?,
    val password: String?,
    val publicName: String?,
    val token: String?,
    val type: Int?,
    val username: String?
)