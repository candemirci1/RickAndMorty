package com.example.rickandmorty.util

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {

    fun simpleCreated(data: String): String {

        val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        val date = simpleDateFormat.parse(data)
        val format = "EEE, d MMM yyyy"
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
        return dateFormatter.format(date!!)

    }
}