package com.router.uwsmap.model

import android.os.Parcel
import android.os.Parcelable

data class Item(
    val 가격: String,
    val 경도: String,
    var 거리: Double = 0.0,
    val 데이터기준일: String,
    val 명칭: String,
    val 영업시간: String,
    val 위도: String,
    val 재고량: String,
    val 전화번호: String,
    val 주소: String,
    val 코드: String
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
