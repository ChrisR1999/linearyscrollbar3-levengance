package com.arturo.almaitu.Alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.arturo.almaitu.Controladores.ControladorLinks;
import com.arturo.almaitu.Modelos.ModeloMultimedia;

import java.util.ArrayList;

public class AlertLinks extends AlertDialog {

    private ImageView image;
    private String title;
    private Context context;
    private Activity activity;

    public AlertLinks(Activity activity, Context context, String title, ImageView image) {
        super(context);
        this.activity = activity;
        this.context = context;
        this.title = title;
        this.image = new ImageView(context);
        this.image.setLayoutParams(image.getLayoutParams());
        this.image.setScaleType(ImageView.ScaleType.FIT_XY);
        this.image.setImageDrawable(image.getDrawable());
        initDialog();
    }

    private void initDialog() {
        final ArrayList<ModeloMultimedia> linkList = new ControladorLinks(context).getLinksAndDescriptions(title);
        final AlertDialog.Builder build = new AlertDialog.Builder(activity);
        final LinearLayout linear = new LinearLayout(context);
        final ScrollView scroll = new ScrollView(context);
        final LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        final LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );


        linear.setGravity(Gravity.CENTER);
        linear.setLayoutParams(linearParams);
        linear.setOrientation(LinearLayout.VERTICAL);

        scrollParams.setMargins(0, 10, 0, 0);
        scroll.setLayoutParams(scrollParams);
        scroll.addView(linear);

        linear.addView(image);

        for (final ModeloMultimedia s : linkList) {
            final Button[] link = new Button[3];
            for (int i = 0; i < link.length; i++) {
                link[i] = new Button(context);
                addLink(linear, link[i], i, s, image.getLayoutParams().width);
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

    private void addLink(LinearLayout linear, Button button, int index, ModeloMultimedia model, int width) {
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
                    width,
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
