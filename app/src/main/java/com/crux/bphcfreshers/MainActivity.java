package com.crux.bphcfreshers;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity<var> extends AppCompatActivity

    implements NavigationView.OnNavigationItemSelectedListener{

    public static String username = "";
    public static String emailid = "";
    NavigationView navigationView;
    View headerView;
    public TextView user_text;
    public TextView email_text;


    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    FirebaseAuth.AuthStateListener mAuthListner;

    public void URLopener(String url) {

        CustomTabsIntent customTabsIntent;
        final CustomTabsClient[] customTabsClient = new CustomTabsClient[1];
        final CustomTabsSession[] customTabsSession = new CustomTabsSession[1];

        CustomTabsServiceConnection customTabsServiceConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient client) {
                customTabsClient[0] = client;
                customTabsClient[0].warmup(0L);
                customTabsSession[0] = customTabsClient[0].newSession(null);


            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                customTabsClient[0] = null;

            }
        };


        CustomTabsClient.bindCustomTabsService(this, String.valueOf(R.layout.activity_main), customTabsServiceConnection);
        customTabsIntent = new CustomTabsIntent.Builder(customTabsSession[0])
                .setShowTitle(true)
                .setToolbarColor(Color.rgb(80, 70, 250))
                .build();

        customTabsIntent.launchUrl(this, Uri.parse(url));

    }

    private boolean mLocationPermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.screenMain, new CInfoFragment());
        fragmentTransaction.commit();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            username = user.getDisplayName();
            emailid = user.getEmail();

            navigationView = findViewById(R.id.nav_view);
            headerView = navigationView.getHeaderView(0);

            user_text = headerView.findViewById(R.id.username);
            email_text = headerView.findViewById(R.id.emailid);
            user_text.setText(username);
            email_text.setText(emailid);

        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.cinfo) {
            fragment = new CInfoFragment();
        } else if (id == R.id.campusMap) {
            Intent intent = new Intent(MainActivity.this, BPHCMap.class);
            startActivity(intent);
        } else if (id == R.id.messInfo) {
            fragment = new MessInfoFragment();
        } else if (id == R.id.libinfo) {
            fragment = new LibraryInfoFrag();
        } else if (id == R.id.accInfo) {
            URLopener("http://swd.bits-hyderabad.ac.in/");
        } else if (id == R.id.timeTable) {
            fragment = new TimeTableFragment();
        } else if (id == R.id.erp) {
            URLopener("https://erp.bits-pilani.ac.in:4431/psp/hcsprod/?cmd=login&languageCd=ENG");
        }
        else if (id == R.id.facebook) {
            Intent intent = new Intent(MainActivity.this, FacebookLinks.class);
            startActivity(intent);
        }
        else if (id == R.id.signin) {
             if (mAuth.getCurrentUser() != null) {
                        startActivity(new Intent(MainActivity.this, Gsignout.class));
                    }
                else
                    {
                        Intent intent = new Intent(MainActivity.this, GSigni.class);
                        startActivity(intent);

                    }
            /*Intent intent = new Intent(MainActivity.this, GSigni.class);
            startActivity(intent);*/

        }




        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screenMain, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
     // you have one. Use User.getToken() instead.
    }





