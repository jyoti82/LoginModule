package com.jyoti.loginmodule.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;

import com.fasterxml.jackson.databind.JsonNode;
import com.jyoti.loginmodule.services.LogService;



public class Utils {
	private static final String logTag = "Utils";
	public static void showAlert(Activity context, String message){
		
		if (context == null || context.isFinishing()) {
			return;
		}
		try {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

			alertDialog.setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			   public void onClick(DialogInterface dialog, int which) {
			     
			   }
			});
			 
			alertDialog.show();
			}
			catch(Exception e)
			{
			    LogService.d(logTag, e.toString());
			}
		/*
		FragmentTransaction ft = context.getFragmentManager().beginTransaction();
		Fragment oldFrag = context.getFragmentManager().findFragmentByTag(alertTag);			
		if (oldFrag != null) {
			ft.remove(oldFrag);
		}
		AlertFragment newFragment = AlertFragment.newInstance(message);
		ft.add(android.R.id.content, newFragment, alertTag);
		ft.commitAllowingStateLoss();*/
	}
	
	// Post a Runnable to UI Thread
		public static void postToUIThread(Runnable r) {
			new Handler(Looper.getMainLooper()).post(r);
		}

		public static boolean isNullOrEmpty(JsonNode node) {
			// TODO Auto-generated method stub
			boolean result = false;

			if (node == null || node.isNull()) {
				result = true;
			}
			else if (node.isObject()) {
				// for {}
				if (node.size() == 0) {
					result = true;
				}
			}
			return result;
			
		}
}
