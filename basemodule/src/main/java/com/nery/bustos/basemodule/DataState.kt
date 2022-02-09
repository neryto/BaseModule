package com.nery.bustos.basemodule

sealed class DataState<out T>{
    data class Success<out T>(val data:T) : DataState<T>()
    data class Error(val message:String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()

}