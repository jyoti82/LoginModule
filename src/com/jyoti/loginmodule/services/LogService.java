package com.jyoti.loginmodule.services;

import com.jyoti.loginmodule.Constants;

import android.util.Log;



public class LogService {

	public final static int LOG_LEVEL_VERBOSE = 0;
	public final static int LOG_LEVEL_DEBUG = 1;
	public final static int LOG_LEVEL_INFO = 2;
	public final static int LOG_LEVEL_WARN = 3;
	public final static int LOG_LEVEL_ERROR = 4;
	public final static int LOG_LEVEL_DISABLED = 5;
	
	private static int logLevel = Constants.applicationConfig.getLogLevel();
	
	public static void setLevel(int level) {
		logLevel = level;
	}
	
	// Always log this type of message
	public static void a(String tag, String message) {
		Log.v(tag, message);
	}
	
	public static void v(String tag, String message) {
		if(logLevel > LOG_LEVEL_VERBOSE) return;
		Log.v(tag, message);
	}
	
	public static void d(String tag, String message) {
		if(logLevel > LOG_LEVEL_DEBUG) return;
		Log.d(tag, message);
	}
	
	public static void i(String tag, String message) {
		if(logLevel > LOG_LEVEL_INFO) return;
		Log.i(tag, message);
	}
	
	public static void w(String tag, String message) {
		if(logLevel > LOG_LEVEL_WARN) return;
		Log.w(tag, message);
	}
	
	public static void e(String tag, String message) {
		if(logLevel > LOG_LEVEL_ERROR) return;
		Log.e(tag, message);
	}


}
