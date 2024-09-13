package com.github.kr328.clash.core.model

import android.os.Parcel
import android.os.Parcelable
import com.github.kr328.clash.core.util.Parcelizer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TunnelState(
    val mode: Mode,
) : Parcelable {
    @Serializable
    enum class Mode {
        @SerialName("direct")
        Direct,

        @SerialName("global")
        Global,

        @SerialName("rule")
        Rule,

        @SerialName("script")
        Script,
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        Parcelizer.encodeToParcel(serializer(), parcel, this)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<TunnelState> {
        override fun createFromParcel(parcel: Parcel): TunnelState = Parcelizer.decodeFromParcel(serializer(), parcel)

        override fun newArray(size: Int): Array<TunnelState?> = arrayOfNulls(size)
    }
}
