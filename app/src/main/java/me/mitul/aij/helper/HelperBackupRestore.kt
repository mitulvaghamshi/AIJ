package me.mitul.aij.helper

import android.os.Environment
import me.mitul.aij.utils.Constants
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class HelperBackupRestore {
    private val SD_DIR =
        File(Environment.getExternalStorageDirectory(), Constants.DB_NAME + ".bak")

    fun exportStudentsProfile(bytes: ByteArray?, name: String?) {
        if (!SD_DIR.exists()) SD_DIR.mkdir()
        val file = name?.let { File(SD_DIR, it) }
        try {
            try {
                FileOutputStream(file).write(bytes)
            } catch (ignored: IOException) {
            }
        } catch (ignored: FileNotFoundException) {
        }
    }

    companion object {
        private val sdPresent = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        private val DB_DIR =
            File(Environment.getExternalStorageDirectory(), Constants.DB_NAME + ".bak")
        private val IMPORT_FILE = File(DB_DIR, Constants.DB_NAME)
        private val DATA_DIR_DB =
            File("${Environment.getDataDirectory()}/${Constants.DB_PATH}/${Constants.DB_NAME}")
        val isDbExists = IMPORT_FILE.exists()

        @JvmStatic
        fun exportDb() {
            if (!sdPresent) return
            val filename = "AIJ_DB"
            val exportDir = DB_DIR
            val file = File(exportDir, filename)
            if (!exportDir.exists()) exportDir.mkdirs()
            try {
                file.createNewFile()
                copyFile(DATA_DIR_DB, file)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        fun restoreDb() {
            if (!sdPresent || !isDbExists) return
            val exportFile = DATA_DIR_DB
            try {
                exportFile.createNewFile()
                copyFile(IMPORT_FILE, exportFile)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        @Throws(IOException::class)
        private fun copyFile(src: File, dst: File) {
            FileInputStream(src).channel.use { inChannel ->
                FileOutputStream(dst).channel.use { outChannel ->
                    inChannel.transferTo(0, inChannel.size(), outChannel)
                }
            }
        }
    }
}
