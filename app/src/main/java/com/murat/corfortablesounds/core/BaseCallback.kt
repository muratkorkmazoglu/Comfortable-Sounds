package com.murat.corfortablesounds.core

interface BaseCallBack<T> {
    fun onSuccess(data: T)
    fun onFail(message: String)
}
