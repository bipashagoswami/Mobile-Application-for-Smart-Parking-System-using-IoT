package com.testing.parkpro;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class Loading {
    private Activity activity;
    private AlertDialog dialog;

    Loading(Activity myActivity){
        activity=myActivity;
    }

    void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();

    }

    void dismissLoading(){
        dialog.dismiss();
    }
}


