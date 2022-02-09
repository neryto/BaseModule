package com.nery.bustos.basemodule.network

import com.google.gson.Gson
import com.nery.bustos.basemodule.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import org.json.JSONObject
import retrofit2.Response
import java.lang.reflect.Type

fun <T> Response<T>.toFlow(scope: CoroutineScope,typeToken: Type)
        : StateFlow<DataState<T>> = flow {
    val response = this@toFlow
     if (response.isSuccessful){
         val res = Gson().toJson(response.body())
         val dataOut = fromJson<T>(res, typeToken)
         emit(DataState.Success(dataOut))
     }else{
         val res =   response.errorBody()?.string()?:""
         val jObject = JSONObject(res)
         val message : String =
             jObject.get("error_key") as String //TODO HANDLE ERROR
         emit(DataState.Error(message))
     }

}
    .catch {
        emit(DataState.Error("An error occurred, try again"))
    }
    .stateIn(
    scope = scope,
    started = SharingStarted.Eagerly,
    initialValue = DataState.Loading
)

private fun <T> fromJson(json: String, typeToken: Type): T {
    return Gson().fromJson(json, typeToken)

}