package com.jyoti.loginmodule;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jyoti.loginmodule.services.LogService;

public class Next extends Activity {
	public boolean isInFront = false;
	
	public Next(){
		super();
		//super(R.string.left_and_right);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		//getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		setContentView(R.layout.activity_next);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.next, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// Get extra data included in the Intent
			String id = intent.getStringExtra("id");
			int type = intent.getIntExtra("type", -1);

			boolean wasAdded = intent.getBooleanExtra("wasAdded", true);
			boolean callSucceeded = intent.getBooleanExtra("callSucceeded", true);
			LogService.d("FAVORITE", "HomeActivity received message in broadcast receiver. Type:" + type + " Callsucceeded: " + callSucceeded + " wasAdded:" + wasAdded + " id:" + id);
			//refreshOptionsView();
		}
	};
	
	@Override
	protected void onResume() {
		getIntent().putExtra("activityOnTop", 0); // Setting home's activity on top intent extra (only activity which can start without clicking on the hamburger)
		super.onResume();
		//LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter(MyFavoritesService.favUpdateEvent));
		//refreshOptionsView();
		isInFront = true;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		//slider.startShow();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		//LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
		isInFront = false;
		//slider.stopShow();
	}
	
}
