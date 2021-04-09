package com.example.moengage_sample

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.moe.pushlibrary.MoEHelper
import com.moe.pushlibrary.models.GeoLocation
import com.moengage.core.Properties
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun fetchAdvertisingId() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = async {
                return@async AdvertisingIdClient.getAdvertisingIdInfo(this@MainActivity)
            }.await()

            // adding GoogleAdd-Id (Unique-id)
            val googleAddId = result.id
            Log.i(TAG, "fetchAdvertisingId: $googleAddId")
            MoEHelper.getInstance(applicationContext).setUniqueId(googleAddId)
        }

    }

    fun trackEvents(view: View) {
        val properties = Properties()
        properties
            .addAttribute("id", 2)
            .addAttribute("name", "naveen")
            .addAttribute("date_of_registration", java.util.Date())
            .addAttribute("user_Location", GeoLocation(40.77, 73.98))
        MoEHelper.getInstance(applicationContext).trackEvent("User-Details", properties)

        makeToast(this, "Event track successfully")
    }

    fun loginTrack(view: View) {
        fetchAdvertisingId()

        makeToast(this, "Login successfully")
    }

    fun logoutTrack(view: View) {
        MoEHelper.getInstance(applicationContext).logoutUser()

        makeToast(this, "Logout successfully")
    }
}

private fun makeToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}
