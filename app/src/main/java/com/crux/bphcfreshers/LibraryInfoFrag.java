package com.crux.bphcfreshers;

import android.content.ComponentName;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class   LibraryInfoFrag extends Fragment {

    public void URLopener (String url) {

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


        CustomTabsClient.bindCustomTabsService(getContext(), String.valueOf(R.layout.activity_main), customTabsServiceConnection);
        customTabsIntent = new CustomTabsIntent.Builder(customTabsSession[0])
                .setShowTitle(true)
                .setToolbarColor(Color.rgb(80,70,250))
                .build();

        customTabsIntent.launchUrl(getContext(), Uri.parse(url));

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_libraryinfo, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button libButton = view.findViewById(R.id.libInfo);

        libButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URLopener("http://libraryopac.bits-hyderabad.ac.in/");
            }
        });

    }
}
