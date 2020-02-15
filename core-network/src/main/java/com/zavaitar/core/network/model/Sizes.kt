package com.zavaitar.core.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Sizes(
    @SerializedName("large") val large: Large,
    @SerializedName("medium") val medium: Medium,
    @SerializedName("small") val small: Small,
    @SerializedName("thumb") val thumb: Thumb
)