package com.example.moengage_sample

import android.app.Application
import com.moe.pushlibrary.MoEHelper
import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.config.LogConfig
import com.moengage.core.model.AppStatus
import java.util.*


/**
 * Created by Naveen Verma on 9/4/21.
 * To The New
 * naveen.verma@tothenew.com
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val moEngage: MoEngage = MoEngage.Builder(
            this, getString(R.string.mongege_key)
        )
            .configureLogs(LogConfig(LogLevel.VERBOSE, true))
            .build()
        MoEngage.initialise(moEngage)

        MoEHelper.getInstance(applicationContext).setAppStatus(AppStatus.INSTALL)
    }
}