package com.sanjay.androidamcservice.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sanjay.androidamcservice.ui.activities.RegistrationActivity;

public class AlertBoxUtils {
    Context context;


    public AlertBoxUtils(Context context) {
        this.context = context;
    }

    public void registerAlertBox(String title, String message, String postivieBtn, String negativeBtn) {
        new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(postivieBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.startActivity(new Intent(context, RegistrationActivity.class));
                    }
                })
                .setNegativeButton(negativeBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }
}
