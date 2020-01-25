package com.murat.corfortablesounds.service

import com.murat.corfortablesounds.service.response.JsonData
import io.reactivex.Observable
import retrofit2.http.*

interface SoundsAPI {

    @GET("file:///Users/murat/AndroidStudioProjects/Comfortable%20Sounds/json.html")
    fun getJsonData(): Observable<JsonData>

}
