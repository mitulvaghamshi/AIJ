package me.mitul.aij.utils;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import me.mitul.aij.R;

public class CustomNotification {
    private static final String NOTIFICATION_TAG = "AIJ";

    public static void notify(final Context context, final String exampleString, final int number) {
        final Resources res = context.getResources();
        final Bitmap picture = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
        final String title = "AIJ";
        final String text = res.getString(R.string.new_message_notification_placeholder_text_template, exampleString);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setDefaults(android.app.Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setLargeIcon(picture)
                .setTicker(exampleString)
                .setNumber(number)
                .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/")), PendingIntent.FLAG_UPDATE_CURRENT))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(text).setBigContentTitle(title).setSummaryText("Open Google"))
                .addAction(android.R.drawable.ic_menu_share, res.getString(R.string.action_share), PendingIntent.getActivity(context, 0, Intent.createChooser(new Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, context.getString(R.string.sms_text)), "Share"), PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true);
        notify(context, builder.build());
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private static void notify(final Context context, final android.app.Notification notification) {
        final NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NOTIFICATION_TAG, 0, notification);
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    public static void cancel(final Context context) {
        final NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(NOTIFICATION_TAG, 0);
    }
}
