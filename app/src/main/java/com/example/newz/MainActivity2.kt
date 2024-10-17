package com.example.newz

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val toggleWifiButton: Button = findViewById(R.id.toggleWifiButton)

        toggleWifiButton.setOnClickListener {
            if (!isInternetAvailable()) {
                toggleWiFi()
            } else {
                Toast.makeText(this, "Internet is currently active. Cannot toggle Wi-Fi.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun toggleWiFi() {
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiManager.isWifiEnabled = !wifiManager.isWifiEnabled
        Toast.makeText(this, "Wi-Fi toggled", Toast.LENGTH_SHORT).show()
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
