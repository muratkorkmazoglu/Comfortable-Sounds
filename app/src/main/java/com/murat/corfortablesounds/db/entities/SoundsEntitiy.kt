package com.murat.corfortablesounds.db.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.room.*
import com.murat.corfortablesounds.service.response.JsonData


@Entity(tableName = "Sounds")
data class SoundsEntitiy(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "sound_name")
    var soundName: String?,
    @ColumnInfo(name = "singer_name")
    var singerName: String?,
    @ColumnInfo(name = "mp3")
    var mp3: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(soundName)
        parcel.writeString(singerName)
        parcel.writeString(mp3)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SoundsEntitiy> {
        override fun createFromParcel(parcel: Parcel): SoundsEntitiy {
            return SoundsEntitiy(parcel)
        }

        override fun newArray(size: Int): Array<SoundsEntitiy?> {
            return arrayOfNulls(size)
        }
    }
}