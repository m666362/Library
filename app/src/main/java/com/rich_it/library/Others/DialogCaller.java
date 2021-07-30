package com.rich_it.library.Others;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.StringRequestListener;
import com.rich_it.library.R;

import java.util.logging.LogManager;


public class DialogCaller {

    private static final String TAG = DialogCaller.class.getName();
    private Activity activity;
    private AlertDialog alertDialog;

    public DialogCaller(Activity activity) {
        this.activity = activity;
    }

    public static void showDialog(Context context, String title, String message, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, onClickListener)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    public static void showFullDialog(Context context, String title, String message, DialogInterface.OnClickListener positiveButtonOnClickListener, DialogInterface.OnClickListener negativeButtonOnClickListener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, positiveButtonOnClickListener)
                .setNegativeButton(android.R.string.no, negativeButtonOnClickListener)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    public static void showErrorAlert(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    public void showLoading() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        builder
                .setView(layoutInflater.inflate(R.layout.custon_dialog, null))
                .setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();

    }

    public void dismissDialog(){
        alertDialog.dismiss();
    }
}
