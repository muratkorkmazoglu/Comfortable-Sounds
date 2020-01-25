package com.mrtteknology.app.kotlinkarikaturapp.db.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Comic")
data class ComicsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "image")
    var image: String?,
    @ColumnInfo(name = "artistId")
    var artistId: String?,
    @ColumnInfo(name = "likeResult")
    var likeResult: Boolean? = false,
    @ColumnInfo(name = "like")
    var like: Int?,
    @ColumnInfo(name = "categoryName")
    var categoryName: String?,
    @ColumnInfo(name = "artistName")
    var artistName: String?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(image)
        parcel.writeString(artistId)
        parcel.writeValue(likeResult)
        parcel.writeValue(like)
        parcel.writeString(categoryName)
        parcel.writeString(artistName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ComicsEntity> {
        override fun createFromParcel(parcel: Parcel): ComicsEntity {
            return ComicsEntity(parcel)
        }

        override fun newArray(size: Int): Array<ComicsEntity?> {
            return arrayOfNulls(size)
        }
    }
}