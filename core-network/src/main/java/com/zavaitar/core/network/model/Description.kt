package com.zavaitar.core.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Description(
    @SerializedName("urls") val urls: List<Any>
)