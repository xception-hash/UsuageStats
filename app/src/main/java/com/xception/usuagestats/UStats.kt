package com.xception.usuagestats

import android.app.usage.UsageEvents
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.util.Log
import java.util.*

object UStats {

    fun getStats(context: Context) {
        val usm = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val end = System.currentTimeMillis()
        val start = end - 1000000
        Log.d(TAG, "Range start:" + Date(start))
        Log.d(TAG, "Range end:" + Date(end))

        val uEvents = usm.queryEvents(start, end)
        while (uEvents.hasNextEvent()) {
            val e = UsageEvents.Event()
            uEvents.getNextEvent(e)
            Log.d(TAG, "Event: " + e.packageName + "\t" + Date(e.timeStamp))

        }
    }

    fun getUsageStatsList(context: Context): List<UsageStats> {
        val usm = getUsageStatsManager(context)
        val end = System.currentTimeMillis()
        val start = end - 1000000
        Log.d(TAG, "Range start:" + Date(start))
        Log.d(TAG, "Range end:" + Date(end))

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
    private val TAG: String = UStats::class.java.simpleName
}