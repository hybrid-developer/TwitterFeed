package com.zavaitar.core.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class UrlX(
    @SerializedName("display_url") val displayUrl: String,
    @SerializedName("expanded_url") val expandedUrl: String,
    @SerializedName("indices") val indices: List<Int>,
    @SerializedName("url") val url: String
)