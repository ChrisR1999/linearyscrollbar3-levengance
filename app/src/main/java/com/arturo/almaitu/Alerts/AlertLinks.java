package com.arturo.almaitu.Alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.arturo.almaitu.Controladores.ControladorLinks;
import com.arturo.almaitu.Modelos.ModeloMultimedia;

import java.util.ArrayList;

public class AlertLinks extends AlertDialog {

    private Drawable image;
    private String title;
    private Context context;
    private Activity activity;

    public AlertLinks(Activity activity, Context context, String title, Drawable image) {
        super(context);
        this.activity = activity;
        this.context = context;
        this.title = title;
        this.image = image;
        initDialog();
    }

    private void initDialog() {
        final ArrayList<ModeloMultimedia> linkList = new ControladorLinks(context).getLinksAndDescriptions(title);
        //final AlertDialog.Builder build = new AlertDialog.Builder(new ContextThemeWrapper(activity, android.R.style.Theme_Holo_Light_DarkActionBar));
        //final AlertDialog.Builder build = new AlertDialog.Builder(new ContextThemeWrapper(activity, android.R.style.Theme_DeviceDefault_Dialog));
        final AlertDialog.Builder build = new AlertDialog.Builder(activity);
        final ScrollView scroll = new ScrollView(context);
        final ViewGroup.LayoutParams scrollParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(575, 850);
        final LinearLayout linear = new LinearLayout(context);
        final ImageView image = new ImageView(context);

        linear.setGravity(Gravity.CENTER);
        linear.setOrientation(LinearLayout.VERTICAL);

        scroll.setLayoutParams(scrollParams);
        scroll.addView(linear);

        image.setImageDrawable(this.image);
        image.setScaleType(ImageView.ScaleType.FIT_XY);

        params.setMargins(0, 45, 0, 10);
        image.setLayoutParams(params);
        linear.addView(image);

        for (final ModeloMultimedia s : linkList) {
            final Button[] link = new Button[3];
            for (int i = 0; i < link.length; i++) {
                link[i] = new Button(context);
                addLink(linear, link[i], i, s);
            }
        }

        build.setTitle(title);
        build.setView(scroll);
        build.setNegativeButton("Cerrar", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        build.create();
        build.show();
    }

    private void addLink(LinearLayout linear, Button button, int index, ModeloMultimedia model) {
        String link = "";
        String description = " ";
        switch (index) {
            case 0:
                link = model.getLink();
                description = model.getDescripcion();
                break;
            case 1:
                link = model.getLink2();
                description = model.getDescripcion2();
                break;
            case 2:
                link = model.getLink3();
                description = model.getDescripcion3();
                break;
        }
        if ((link != null) && (description != null)) {
            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    650,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 10, 0, 0);
            params.setMarginStart(10);
            params.setMarginEnd(10);
            button.setText(description);
            button.setLayoutParams(params);

            final String finalLink = link;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //final Intent intent = new Intent(activity, paginaweb.class);
                    //intent.putExtra("link", finalLink);
                    //activity.startActivity(intent);
                    apretar(finalLink);
                }
            });
            linear.addView(button);
        }
    }

    public void apretar(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);
    }
}
