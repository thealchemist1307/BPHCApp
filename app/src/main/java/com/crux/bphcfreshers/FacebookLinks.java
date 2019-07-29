package com.crux.bphcfreshers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.FacebookSdk;

import bolts.AppLinks;

public class FacebookLinks extends AppCompatActivity {

    public static String FEG_URL = "https://www.facebook.com/groups/BPHC.Free.Expression.Group/";
    public static String FEG_PAGE_ID = "FEG";

    public static String SHOUT_URL = "https://www.facebook.com/groups/bphcshoutbox/";
    public static String SHOUT_PAGE_ID = "Shoutbox";
    public static String ANIME_URL = "https://www.facebook.com/groups/animeclub.bphc/";
    public static String ANIME_PAGE_ID = "Anime";
    public static String TRAVEL_URL = "https://www.facebook.com/groups/462587887267652/";
    public static String TRAVEL_PAGE_ID = "Travel";
    public static String DB_URL = "https://www.facebook.com/groups/BPHC.Free.Expression.Group/";
    public static String DB_PAGE_ID = "DB";
    public static String DC_URL = "https://www.facebook.com/groups/bphcdc/";
    public static String DC_PAGE_ID = "DB";
    public static String BUY_URL = "https://www.facebook.com/groups/173872382801665/";
    public static String BUY_PAGE_ID = "BUY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_links);
        FacebookSdk.sdkInitialize(this);

        Button feg = findViewById(R.id.feg);
        Button shout = findViewById(R.id.shoutbox);
        Button travel = findViewById(R.id.travel);
        Button db = findViewById(R.id.db);
        Button anime = findViewById(R.id.anime);
        Button buy = findViewById(R.id.buy);
        Button dc=findViewById(R.id.dc);
        feg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(FacebookLinks.this,FEG_URL,FEG_PAGE_ID);
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });
        shout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(FacebookLinks.this,SHOUT_URL,SHOUT_PAGE_ID);
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });
        anime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(FacebookLinks.this,ANIME_URL,ANIME_PAGE_ID);
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(FacebookLinks.this,BUY_URL,BUY_PAGE_ID);
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });
        db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(FacebookLinks.this,DB_URL,DB_PAGE_ID);
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });
        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(FacebookLinks.this,TRAVEL_URL,TRAVEL_PAGE_ID);
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });
        dc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(FacebookLinks.this,DC_URL,DC_PAGE_ID);
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });

//method to get the right URL to use in the intent

    }

    public String getFacebookPageURL (Context context,String url,String id){
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + url;
            } else { //older versions of fb app
                return "fb://page/" + id;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return url; //normal web url
        }
    }

}

