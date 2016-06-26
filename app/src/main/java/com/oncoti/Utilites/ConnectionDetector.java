package com.oncoti.Utilites;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ConnectionDetector {

	private Context _context;
	private String connStatus = "notconnected";

	public ConnectionDetector(Context context) {
		this._context = context;
	}

	/**
	 * Checking for all possible internet providers
	 * **/
	public boolean isConnectingToInternet() {
		ConnectivityManager connectivity = (ConnectivityManager) _context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
					
						return true;
						
					}

		}
		return false;
	}

	public static boolean hasActiveInternetConnection(Context context) {

		try {
			HttpURLConnection urlc = (HttpURLConnection) (new URL(
					"http://www.google.com").openConnection());
			urlc.setRequestProperty("User-Agent", "Test");
			urlc.setRequestProperty("Connection", "close");
			urlc.setConnectTimeout(1500);
			urlc.connect();
			return (urlc.getResponseCode() == 200);
		} catch (IOException e) {
			Log.e("Connection Detector", "Error checking internet connection",
					e);
		}

		return false;
	}
}
