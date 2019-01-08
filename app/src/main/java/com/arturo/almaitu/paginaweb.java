package com.arturo.almaitu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class paginaweb extends AppCompatActivity {

    private WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginaweb);
        initComponents();
        String url = getIntent().getStringExtra("link");
        wv1.loadUrl(url);
    }

    public void initComponents() {
        wv1 = (WebView) findViewById(R.id.web);
        WebSettings webSettings = wv1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        wv1.setWebViewClient(new WebViewClient());
    }
}
