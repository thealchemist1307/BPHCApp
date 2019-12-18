package com.crux.bphcfreshers

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class BPHCMap : FragmentActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bphcmaps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) { //        mMap = googleMap;
        val MBPHC = LatLng(17.544743, 78.572140)
        //        mMap.addMarker(new MarkerOptions().position(MBPHC).title("Welcome to BPHC"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MBPHC,16F));
        MapsInitializer.initialize(this@BPHCMap)
        mMap = googleMap
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.addMarker(MarkerOptions().position(MBPHC).title("Welcome to BPHC"))
        val camPos_BPHC = CameraPosition.builder().target(MBPHC).zoom(16f).bearing(0f).tilt(45f).build()
        mMap!!.moveCamera(CameraUpdateFactory.newCameraPosition(camPos_BPHC))
    }
}