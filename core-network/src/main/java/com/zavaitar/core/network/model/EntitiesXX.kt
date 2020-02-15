package com.zavaitar.core.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class EntitiesXX(
    @SerializedName("description") val description: Description,
    @SerializedName("url") val url: UrlXX
)