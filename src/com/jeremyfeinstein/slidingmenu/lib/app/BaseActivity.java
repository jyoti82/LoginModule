package com.jeremyfeinstein.slidingmenu.lib.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jyoti.loginmodule.Constants;
import com.jyoti.loginmodule.MainApplication;
import com.jyoti.loginmodule.NetworkStateChecker;
import com.jyoti.loginmodule.R;

public class BaseActivity extends SlidingFragmentActivity  {
	
	private static float density;
	private View actionBarView;
	private RelativeLayout iconTitleContainer;
	private ImageView icon;
	private TextView titleTextView;
	private TextView rightTextView;
	private ImageView search;
	private String title;
	private Bundle actMetaData;
	private boolean isMetaDataFetched;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		density = getResources().getDisplayMetrics().density;
		Drawable selectedDrawable;
		ActionBar actionBar = getActionBar();
		
		// Activity has disabled ActionBar 
		if (actionBar != null) {
			actionBar.setBackgroundDrawable(null);
			
			Bundle metaData = getMetaData();
			if (metaData != null) {
				
				createTopBarLayout();
				boolean isTop = metaData.getBoolean("isTop");
				boolean showSearch = metaData.getBoolean("showSearch");
				if (showSearch) {
					search.setVisibility(View.VISIBLE);
				}
				int titleMarginLeft = 0;
				if (isTop) {
					// isTop is True, then add hamburger icon
					selectedDrawable = getDrawable(R.drawable.more_on);
					icon.setImageDrawable(selectedDrawable);
					titleMarginLeft = (int) getResources().getDimension(R.dimen.title_left_margin_isTop);
				}
				// add Back Icon
				else {
					selectedDrawable = getDrawable(R.drawable.back_on);
					icon.setImageDrawable(selectedDrawable);
					titleMarginLeft = (int) getResources().getDimension(R.dimen.title_left_margin_not_isTop);
					// Since this is Back Icon, add click listener here only.
					iconTitleContainer.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							onBackPressed();
						}
					});
				}
				int titleMarginTop = (int) getResources().getDimension(R.dimen.title_top_margin);
				((MarginLayoutParams)titleTextView.getLayoutParams()).leftMargin = titleMarginLeft;
				((MarginLayoutParams)titleTextView.getLayoutParams()).topMargin = titleMarginTop;
				actionBar.setCustomView(actionBarView);
				actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
			}
		}
	}
	
	protected void setCustomTitle(String customTitle) {
		title = customTitle;
		if (titleTextView != null && title != null) {
			titleTextView.setText(title.toUpperCase());
		}
	}
	
	private void createTopBarLayout() {
		Drawable selectedDrawable;
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		actionBarView = inflater.inflate(R.layout.top_action_bar_layout, null);
		search = (ImageView) actionBarView.findViewById(R.id.search_icon);
		selectedDrawable = getDrawable(R.drawable.search_on);
		icon.setImageDrawable(selectedDrawable);
		iconTitleContainer = (RelativeLayout) actionBarView.findViewById(R.id.iconTitleContainer);
		icon = (ImageView) actionBarView.findViewById(R.id.icon);
		titleTextView = (TextView) actionBarView.findViewById(R.id.topbar_title);
		//titleTextView.setTypeface(TypeFaceProvider.getTypeFace(this, TypeFaceProvider.MUSEOSANS_300));
		rightTextView = (TextView) actionBarView.findViewById(R.id.rightTextViewForDVR);
		//rightTextView.setTypeface(TypeFaceProvider.getTypeFace(this, TypeFaceProvider.MUSEOSANS_300));
	}
	
	public TextView getRightPlaceHolderView() {
		return rightTextView;
	}

	// Called only when isTop flag is true
	public void setHamburgerClickHandler(View.OnClickListener homeHandler) /*throws Exception*/ {
		boolean isTop = getIsTop();
		if (isTop) {
			iconTitleContainer.setOnClickListener(homeHandler);
		}
		else {
			//throw new Exception("Setting Hamburger click handler on a non-isTop Activity");
		}
	}
	
	public void setSearchClickHandler(View.OnClickListener searchHandler) /*throws Exception*/ {
		boolean isSearch = getIsSearch();
		if (isSearch) {
			search.setOnClickListener(searchHandler);
		}
		else {
			//throw new Exception("Setting Hamburger click handler on a non-isTop Activity");
		}
	}
	
	private int convertDpToPixels(int dpUnits) {
		return (int)(dpUnits * density + 0.5f);
	}
	
	private boolean getIsTop() {
		boolean result = false;
		Bundle metaData = getMetaData();
		if (metaData != null) {
			result = metaData.getBoolean("isTop", false);
		}
		return result;
	}
	
	private boolean getIsSearch() {
		boolean result = false;
		Bundle metaData = getMetaData();
		if (metaData != null) {
			result = metaData.getBoolean("showSearch", false);
		}
		return result;
	}
	
	private Bundle getMetaData() {
		if (!isMetaDataFetched) {
			ActivityInfo app;
			try {
				app = getPackageManager().getActivityInfo(this.getComponentName(), PackageManager.GET_ACTIVITIES|PackageManager.GET_META_DATA);
				actMetaData = app.metaData;
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
			isMetaDataFetched = true;
		}
		return actMetaData;
	}
	private BroadcastReceiver mNetworkStateChecker = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getExtras()!=null) {
				if(intent.getExtras().getBoolean(NetworkStateChecker.noNetworkExtra,Boolean.FALSE)) {
					//Utils.showAlert(BaseActivity.this, MainApplication.getContext().getResources().getString(R.string.internet_disconnected));
					try {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(BaseActivity.this);

						alertDialog.setTitle(R.string.internet_disconnected).setMessage(R.string.internet_not_available).setPositiveButton("OK", new DialogInterface.OnClickListener() {
						   public void onClick(DialogInterface dialog, int which) {
						     finish();
						   }
						});
						 
						alertDialog.show();
						}
						catch(Exception e)
						{
						    //Log.d(Constants.TAG, "Show Dialog: "+e.getMessage());
						}
				}
			}
		}

	};
	
	private BroadcastReceiver mHandleUserSessionRenewalFailure = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			//Utils.handleUserSessionRenewalError();
		}

	};
	
	@Override
	protected void onPause() {
		super.onPause();
		LocalBroadcastManager.getInstance(MainApplication.getContext()).unregisterReceiver(mNetworkStateChecker);
		LocalBroadcastManager.getInstance(MainApplication.getContext()).unregisterReceiver(mHandleUserSessionRenewalFailure);
	}

	@Override
	protected void onResume() {
		super.onResume();
		//LocalBroadcastManager.getInstance(MainApplication.getContext()).registerReceiver(mNetworkStateChecker,new IntentFilter(NetworkStateChecker.noNetworkAction));
		//LocalBroadcastManager.getInstance(MainApplication.getContext()).registerReceiver(mHandleUserSessionRenewalFailure,new IntentFilter(Constants.userSessionRenewalError));
		//if (!UserProfileService.isUserSessionUpdateSucceeded()) Utils.handleUserSessionRenewalError();
	}
}
