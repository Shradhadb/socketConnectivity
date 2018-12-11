package com.github.socketconnectivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.socketconnectivity.utils.Constants
import com.github.socketconnectivity.utils.Utility
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mSocket: Socket? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!Utility.isNetworkAvailable(applicationContext)) {

            Utility.displaySnackBar(parent_layout, getString(R.string.check_internet))
        } else {
            connectSocket()
        }
    }

    private fun connectSocket() {
        // establish socket connectivity here, use Application class
        val app = application as SocketApplication
        mSocket = app.getSocket(Constants.SOCKET_URL)
        mSocket!!.on(Socket.EVENT_CONNECT, onConnect)
        mSocket!!.on(Socket.EVENT_DISCONNECT, onDisconnect)
        mSocket!!.on(Socket.EVENT_CONNECT_ERROR, onConnectError)

        //  on the event to receive message from socket
        mSocket!!.on("YOUR_KEY", onReceiveMsg)
        mSocket!!.connect()

    }


    // listener to get message
    private val onReceiveMsg = Emitter.Listener { args ->
        runOnUiThread {
            // Perform UI related work here
            var msg = args[0].toString()
            txt_msg.text = msg
        }
    }

    private val onConnect = Emitter.Listener {
        Utility.displaySnackBar(parent_layout, getString(R.string.connection_msg))
        // "emit" to send message to socket
        mSocket!!.emit("YOUR_KEY", "message")
    }

    private val onDisconnect = Emitter.Listener {

    }
    private val onConnectError = Emitter.Listener {

    }

    override fun onBackPressed() {
        disconnectSocket()
        super.onBackPressed()
    }


    // off calling event and disconnect socket before leaving the activity
    private fun disconnectSocket() {
        mSocket!!.off("YOUR_KEY")
        mSocket!!.disconnect()

    }


}
