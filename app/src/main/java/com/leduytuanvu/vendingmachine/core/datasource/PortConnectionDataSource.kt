package com.leduytuanvu.vendingmachine.core.datasource

class PortConnectionDataSource {

    init {
        System.loadLibrary("native_port_communication")
    }

    private var fdPort1: Int = -1
    private var fdPort2: Int = -1

    private external fun openPort(portName: String): Int
    private external fun closePort(fd: Int)
    private external fun readData(fd: Int, buffer: ByteArray, length: Int): Int

    fun connectPorts() {
        fdPort1 = openPort("/dev/ttys3")
        fdPort2 = openPort("/dev/ttys4")
    }

    fun disconnectPorts() {
        closePort(fdPort1)
        closePort(fdPort2)
    }

    fun readFromPort1(buffer: ByteArray, length: Int): Int {
        return readData(fdPort1, buffer, length)
    }

    fun readFromPort2(buffer: ByteArray, length: Int): Int {
        return readData(fdPort2, buffer, length)
    }
}
