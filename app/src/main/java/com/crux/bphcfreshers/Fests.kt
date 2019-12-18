package com.crux.bphcfreshers

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.crux.bphcfreshers.Fests
import com.facebook.FacebookSdk

class Fests : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fests)
        @Suppress("DEPRECATION")
        FacebookSdk.sdkInitialize(this)
        val atmos = findViewById<Button>(R.id.ATMOS)
        atmos.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            val facebookUrl = getFacebookPageURL(this@Fests, ATMOS_URL, ATMOS_PAGE_ID)
            facebookIntent.data = Uri.parse(facebookUrl)
            startActivity(facebookIntent)
        }
        val arena = findViewById<Button>(R.id.ARENA)
        arena.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            val facebookUrl = getFacebookPageURL(this@Fests, ARENA_URL, ARENA_PAGE_ID)
            facebookIntent.data = Uri.parse(facebookUrl)
            startActivity(facebookIntent)
        }
        val verba = findViewById<Button>(R.id.VERBA)
        verba.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            val facebookUrl = getFacebookPageURL(this@Fests, VERBA_URL, VERBA_PAGE_ID)
            facebookIntent.data = Uri.parse(facebookUrl)
            startActivity(facebookIntent)
        }
        val pearl = findViewById<Button>(R.id.PEARL)
        pearl.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            val facebookUrl = getFacebookPageURL(this@Fests, PEARL_URL, PEARL_PAGE_ID)
            facebookIntent.data = Uri.parse(facebookUrl)
            startActivity(facebookIntent)
        }
    }

    fun getFacebookPageURL(context: Context, url: String, id: String): String {
        val packageManager = context.packageManager
        return try {
            @Suppress("DEPRECATION") val versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode
            if (versionCode >= 3002850) { //newer versions of fb app
                "fb://facewebmodal/f?href=$url"
            } else { //older versions of fb app
                "fb://page/$id"
            }
        } catch (e: PackageManager.NameNotFoundException) {
            url //normal web url
        }
    }

    companion object {
        var ATMOS_URL = "https://www.facebook.com/bits.atmos/"
        var ATMOS_PAGE_ID = "ATMOS"
        var PEARL_URL = "https://www.facebook.com/bitspearl/"
        var PEARL_PAGE_ID = "PEARL"
        var ARENA_URL = "https://www.facebook.com/bits.arena/"
        var ARENA_PAGE_ID = "ARENA"
        var VERBA_URL = "https://www.facebook.com/verbamaximus/"
        var VERBA_PAGE_ID = "VERBA"
    }
}