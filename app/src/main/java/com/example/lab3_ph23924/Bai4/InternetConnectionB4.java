package com.example.lab3_ph23924.Bai4;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

public class InternetConnectionB4 {

    public static boolean checkConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            // Lấy tất cả các mạng hiện tại
            Network[] networks = connectivityManager.getAllNetworks();

            for (Network network : networks) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);

                // Kiểm tra có mạng hiện tại nào có khả năng truy cập Internet không
                if (networkCapabilities != null &&
                        (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))) {
                    return true; // Có kết nối Internet
                }
            }
        }

        return false; // Không có kết nối Internet
    }
}
