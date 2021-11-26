package com.router.uwsmap.model

import android.os.Parcel
import android.os.Parcelable

data class Item(
    val addr: String,
    val code: String,
    var distance : Double,
    val color: String,
    val inventory: String,
    val lat: String,
    val lng: String,
    val name: String,
    val openTime: String,
    val price: String,
    val regDt: String,
    val tel: String
) : Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}