package com.crux.bphcfreshers

import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.crux.bphcfreshers.FacebookLinks
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser

class MainActivity<`var`> : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var navigationView: NavigationView? = null
    var headerView: View? = null
    var user_text: TextView? = null
    var email_text: TextView? = null
    var profile_pic: ImageView? = null
    private var mAuth: FirebaseAuth? = null
    private val currentUser: FirebaseUser? = null
    var mAuthListner: AuthStateListener? = null
    fun URLopener(url: String?) {
        val customTabsIntent: CustomTabsIntent
        val customTabsClient = arrayOfNulls<CustomTabsClient>(1)
        val customTabsSession = arrayOfNulls<CustomTabsSession>(1)
        val customTabsServiceConnection: CustomTabsServiceConnection = object : CustomTabsServiceConnection() {
            override fun onCustomTabsServiceConnected(componentName: ComponentName, client: CustomTabsClient) {
                customTabsClient[0] = client
                customTabsClient[0]!!.warmup(0L)
                customTabsSession[0] = customTabsClient[0]!!.newSession(null)
            }

            override fun onServiceDisconnected(name: ComponentName) {
                customTabsClient[0] = null
            }
        }
        CustomTabsClient.bindCustomTabsService(this, R.layout.activity_main.toString(), customTabsServiceConnection)
        customTabsIntent = CustomTabsIntent.Builder(customTabsSession[0])
                .setShowTitle(true)
                .setToolbarColor(Color.rgb(80, 70, 250))
                .build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }

    private val mLocationPermissionGranted = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        var navigationView = findViewById<NavigationView>(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.screenMain, CInfoFragment())
        fragmentTransaction.commit()
        mAuth = FirebaseAuth.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            username = user.displayName
            emailid = user.email
            pphoto = user.photoUrl
            navigationView = findViewById(R.id.nav_view)
            headerView = navigationView.getHeaderView(0)
            user_text = headerView?.findViewById(R.id.username)
            email_text = headerView?.findViewById(R.id.emailid)
            profile_pic = headerView?.findViewById(R.id.profilepic)
            user_text?.setText(username)
            email_text?.setText(emailid)
            profile_pic?.let { Glide.with(this).load(pphoto).into(it) }
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.cinfo) {
            fragment = CInfoFragment()
        } else if (id == R.id.campusMap) {
            val intent = Intent(this@MainActivity, BPHCMap::class.java)
            startActivity(intent)
        } else if (id == R.id.messInfo) {
            fragment = MessInfoFragment()
        } else if (id == R.id.libinfo) {
            fragment = LibraryInfoFrag()
        } else if (id == R.id.accInfo) {
            URLopener("http://swd.bits-hyderabad.ac.in/")
        } else if (id == R.id.timeTable) {
            val intent = Intent(this@MainActivity, timetableView::class.java)
            startActivity(intent)
        } else if (id == R.id.erp) {
            URLopener("https://erp.bits-pilani.ac.in:4431/psp/hcsprod/?cmd=login&languageCd=ENG")
        } else if (id == R.id.facebook) {
            val intent = Intent(this@MainActivity, FacebookLinks::class.java)
            startActivity(intent)
        } else if (id == R.id.signin) {
            if (mAuth!!.currentUser != null) {
                startActivity(Intent(this@MainActivity, Gsignout::class.java))
            } else {
                val intent = Intent(this@MainActivity, GSigni::class.java)
                startActivity(intent)
            }
            /*Intent intent = new Intent(MainActivity.this, GSigni.class);
            startActivity(intent);*/
        }
        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.screenMain, fragment)
            fragmentTransaction.commit()
        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    } // you have one. Use User.getToken() instead.

    companion object {
        var username: String? = ""
        var emailid: String? = ""
        var pphoto: Uri? = null
    }
}