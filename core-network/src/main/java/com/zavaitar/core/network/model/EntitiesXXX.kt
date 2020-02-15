package com.zavaitar.core.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class EntitiesXXX(
    @SerializedName("description") val description: DescriptionX,
    @SerializedName("url") val url: UrlXXXX
)