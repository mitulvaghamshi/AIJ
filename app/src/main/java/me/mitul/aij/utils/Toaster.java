package me.mitul.aij.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class Toaster extends Activity {
    private final Context mContext;

    public Toaster(Context context) {
        mContext = context;
    }

    public void showToast(String string) {
        Toast.makeText(mContext, string, Toast.LENGTH_LONG).show();
    }

    public void showDialog1(final String title) {
        showDialog3(title, "");
    }

    public void showDialog2(final String msg) {
        showDialog3("", msg);
    }

    public void showDialog3(final String title, final String msg) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                new AlertDialog
                        .Builder(mContext)
                        .setTitle(title)
                        .setMessage(msg)
                        .create().show();
            }
        });
    }
}
