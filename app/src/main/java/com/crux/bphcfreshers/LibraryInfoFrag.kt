package com.crux.bphcfreshers

import android.content.ComponentName
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
import androidx.fragment.app.Fragment

class LibraryInfoFrag : Fragment() {
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
        CustomTabsClient.bindCustomTabsService(context, R.layout.activity_main.toString(), customTabsServiceConnection)
        customTabsIntent = CustomTabsIntent.Builder(customTabsSession[0])
                .setShowTitle(true)
                .setToolbarColor(Color.rgb(80, 70, 250))
                .build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_libraryinfo, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val libButton = view.findViewById<Button>(R.id.libInfo)
        libButton.setOnClickListener { URLopener("http://libraryopac.bits-hyderabad.ac.in/") }
    }
}