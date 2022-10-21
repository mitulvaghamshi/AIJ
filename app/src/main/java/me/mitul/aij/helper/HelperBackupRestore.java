package me.mitul.aij.helper;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class HelperBackupRestore {
    private static final File DATABASE_DIRECTORY = new File(Environment.getExternalStorageDirectory(), ".mady_db_");
    private static final File IMPORT_FILE = new File(DATABASE_DIRECTORY, "AIJ_DB");
    private static final File DATA_DIRECTORY_DATABASE = new File(Environment.getDataDirectory() + "/data/" + "me.mitul.aij" + "/databases/" + "AIJ_DB.s3db");
    private final File SDCARD_DIRECTORY = new File(Environment.getExternalStorageDirectory(), ".aij");

    public static boolean exportDb() {
        if (SdIsPresent()) {
            return false;
        }
        String filename = "AIJ_DB";
        File exportDir = DATABASE_DIRECTORY;
        File file = new File(exportDir, filename);
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        try {
            file.createNewFile();
            copyFile(DATA_DIRECTORY_DATABASE, file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void restoreDb() {
        if (SdIsPresent()) {
            return;
        }
        File exportFile = DATA_DIRECTORY_DATABASE;
        if (!sdDatabaseExists()) {
            return;
        }
        try {
            exportFile.createNewFile();
            copyFile(IMPORT_FILE, exportFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean sdDatabaseExists() {
        return IMPORT_FILE.exists();
    }

    private static void copyFile(File src, File dst) throws IOException {
        try (FileChannel inChannel = new FileInputStream(src).getChannel();
             FileChannel outChannel = new FileOutputStream(dst).getChannel()) {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        }
    }

    private static boolean SdIsPresent() {
        return !Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public void exportStudentsProfile(byte[] bytes, String name) {
        if (!SDCARD_DIRECTORY.exists()) {
            SDCARD_DIRECTORY.mkdir();
        }
        File file = new File(SDCARD_DIRECTORY, name);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            try {
                fOut.write(bytes);
            } catch (IOException ignored) {
            }
        } catch (FileNotFoundException ignored) {
        }
    }
}
