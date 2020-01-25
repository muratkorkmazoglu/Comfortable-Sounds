package com.murat.corfortablesounds.service

import com.murat.corfortablesounds.service.response.JsonData
import io.reactivex.Observable
import retrofit2.http.*

interface SoundsAPI {

    @GET("sounds.json")
    fun getJsonData(): Observable<List<JsonData>>

}
