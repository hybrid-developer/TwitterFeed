package com.zavaitar.core.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class UserMentionX(
    @SerializedName("id") val id: Long,
    @SerializedName("id_str") val idStr: String,
    @SerializedName("indices") val indices: List<Int>,
    @SerializedName("name") val name: String,
    @SerializedName("screen_name") val screenName: String
)