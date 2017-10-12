package utils;

import android.content.Context;
import android.net.ConnectivityManager;

public final class NetworkUtils {

	private NetworkUtils() {}

	public static boolean isActiveNetwork(Context cotext) {
		ConnectivityManager connectivityManager = (ConnectivityManager)cotext.getSystemService(Context.CONNECTIVITY_SERVICE); 
		if(connectivityManager.getActiveNetworkInfo() != null)
			return connectivityManager.getActiveNetworkInfo().isAvailable();
		return false;
	}

	public static boolean isMobile(Context cotext) {
		return ((ConnectivityManager)cotext.getSystemService(Context.CONNECTIVITY_SERVICE)).getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
	}

	public static boolean isWifi(Context cotext) {
		return ((ConnectivityManager)cotext.getSystemService(Context.CONNECTIVITY_SERVICE)).getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
	}

}
