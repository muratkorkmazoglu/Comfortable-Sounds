package com.murat.corfortablesounds.service

import com.murat.corfortablesounds.db.entities.SoundsEntitiy
import com.murat.corfortablesounds.service.response.JsonData
import io.reactivex.Observable
import retrofit2.http.*

interface SoundsAPI {

    @GET("birds.json")
    fun getBirdsSound(): Observable<List<SoundsEntitiy>>

    @GET("piano.json")
    fun getPianoSounds(): Observable<List<SoundsEntitiy>>

    @GET("nature.json")
    fun getNatureSounds(): Observable<List<SoundsEntitiy>>

}
