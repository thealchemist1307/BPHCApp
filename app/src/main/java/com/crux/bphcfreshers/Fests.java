package com.crux.bphcfreshers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.FacebookSdk;

import static com.crux.bphcfreshers.FacebookLinks.FEG_PAGE_ID;

public class Fests extends AppCompatActivity {
    public static String ATMOS_URL = "https://www.facebook.com/bits.atmos/";
    public static String ATMOS_PAGE_ID = "ATMOS";
    public static String PEARL_URL = "https://www.facebook.com/bitspearl/";
    public static String PEARL_PAGE_ID = "PEARL";
    public static String ARENA_URL = "https://www.facebook.com/bits.arena/";
    public static String ARENA_PAGE_ID = "ARENA";
    public static String VERBA_URL = "https://www.facebook.com/verbamaximus/";
    public static String VERBA_PAGE_ID = "VERBA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fests);
        FacebookSdk.sdkInitialize(this);
        Button atmos=findViewById(R.id.ATMOS);
        atmos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(Fests.this,ATMOS_URL,ATMOS_PAGE_ID);
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        }); Button arena=findViewById(R.id.ARENA);
        arena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(Fests.this,ARENA_URL,ARENA_PAGE_ID);
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        }); Button verba=findViewById(R.id.VERBA);
        verba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(Fests.this,VERBA_URL,VERBA_PAGE_ID);
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        }); Button pearl=findViewById(R.id.PEARL);
        pearl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(Fests.this,PEARL_URL,PEARL_PAGE_ID);
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });
    }

    public String getFacebookPageURL (Context context, String url, String id){
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
