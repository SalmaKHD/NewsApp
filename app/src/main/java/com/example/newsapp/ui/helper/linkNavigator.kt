package com.example.newsapp.ui.helper

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.widget.Toast

fun Context.openInBrowser(linkUrl: String) {
    // to solve query problem: https://developer.android.com/training/package-visibility
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl))
    val possibleActivitiesList: List<ResolveInfo> =
        packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL)
    val viewIntent =
        if (possibleActivitiesList.isNotEmpty())
            Intent.createChooser(intent, "Select an app to open link")
        else intent

    if (viewIntent.resolveActivity(packageManager) != null) {
        startActivity(viewIntent)
    } else {
        Toast.makeText(this, "No apps found", Toast.LENGTH_SHORT).show()
    }
}