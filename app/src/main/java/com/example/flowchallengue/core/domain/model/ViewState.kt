package com.example.flowchallengue.core.domain.model


sealed class ViewState<out T: Any>{
    object Loading : ViewState<Nothing>()
    data class Success<out T: Any>(val model: T?) : ViewState<T>()
    data class Error(val error : Throwable) : ViewState<Nothing>()
}
