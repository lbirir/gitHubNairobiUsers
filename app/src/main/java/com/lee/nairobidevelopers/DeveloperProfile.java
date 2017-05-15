package com.lee.nairobidevelopers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class DeveloperProfile extends AppCompatActivity {
    WebView wbViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_profile);

        String url = getIntent().getExtras().getString("profileUrl");
        wbViewProfile = (WebView)findViewById(R.id.webViewProfile);
        wbViewProfile.loadUrl(url);
    }
}
