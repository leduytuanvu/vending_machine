#include <jni.h>
#include <fcntl.h>
#include <termios.h>
#include <unistd.h>

extern "C"
JNIEXPORT jint JNICALL
Java_com_leduytuanvu_vendingmachine_core_datasource_PortConnectionDataSource_openPort(JNIEnv *env, jobject, jstring port) {
    const char *portName = env->GetStringUTFChars(port, nullptr);
    int fd = open(portName, O_RDWR | O_NOCTTY);
    env->ReleaseStringUTFChars(port, portName);
    return fd;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_leduytuanvu_vendingmachine_core_datasource_PortConnectionDataSource_closePort(JNIEnv *, jobject, jint fd) {
    close(fd);
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_leduytuanvu_vendingmachine_core_datasource_PortConnectionDataSource_readData(JNIEnv *env, jobject, jint fd, jbyteArray buffer, jint length) {
    jbyte *buf = env->GetByteArrayElements(buffer, nullptr);
    int bytesRead = read(fd, buf, length);
    env->ReleaseByteArrayElements(buffer, buf, 0);
    return bytesRead;
}
