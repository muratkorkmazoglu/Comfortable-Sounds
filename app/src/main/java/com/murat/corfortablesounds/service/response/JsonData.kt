package com.murat.corfortablesounds.service.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class JsonData(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("sound_name")
    val soundName: String? = null,

    @field:SerializedName("singer_name")
    val singerName: String? = null,

    @field:SerializedName("mp3")
    val mp3: String? = null):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeValue(id)
        p0.writeString(soundName)
        p0.writeString(singerName)
        p0.writeString(mp3)
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<JsonData> {
        override fun createFromParcel(parcel: Parcel): JsonData {
            return JsonData(parcel)
        }

        override fun newArray(size: Int): Array<JsonData?> {
            return arrayOfNulls(size)
        }
    }
}
