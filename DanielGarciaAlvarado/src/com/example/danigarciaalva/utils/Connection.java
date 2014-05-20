package com.example.danigarciaalva.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/*
 * Pequeña utilidad que nos permite saber si estamos conectados a Internet
 * 
 */
public class Connection {
	public static boolean isConnected(Context context){
		ConnectivityManager cm =
		        (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		return (activeNetwork != null ) && activeNetwork.isConnectedOrConnecting();
	}
}
