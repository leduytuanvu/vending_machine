package com.leduytuanvu.vendingmachine.core.datasource.storageDataSource

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

@SuppressLint("SdCardPath")
class StorageDataSource @Inject constructor() {
    val pathFileAds = "/sdcard/vending_machine/ads/"

    val pathFileJsonSetUpVendingMachinePort = "/sdcard/vending_machine_data/settings/set_up_system/set_up_port/vending_machine_port.json"
    val pathFileJsonSetUpCashBoxPort = "/sdcard/vending_machine_data/settings/set_up_system/set_up_port/cash_box_port.json"
    val pathFileJsonSetUpMachineType = "/sdcard/vending_machine_data/settings/set_up_system/set_up_vending_machine/set_up_vending_machine_type.json"
    val pathFileJsonSetUpCashBoxType = "/sdcard/vending_machine_data/settings/set_up_system/set_up_cash_box/set_up_cash_box_type.json"
    val pathFileJsonListOfProductForSale = "/sdcard/vending_machine_data/products/list_product_for_sale.json"
    val pathFileJsonListOfProductGetFromServer = "/sdcard/vending_machine_data/products/list_product_get_from_server.json"

    fun folderIsExist(folderPath: String): Boolean {
        val folder = File(folderPath)
        return folder.exists() && folder.isDirectory
    }

    fun fileIsExist(filePath: String): Boolean {
        val file = File(filePath)
        return file.exists()
    }

    fun createFileIsSuccess(filePath: String): Boolean {
        return if (!fileIsExist(filePath)) {
            File(filePath).mkdirs()
        } else {
            false
        }
    }

    fun saveJsonToStorage(jsonData: String, filePath: String) {
        val file = File(filePath)
        file.parentFile?.mkdirs()
        file.createNewFile()
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(jsonData.toByteArray())
        fileOutputStream.close()
    }

    fun readJsonFromStorage(filePath: String): String {
        val file = File(filePath)
        val fileInputStream = FileInputStream(file)
        val jsonData = fileInputStream.readBytes().toString(Charsets.UTF_8)
        fileInputStream.close()
        return jsonData
    }

    // Get list video ads from storage
    fun getListVideoAdsFromStorage() : ArrayList<String> {
        val listPath = ArrayList<String>()
        val directory = File(pathFileAds)
        val files: Array<File>? = directory.listFiles()
        files?.forEach { file ->
            listPath.add(file.absolutePath)
            Log.d("FileList", "File: ${file.absolutePath}")
        }
        return listPath
    }

    // Save video ads from asset to storage
    fun saveVideoAdsFromAssetToStorage(context: Context, rawResId: Int, fileName: String) {
        val dir = File(pathFileAds)
        if (!dir.exists()) dir.mkdirs()
        val file = File(pathFileAds, fileName)
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
    }
}