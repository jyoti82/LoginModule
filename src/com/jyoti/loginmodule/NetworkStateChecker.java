package com.jyoti.loginmodule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.content.LocalBroadcastManager;

import com.jyoti.loginmodule.services.LogService;

public class NetworkStateChecker extends BroadcastReceiver {

	public static String noNetworkAction = "network_disconnected";
	public static String noNetworkExtra = "no_connectivity";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getExtras()!=null) {
			if(intent.getExtras().getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY,Boolean.FALSE)) {
				LogService.d("NETWORK", "There's no network connectivity, sending local broadcast");
				Intent localIntent = new Intent(noNetworkAction);
				localIntent.putExtra(noNetworkExtra, true);
				LocalBroadcastManager.getInstance(MainApplication.getContext()).sendBroadcast(localIntent);
			}
		}
	}

}
