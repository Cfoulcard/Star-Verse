package com.example.starverse.utilties

data class Resource<out T>(val status: UiStateStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(UiStateStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(UiStateStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(UiStateStatus.LOADING, data, null)
        }
    }
}
