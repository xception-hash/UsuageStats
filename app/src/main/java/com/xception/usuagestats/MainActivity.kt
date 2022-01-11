package com.xception.usuagestats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Check if permission enabled
        if (UStats.getUsageStatsList(this).isEmpty()) {
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            startActivity(intent)
        }

        findViewById<Button>(R.id.stats_btn).setOnClickListener {
            //call one of the method below
            UStats.printCurrentUsageStatus(this@MainActivity)
            UStats.getStats(this)
        }
    }
}