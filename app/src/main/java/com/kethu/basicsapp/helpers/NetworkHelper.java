package com.kethu.basicsapp.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by satya on 02-Dec-17.
 */

public class NetworkHelper {
    public static boolean isInternetConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo activeNetwork = null;

        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();

            return activeNetwork.isConnected();
        }

        return false;


    }

}
