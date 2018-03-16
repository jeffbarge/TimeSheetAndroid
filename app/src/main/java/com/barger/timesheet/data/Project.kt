package com.barger.timesheet.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity(tableName = "projects")
class Project(@PrimaryKey(autoGenerate = true)
              var id: Int,
              var name: String) : Parcelable {

    private constructor(p: Parcel) : this(id = p.readInt(), name = p.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.apply {
            writeInt(id)
            writeString(name)
        }
    }

    override fun describeContents() = 0

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<Project> {
            override fun createFromParcel(parcel: Parcel) = Project(parcel)

            override fun newArray(size: Int) = arrayOfNulls<Project>(size)
        }
    }
}