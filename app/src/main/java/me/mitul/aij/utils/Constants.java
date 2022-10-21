package me.mitul.aij.utils;

import android.graphics.Color;

import me.mitul.aij.R;

public class Constants {
    public static Integer[] drawableIcons = {
            R.drawable.ic_brightness_1_black_24dp,
            R.drawable.ic_brightness_1_blue_24dp,
            R.drawable.ic_brightness_1_green_24dp,
            R.drawable.ic_brightness_1_orange_24dp,
            R.drawable.ic_brightness_1_pink_24dp,
            R.drawable.ic_brightness_1_red_24dp,
            R.drawable.ic_brightness_1_teal_24dp,
            R.drawable.ic_brightness_1_yellow_24dp,
            R.drawable.ic_brightness_1_brown_24dp,
    };
    public static String KEY_ID = "id";
    public static String KEY_NAME = "name";
    public static String KEY_THUMB_URL = "thumb_url";
    public static String KEY_ENROLL = "enroll";
    public static String KEY_SERIAL = "serial";
    public int[] colorList = new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.BLACK, Color.MAGENTA};
    public String DB_NAME = "AIJ_DB.s3db";
    public String DB_PATH = "/data/data/me.mitul.dankawala/databases";
    public int DATABASE_VERSION = 1;
    private String DIRECTORY_SDCARD = "aij_db";
    private String PACKAGE_NAME = "me.mitul.aij";
    private String DEVICE_ID;

    public String getDIRECTORY_SDCARD() {
        return DIRECTORY_SDCARD;
    }

    public void setDIRECTORY_SDCARD(String DIRECTORY_SDCARD) {
        this.DIRECTORY_SDCARD = DIRECTORY_SDCARD;
    }

    public String getDB_NAME() {
        return DB_NAME;
    }

    public void setDB_NAME(String DB_NAME) {
        this.DB_NAME = DB_NAME;
    }

    public String getPACKAGE_NAME() {
        return PACKAGE_NAME;
    }

    public void setPACKAGE_NAME(String PACKAGE_NAME) {
        this.PACKAGE_NAME = PACKAGE_NAME;
    }

    public String getDEVICE_ID() {
        return DEVICE_ID;
    }

    public void setDEVICE_ID(String DEVICE_ID) {
        this.DEVICE_ID = DEVICE_ID;
    }

    // public static void setTheme(Context context, int theme) {
    //     SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    //     prefs.edit().putInt(context.getString(R.string.prefs_theme_key), theme).apply();
    // }
    //
    // public static int getTheme(Context context) {
    //     SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    //     return prefs.getInt(context.getString(R.string.prefs_theme_key), -1);
    // }
}
