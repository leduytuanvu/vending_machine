package com.leduytuanvu.vendingmachine.core.datasource

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.xml.sax.ErrorHandler
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

@SuppressLint("SdCardPath")
class StorageDataSource @Inject constructor() {
    val pathFileAds = "/sdcard/AVF/ADS/"
    val pathFileAdsPhone = "/storage/emulated/0/Documents/ADS/"
    val pathFileProduct = "/sdcard/avfdata/product/"


    fun checkFile(filePath: String): Boolean {
        val file = File(filePath)
        return file.exists() && file.isDirectory
    }

    fun createFile(filePath: String): Boolean {
        return if (!checkFile(filePath)) {
            File(filePath).mkdirs()
        } else {
            false
        }
    }

    fun saveData(jsonData: String, filePath: String) {
        val file = File(filePath)
        try {
            file.parentFile?.mkdirs()
            file.createNewFile()
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(jsonData.toByteArray())
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readData(filePath: String): String {
        var jsonData = ""
        try {
            val file = File(filePath)
            val fileInputStream = FileInputStream(file)
            jsonData = fileInputStream.readBytes().toString(Charsets.UTF_8)
            fileInputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return jsonData
    }

    fun getListVideoAds() : ArrayList<String> {
        val listPath = ArrayList<String>()
        val directory = File(pathFileAds)
        val files: Array<File>? = directory.listFiles()
        files?.forEach { file ->
            listPath.add(file.absolutePath)
            Log.d("FileList", "File: ${file.absolutePath}")
        }
        return listPath
    }

    fun saveVideoAds(context: Context, rawResId: Int, fileName: String) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
//            ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                context as Activity,
//                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                1
//            )
//            return
//        }
        val dir = File(pathFileAds)
        if (!dir.exists()) dir.mkdirs()
        val file = File(pathFileAds, fileName)
        try {
            val inputStream: InputStream = context.resources.openRawResource(rawResId)
            val outputStream: OutputStream = FileOutputStream(file)
            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } > 0) {
                outputStream.write(buffer, 0, length)
            }
            outputStream.flush()
            outputStream.close()
            inputStream.close()
            Log.d("SaveVideo", "Video Saved at ${file.absolutePath}")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("SaveVideo", "Error: ${e.message}")
        }
    }

    fun getListVideoAdsPhone() : ArrayList<String> {
        val listPath = ArrayList<String>()
        val directory = File(pathFileAdsPhone)
        val files: Array<File>? = directory.listFiles()
        files?.forEach { file ->
            listPath.add(file.absolutePath)
            Log.d("FileList", "File: ${file.absolutePath}")
        }
        return listPath
    }

    fun saveVideoAdsPhone(context: Context, rawResId: Int, fileName: String) {
        try {
            val dir = File(pathFileAdsPhone)
            Log.d("SaveVideo", "Video Saved at $pathFileAdsPhone")
            if (!dir.exists()) dir.mkdirs()
            val file = File(pathFileAdsPhone, fileName)

            val inputStream: InputStream = context.resources.openRawResource(rawResId)
            val outputStream: OutputStream = FileOutputStream(file)
            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } > 0) {
                outputStream.write(buffer, 0, length)
            }
            outputStream.flush()
            outputStream.close()
            inputStream.close()
            Log.d("SaveVideo", "Video Saved at ${file.absolutePath}")
        } catch (e: Exception) {
            Log.d("SaveVideo", "Video Saved at ${e.message}")
        }
    }
}