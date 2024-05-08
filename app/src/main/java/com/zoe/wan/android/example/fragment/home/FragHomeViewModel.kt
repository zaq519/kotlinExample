package com.zoe.wan.android.example.fragment.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.zoe.wan.android.example.repository.Repository
import com.zoe.wan.android.example.repository.data.HomeListItemData
import com.zoe.wan.base.BaseViewModel
import com.zoe.wan.base.SingleLiveEvent
import kotlinx.coroutines.launch

class FragHomeViewModel(application: Application): BaseViewModel(application) {

    val list = SingleLiveEvent<List<HomeListItemData>?>()

    init {
        getHomeList()
    }

    private fun getHomeList() {
        //suspend方法只能在协程中调用
        viewModelScope.launch {
            val data = Repository.getHomeList("0")
            if (data != null) {
                list.postValue(data.datas)
            }
        }
    }

}