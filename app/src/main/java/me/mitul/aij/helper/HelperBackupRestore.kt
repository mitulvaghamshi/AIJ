package me.mitul.aij.helper

import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class HelperBackupRestore {
    private val SDCARD_DIRECTORY = File(Environment.getExternalStorageDirectory(), ".aij")
    fun exportStudentsProfile(bytes: ByteArray?, name: String?) {
        if (!SDCARD_DIRECTORY.exists()) {
            SDCARD_DIRECTORY.mkdir()
        }
        val file = File(SDCARD_DIRECTORY, name)
        try {
            val fOut = FileOutputStream(file)
            try {
                fOut.write(bytes)
            } catch (ignored: IOException) {
            }
        } catch (ignored: FileNotFoundException) {
        }
    }

    companion object {
        private val DATABASE_DIRECTORY =
            File(Environment.getExternalStorageDirectory(), ".mady_db_")
        private val IMPORT_FILE = File(DATABASE_DIRECTORY, "AIJ_DB")
        private val DATA_DIRECTORY_DATABASE = File(
            Environment.getDataDirectory()
                .toString() + "/data/" + "me.mitul.aij" + "/databases/" + "AIJ_DB.s3db"
        )

        fun exportDb(): Boolean {
            if (SdIsPresent()) {
                return false
            }
            val filename = "AIJ_DB"
            val exportDir = DATABASE_DIRECTORY
            val file = File(exportDir, filename)
            if (!exportDir.exists()) {
                exportDir.mkdirs()
            }
            return try {
                file.createNewFile()
                copyFile(DATA_DIRECTORY_DATABASE, file)
                true
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        }

        fun restoreDb() {
            if (SdIsPresent()) {
                return
            }
            val exportFile = DATA_DIRECTORY_DATABASE
            if (!sdDatabaseExists()) {
                return
            }
            try {
                exportFile.createNewFile()
                copyFile(IMPORT_FILE, exportFile)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        fun sdDatabaseExists(): Boolean {
            return IMPORT_FILE.exists()
        }

        @Throws(IOException::class)
        private fun copyFile(src: File, dst: File) {
            FileInputStream(src).channel.use { inChannel ->
                FileOutputStream(dst).channel.use { outChannel ->
                    inChannel.transferTo(
                        0,
                        inChannel.size(),
                        outChannel
                    )
                }
            }
        }

        private fun SdIsPresent(): Boolean {
            return Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED
        }
    }
}
