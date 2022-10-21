package me.mitul.aij.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParser {
    public String a(String string) {
        try {
            Date date = new SimpleDateFormat("MM-yyyy", Locale.US).parse(string);
            return new SimpleDateFormat("MMM,yyyy", Locale.US).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String b(String string) {
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(string);
            return new SimpleDateFormat("dd-MMM-yyyy,EEE", Locale.US).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String c(String string) {
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(string);
            return new SimpleDateFormat("dd-MMM,EEE", Locale.US).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String d(String string) {
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(string);
            return new SimpleDateFormat("dd,EEE", Locale.US).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String e(String string) {
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(string);
            return new SimpleDateFormat("dd-MM-yyyy,EEE", Locale.US).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
