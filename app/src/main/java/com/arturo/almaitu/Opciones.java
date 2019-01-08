package com.arturo.almaitu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.arturo.almaitu.Utillities.CacheUtilities;


public class Opciones extends AppCompatActivity {

    private Toolbar toolbar;
    private Switch cacheSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        initComponents();
    }

    private void initComponents() {
        CacheUtilities cache = new CacheUtilities(this);
        cacheSwitch = (Switch) findViewById(R.id.switchCache);
        toolbar = (Toolbar) findViewById(R.id.toolbarSinBarra);

        if ((CacheUtilities.getCacheStatus()) == 1) {
            cacheSwitch.setChecked(true);
        }

            cacheSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    final String toastText;
                    if (b) {
                        CacheUtilities.setCacheStatus(1);
                        toastText = getResources().getString(R.string.cacheHabilitada);
                        Toast.makeText(Opciones.this, toastText , Toast.LENGTH_SHORT).show();
                    }else {
                        CacheUtilities.setCacheStatus(0);
                        toastText = getResources().getString(R.string.cacheInhabilitada);
                        Toast.makeText(Opciones.this, toastText, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.opciones));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
