package com.example.newsapp.ui.helper

import android.util.Log
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatIsoDate(isoDate: String): String {
    var formattedDate = "-"
    try {
        val instant = Instant.parse(isoDate)
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy • hh:mm a", Locale.US)
            .withZone(ZoneId.of("UTC"))  // Change zone if needed
        formattedDate = formatter.format(instant)
    } catch (e: IllegalStateException) {
        Log.d("ISO-FORMATTER","Error parsing date...")
    }
    return formattedDate
}