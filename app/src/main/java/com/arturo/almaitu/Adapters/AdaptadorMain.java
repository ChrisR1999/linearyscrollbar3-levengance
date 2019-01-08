package com.arturo.almaitu.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arturo.almaitu.Alerts.AlertLinks;
import com.arturo.almaitu.Modelos.ModeloMultimedia;
import com.arturo.almaitu.R;
import com.arturo.almaitu.Utillities.CacheUtilities;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class AdaptadorMain extends BaseAdapter {

    private Activity activity;
    private ArrayList<ModeloMultimedia> items;
    private Context contexto;
    private InterstitialAd mInterstitialAd;
    private String foto = "";
    private int cache;

    public AdaptadorMain(Activity activity, ArrayList<ModeloMultimedia> items, Context contexto, InterstitialAd mInterstitialAd) {
        this.activity = activity;
        this.items = items;
        this.contexto = contexto;
        this.mInterstitialAd = mInterstitialAd;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final ModeloMultimedia dir;
        final TextView comicName;
        final ImageView comicImage;
        final Button button;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.comiccard, null);
        }

        dir = items.get(position);
        comicName = (TextView) v.findViewById(R.id.comicName);
        comicImage = (ImageView) v.findViewById(R.id.comicImage);
        button = (Button) v.findViewById(R.id.buttonLink);
        comicName.setText(dir.getNombre());
        foto = dir.getNombreImagen();
        //comicImage.setImageDrawable(createImage(dir));
        if (CacheUtilities.getCacheStatus() == 1) {
            Glide.with(contexto).load(foto).placeholder(R.mipmap.alitas).into(comicImage);
        } else {
            Glide.with(contexto).load(R.mipmap.alitas).placeholder(R.mipmap.alitas).into(comicImage);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                openDialog(dir.getNombre(), comicImage.getDrawable());
            }
        });
        return v;
    }

    private void openDialog(String comicName, Drawable image) {
        AlertLinks alert = new AlertLinks(activity, contexto, comicName, image);
    }
}