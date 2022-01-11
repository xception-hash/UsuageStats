# UsuageStats
Sample project for UsusageStats Manager

Add this permission in Manifest
 ```xml
 <uses-permission
        android:name="android.permission.PACKAGE_USAGE_STATS"
        tools:ignore="ProtectedPermissions" />
```
Get UsageStatsManager
```kotlin
 val usm = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
 val start = //start time in milliseconds 
 val end = System.currentTimeMillis() //end time in milliseconds
 ```
 Then query the stats manager
 ```kotlin
 usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, start, end)
 ```
