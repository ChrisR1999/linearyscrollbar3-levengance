package com.arturo.almaitu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Categorias extends AppCompatActivity {
    private AdView mAdView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        MobileAds.initialize(this, "ca-app-pub-5146175048698339~1301771677");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        initComponents();
        // MobileAds.initialize(this, "ca-app-pub-5146175048698339~1301771677");

    }

    private void initComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbarSinBarra);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.categoriasMenu));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void iralcomic(View view) {
        final ImageButton button = (ImageButton) view.findViewById(view.getId());
        final String company = button.getContentDescription().toString();
        Intent intent = new Intent(this, VisorDeComics2.class);
        intent.putExtra("company", company);
        // Si hay conexión a Internet en este momento
        startActivity(intent);


    }
}
