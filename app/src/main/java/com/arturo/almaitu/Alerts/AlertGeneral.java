package com.arturo.almaitu.Alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.arturo.almaitu.R;

public class AlertGeneral extends AlertDialog {

    private Activity activity;
    private Context context;
    private final String title;
    private final String text;

    public AlertGeneral(Activity activity, Context context, String title, String text) {
        super(context);
        this.activity = activity;
        this.context = context;
        this.title = title;
        this.text = text;
        initAlert();
    }

    private void initAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(text);
        builder.setNegativeButton("Cerrar", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
        builder.create();
        builder.show();
    }
}
