package com.github.kr328.clash.core.model

import android.os.Parcel
import android.os.Parcelable
import com.github.kr328.clash.common.util.createListFromParcelSlice
import com.github.kr328.clash.common.util.writeToParcelSlice

class ProviderList(
    data: List<Provider>,
) : List<Provider> by data,
    Parcelable {
    constructor(parcel: Parcel) : this(Provider.createListFromParcelSlice(parcel, 0, 20))

    override fun describeContents(): Int = 0

    override fun writeToParcel(
        parcel: Parcel,
        flags: Int,
    ) = writeToParcelSlice(parcel, flags)

    companion object CREATOR : Parcelable.Creator<ProviderList> {
        override fun createFromParcel(parcel: Parcel): ProviderList = ProviderList(parcel)

        override fun newArray(size: Int): Array<ProviderList?> = arrayOfNulls(size)
    }
}
