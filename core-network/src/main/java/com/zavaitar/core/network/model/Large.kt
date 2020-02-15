package com.zavaitar.core.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Large(
    @SerializedName("h") val h: Int,
    @SerializedName("resize") val resize: String,
    @SerializedName("w") val w: Int
)