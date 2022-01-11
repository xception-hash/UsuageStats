package com.xception.usuagestats

import android.app.usage.UsageStatsManager
import com.xception.usuagestats.UStats
import android.app.usage.UsageEvents
import android.app.usage.UsageStats
import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object UStats {
    private val dateFormat = SimpleDateFormat("M-d-yyyy HH:mm:ss", Locale.US)
    val TAG = UStats::class.java.simpleName

    fun getStats(context: Context) {
        val usm = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val interval = UsageStatsManager.INTERVAL_YEARLY
        val calendar = Calendar.getInstance()
        val endTime = calendar.timeInMillis
        calendar.add(Calendar.YEAR, -1)
        val startTime = calendar.timeInMillis
        Log.d(TAG, "Range start:" + dateFormat.format(startTime))
        Log.d(TAG, "Range end:" + dateFormat.format(endTime))
        val end = System.currentTimeMillis()
        val start = end - 1000000
        val uEvents = usm.queryEvents(start, end)
        while (uEvents.hasNextEvent()) {
            val e = UsageEvents.Event()
            uEvents.getNextEvent(e)
            Log.d(TAG, "Event: " + e.packageName + "\t" + Date(e.timeStamp))

        }
    }

    fun getUsageStatsList(context: Context): List<UsageStats> {
        val usm = getUsageStatsManager(context)
        val calendar = Calendar.getInstance()
        val endTime = calendar.timeInMillis
        calendar.add(Calendar.YEAR, -1)
        val startTime = calendar.timeInMillis
        Log.d(TAG, "Range start:" + dateFormat.format(startTime))
        Log.d(TAG, "Range end:" + dateFormat.format(endTime))
        val end = System.currentTimeMillis()
        val start = end - 1000000
        return usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, start, end)
    }

    fun printUsageStats(usageStatsList: List<UsageStats>) {
        for (u in usageStatsList) {
            Log.d(
                TAG, "Pkg: " + u.packageName + "\t" + "ForegroundTime: "
                        + u.totalTimeInForeground
            )
        }
    }

    fun printCurrentUsageStatus(context: Context) {
        printUsageStats(getUsageStatsList(context))
    }

    private fun getUsageStatsManager(context: Context): UsageStatsManager {
        return context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
    }
}