package com.arturo.almaitu;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.arturo.almaitu.Adapters.AdaptadorMain;
import com.arturo.almaitu.Controladores.ControladorLinks;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class Recomendacion extends AppCompatActivity {

    private ListView listComics;
    private ArrayList comicsCards;
    private AdaptadorMain adapter;
    private Toolbar toolbar;
    private EditText searchBar;
    public InterstitialAd mInterstitialAd;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendaciones);
        initComponents();
        //Notificacion();
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
          /*//Esta tambien esta en Adaptador main esta es una prueba para cuando se abra la activity
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }*/
    }

    private void initComponents() {
        int numero = (int) (Math.random() * 30) + 1;
        final String company = Integer.toString(numero);
        comicsCards = new ArrayList();
        listComics = (ListView) findViewById(R.id.listMain);

        /*listComics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final TextView nameView = (TextView) view.findViewById(R.id.comicName);
                final String comicName = nameView.getText().toString();
            }
        });*/

        toolbar = (Toolbar) findViewById(R.id.toolbarSinBarra);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.recomendacion));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        comicsCards = controller.getAllComicsByRandom(company);
        adapter = new AdaptadorMain(this, comicsCards, this, mInterstitialAd);
        listComics.setAdapter(adapter);
    }

    public void openCategories(View view) {
        Intent intent = new Intent(this, Categorias.class);
        startActivity(intent);
    }

    public void rechargeList(View view) {
        this.recreate();
    }

    /*public void Notificacion() {
        // Builds your notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("John's Android Studio Tutorials")
                .setContentText("A video has just arrived!");

        // Creates the intent needed to show the notification
        Intent notificationIntent = new Intent(this, Categorias.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }*/

}
