package com.example.w1d3_networkandthreadslab

import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Handler (
    mHand: android.os.Handler,
    ): Runnable {
    private val myHandler = mHand
    override fun run() {
        try {
            val myUrl = URL("https://users.metropolia.fi/~jarkkov/koe.txt")
            val myConn = myUrl.openConnection()
                    as HttpURLConnection
            val istream: InputStream =
                myConn.getInputStream()
            val allText = istream.bufferedReader().use {
                it.readText()
            }
            val result = StringBuilder()
            result.append(allText)
            val str = result.toString()

            val msg = myHandler.obtainMessage()
            msg.what = 0
            msg.obj = str
            myHandler.sendMessage(msg)
        } catch (e: Exception) {

        }
    }
}