package com.zoe.wan.android.example.activity.tab

import android.app.Application
import androidx.databinding.ObservableField
import com.zoe.wan.base.BaseViewModel

class TabViewModel(application: Application): BaseViewModel(application) {

    val text = ObservableField<String>()

}