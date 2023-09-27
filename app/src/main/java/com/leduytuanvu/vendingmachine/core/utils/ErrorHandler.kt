package com.leduytuanvu.vendingmachine.core.utils

import android.content.Context
import android.widget.Toast
import retrofit2.HttpException
import java.io.EOFException
import java.io.IOException
import java.net.ConnectException
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import javax.net.ssl.SSLHandshakeException

object ErrorHandler {
    fun handle(context: Context, throwable: Throwable) {
        when (throwable) {
            is HttpException -> {
                val statusCode = throwable.code()
                Toast.makeText(context, "HTTP error: $statusCode", Toast.LENGTH_SHORT).show()
            }
            is SocketTimeoutException -> {
                Toast.makeText(context, "Timeout", Toast.LENGTH_SHORT).show()
            }
            is UnknownHostException -> {
                Toast.makeText(context, "Unknown host", Toast.LENGTH_SHORT).show()
            }
            is SSLHandshakeException -> {
                Toast.makeText(context, "SSL Error: ${throwable.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
            is ConnectException -> {
                Toast.makeText(context, "Connection Error: ${throwable.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
            is MalformedURLException -> {
                Toast.makeText(context, "Malformed URL: ${throwable.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
            is ProtocolException -> {
                Toast.makeText(context, "Protocol Error: ${throwable.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
            is UnknownServiceException -> {
                Toast.makeText(context, "Unknown Service: ${throwable.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
            is EOFException -> {
                Toast.makeText(context, "End Of File: ${throwable.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
            is IOException -> {
                Toast.makeText(context, "IO Exception: ${throwable.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(context, "Unknown Error: ${throwable.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

