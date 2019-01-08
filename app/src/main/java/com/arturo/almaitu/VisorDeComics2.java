package com.arturo.almaitu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.arturo.almaitu.Adapters.AdaptadorMain;
import com.arturo.almaitu.Adapters.AdaptadorMain2;
import com.arturo.almaitu.Controladores.ControladorLinks;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class VisorDeComics2 extends AppCompatActivity {

    private GridView gridComics;
    private ArrayList comicsCards;
    private AdaptadorMain2 adapter;
    private Toolbar toolbar;
    private EditText searchBar;
    public InterstitialAd mInterstitialAd;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visordecomics2_0);
        initComponents();
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void initComponents() {
        final String company = getIntent().getStringExtra("company");
        comicsCards = new ArrayList<>();
        gridComics = (GridView) findViewById(R.id.gridMain);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchBar = (EditText) findViewById(R.id.comicSearch);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String search = searchBar.getText().toString();
                getSearchedComics(search, company);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        gridComics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final TextView nameView = (TextView) view.findViewById(R.id.comicName);
                final String comicName = nameView.getText().toString();
            }
        });

        MobileAds.initialize(this, "ca-app-pub-5146175048698339~1301771677");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5146175048698339/2631165212");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        getAllComics(company);
    }

    private void getAllComics(String company) {
        ControladorLinks controller = new ControladorLinks(this);
        comicsCards = controller.getAllComicsByCompany(company);
        adapter = new AdaptadorMain2(this, comicsCards, this, mInterstitialAd);
        gridComics.setAdapter(adapter);
    }

    private void getSearchedComics(String search, String company) {
        ControladorLinks controller = new ControladorLinks(this);
        comicsCards = controller.getSearchedComics(search, company);
        adapter = new AdaptadorMain2(this, comicsCards, this, mInterstitialAd);
        gridComics.setAdapter(adapter);
    }
}
