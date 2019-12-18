package com.crux.bphcfreshers

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.crux.bphcfreshers.FacebookLinks
import com.facebook.FacebookSdk

class FacebookLinks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook_links)
        @Suppress("DEPRECATION")
        FacebookSdk.sdkInitialize(this)
        val feg = findViewById<Button>(R.id.feg)
        val shout = findViewById<Button>(R.id.shoutbox)
        val travel = findViewById<Button>(R.id.travel)
        val db = findViewById<Button>(R.id.db)
        val anime = findViewById<Button>(R.id.anime)
        val buy = findViewById<Button>(R.id.buy)
        val dc = findViewById<Button>(R.id.dc)
        feg.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            val facebookUrl = getFacebookPageURL(this@FacebookLinks, FEG_URL, FEG_PAGE_ID)
            facebookIntent.data = Uri.parse(facebookUrl)
            startActivity(facebookIntent)
        }
        shout.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            val facebookUrl = getFacebookPageURL(this@FacebookLinks, SHOUT_URL, SHOUT_PAGE_ID)
            facebookIntent.data = Uri.parse(facebookUrl)
            startActivity(facebookIntent)
        }
        anime.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            val facebookUrl = getFacebookPageURL(this@FacebookLinks, ANIME_URL, ANIME_PAGE_ID)
            facebookIntent.data = Uri.parse(facebookUrl)
            startActivity(facebookIntent)
        }
        buy.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            val facebookUrl = getFacebookPageURL(this@FacebookLinks, BUY_URL, BUY_PAGE_ID)
            facebookIntent.data = Uri.parse(facebookUrl)
            startActivity(facebookIntent)
        }
        db.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            val facebookUrl = getFacebookPageURL(this@FacebookLinks, DB_URL, DB_PAGE_ID)
            facebookIntent.data = Uri.parse(facebookUrl)
            startActivity(facebookIntent)
        }
        travel.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            val facebookUrl = getFacebookPageURL(this@FacebookLinks, TRAVEL_URL, TRAVEL_PAGE_ID)
            facebookIntent.data = Uri.parse(facebookUrl)
            startActivity(facebookIntent)
        }
        dc.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            val facebookUrl = getFacebookPageURL(this@FacebookLinks, DC_URL, DC_PAGE_ID)
            facebookIntent.data = Uri.parse(facebookUrl)
            startActivity(facebookIntent)
        }
        //method to get the right URL to use in the intent
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
        var FEG_URL = "https://www.facebook.com/groups/BPHC.Free.Expression.Group/"
        var FEG_PAGE_ID = "FEG"
        var SHOUT_URL = "https://www.facebook.com/groups/bphcshoutbox/"
        var SHOUT_PAGE_ID = "Shoutbox"
        var ANIME_URL = "https://www.facebook.com/groups/animeclub.bphc/"
        var ANIME_PAGE_ID = "Anime"
        var TRAVEL_URL = "https://www.facebook.com/groups/462587887267652/"
        var TRAVEL_PAGE_ID = "Travel"
        var DB_URL = "https://www.facebook.com/groups/BPHC.Free.Expression.Group/"
        var DB_PAGE_ID = "DB"
        var DC_URL = "https://www.facebook.com/groups/bphcdc/"
        var DC_PAGE_ID = "DB"
        var BUY_URL = "https://www.facebook.com/groups/173872382801665/"
        var BUY_PAGE_ID = "BUY"
    }
}