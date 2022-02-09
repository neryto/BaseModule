package com.nery.bustos.basemodule.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nery.bustos.basemodule.DataState

open class BaseViewModel<T> : ViewModel() {

    protected val _fetchInfo: MutableLiveData<DataState<T>> = MutableLiveData()
    val fetchInfo: LiveData<DataState<T>>
        get() = _fetchInfo
}