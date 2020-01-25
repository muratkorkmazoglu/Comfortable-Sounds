package com.mrtteknology.app.kotlinkarikaturapp.db.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Token")
data class TokenEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var accessToken: String?,
    var refreshToken: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(accessToken)
        parcel.writeString(refreshToken)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TokenEntity> {
        override fun createFromParcel(parcel: Parcel): TokenEntity {
            return TokenEntity(parcel)
        }

        override fun newArray(size: Int): Array<TokenEntity?> {
            return arrayOfNulls(size)
        }
    }
}