package com.arturo.almaitu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.arturo.almaitu.Alerts.AlertGeneral;
import com.arturo.almaitu.Utillities.CacheUtilities;

public class MenuInicio extends AppCompatActivity {

    private Button categoryButton;
    private Button recomendButton;
    private Button optionsButton;
    private Button contactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);
        initComponents();
    }

    private void initComponents() {
        CacheUtilities cache;
        categoryButton = (Button) findViewById(R.id.categoryButton);
        recomendButton = (Button) findViewById(R.id.recomendButton);
        optionsButton = (Button) findViewById(R.id.optionsButton);
        contactButton = (Button) findViewById(R.id.contactButton);
        cache = new CacheUtilities(this);

        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(MenuInicio.this, Categorias.class);
                startActivity(intent);
            }
        });

        recomendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(MenuInicio.this, Recomendacion.class);
                startActivity(intent);
            }
        });

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String title = getResources().getString(R.string.contacto);
                final String text = getResources().getString(R.string.aviso);
                AlertGeneral alert = new AlertGeneral(MenuInicio.this, MenuInicio.this, title, text);
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(MenuInicio.this, Opciones.class);
                startActivity(intent);
            }
        });
    }
}
