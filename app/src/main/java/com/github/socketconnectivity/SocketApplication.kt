package com.github.socketconnectivity

import android.app.Application
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class SocketApplication : Application() {
    private var mSocket: Socket? = null

    fun getSocket(localUrl: String): Socket? {
        try {
            mSocket = IO.socket(localUrl)
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }

        return mSocket
    }
}