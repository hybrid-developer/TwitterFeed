package com.zavaitar.core.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ExtendedEntities(
    @SerializedName("media") val media: List<Media>
)