package com.leduytuanvu.vendingmachine.core.common

object AppByteArrays {
    // TTYS3
    val ttys3TurnOnLight = byteArrayOf(0x00, 0xFF.toByte(), 0xDD.toByte(), 0x22, 0xAA.toByte(),0x55)
    val ttys3TurnOffLight = byteArrayOf(0x00, 0xFF.toByte(), 0xDD.toByte(), 0x22,0x55, 0xAA.toByte())



    // TTYS4
    val ttys4ResetCashBox = byteArrayOf(
        0x03,
        0x00,
        0x01,
        0x00,
        0x0A.toByte(),
        0x08
    )
}