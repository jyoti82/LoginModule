package com.jyoti.loginmodule;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.concurrent.ThreadPoolExecutor;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;




public class MainApplication extends Application {
	private static final String CONFIG_FILE = "Config.json";
	private static float density;
	private static long maxMemorySize;
	// LruCache accepts as integer so storing it as int. 
	private static int maxCacheSize;

	private static Context mContext;
	private static LinkedList<WeakReference<Activity>> activityStack = new LinkedList<WeakReference<Activity>>();
	
	@Override
	public void onCreate(){


		super.onCreate();
		mContext = this;

		density = getApplicationContext().getResources().getDisplayMetrics().density;
		maxMemorySize = Runtime.getRuntime().maxMemory();
		maxCacheSize = (int)(maxMemorySize / 8);

		
		// Copy fonts from assets to files directory for enabling its use in webview
		//Utils.copyFile(getApplicationContext(), "museosans_100-webfont.ttf");

		// Load all the Device specific constants
		//Constants.getDeviceSpecificValues();

		(((ThreadPoolExecutor)AsyncTask.THREAD_POOL_EXECUTOR)).setCorePoolSize(10);
		(((ThreadPoolExecutor)AsyncTask.THREAD_POOL_EXECUTOR)).prestartAllCoreThreads();
		Log.v("APPLICATION",
				"===============================================================" +
				//"\nBUILD: " + Constants.applicationConfig.getBuildType() + 
				"\nAPPLICATION BASE URL: " + Constants.applicationConfig.getDefaultBaseUrl() +
				//"\nADVERTISE BASE URL: " + Constants.applicationConfig.getDefaultBaseAdvertiseUrl() +
				"\n===============================================================");
	
	
	}
	public static ApplicationConfig getCustomConfiguration() {
		
			ApplicationConfig config = null;
			try {
				
				InputStream inputstream = MainApplication.getContext().getAssets().open(CONFIG_FILE);
				int size = inputstream.available();
				byte[] buffer = new byte[size];
				inputstream.read(buffer);
				inputstream.close();
				String contents = new String(buffer, "UTF-8");
				ObjectMapper mapper = new ObjectMapper();
				config = mapper.readValue(contents, new TypeReference<ApplicationConfig>() {});
			} catch (Exception e) {
				e.printStackTrace();
			}
			return config;
		

	}

	public static Context getContext() {
		return mContext;
	}
	public static String getUniqueAndroidDeviceId() {
		String uniqueId = "jyoti";
		/*
		// TODO Auto-generated method stub
		try
		{
			String androidId = Settings.Secure.getString(getContext().getContentResolver(),Settings.Secure.ANDROID_ID);
			String serialNo = android.os.Build.SERIAL;
			uniqueId = androidId + serialNo;
		}
		catch(Exception e){
			LogService.d("CLIENT_ID", "Client unique id not found");
			e.printStackTrace();
		}
		
		LogService.d("CLIENT_ID", "Client unique id is: " + uniqueId);
		*/
		return uniqueId;
	}

}
