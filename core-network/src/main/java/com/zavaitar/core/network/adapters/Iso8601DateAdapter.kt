package com.zavaitar.core.network.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Iso8601DateAdapter {
    private val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.UK)

    @FromJson
    fun dateFromJson(jsonString: String): Date {
        val dateAsString = jsonString.replace("\"".toRegex(), "")
        return formatter.parse(dateAsString)
    }

    @ToJson
    fun dateToJson(date: Date): String {
        return formatter.format(date)
    }
}
