package com.zavaitar.core.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Entities(
    @SerializedName("hashtags") val hashtags: List<Any>,
    @SerializedName("symbols") val symbols: List<Any>,
    @SerializedName("urls") val urls: List<Url>,
    @SerializedName("user_mentions") val userMentions: List<UserMention>
)