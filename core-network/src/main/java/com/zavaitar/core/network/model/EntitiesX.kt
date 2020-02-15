package com.zavaitar.core.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class EntitiesX(
    @SerializedName("hashtags") val hashtags: List<Any>,
    @SerializedName("symbols") val symbols: List<Any>,
    @SerializedName("urls") val urls: List<UrlX>,
    @SerializedName("user_mentions") val userMentions: List<UserMentionX>
)