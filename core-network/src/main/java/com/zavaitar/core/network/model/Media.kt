package com.zavaitar.core.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Media(
    @SerializedName("display_url") val displayUrl: String,
    @SerializedName("expanded_url") val expandedUrl: String,
    @SerializedName("id") val id: Long,
    @SerializedName("id_str") val idStr: String,
    @SerializedName("indices") val indices: List<Int>,
    @SerializedName("media_url") val mediaUrl: String,
    @SerializedName("media_url_https") val mediaUrlHttps: String,
    @SerializedName("sizes") val sizes: Sizes,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)