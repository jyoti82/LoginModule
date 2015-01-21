package com.jyoti.loginmodule;

import com.jyoti.loginmodule.handlers.ItemDataHandler;
import com.jyoti.loginmodule.handlers.UserLogInHandler;
import com.jyoti.loginmodule.models.AuthenticatedUserInfo;
import com.jyoti.loginmodule.models.UserCredential;
import com.jyoti.loginmodule.models.UserData;
import com.jyoti.loginmodule.response.BaseResponse;
import com.jyoti.loginmodule.services.LogService;
import com.jyoti.loginmodule.services.RequestService;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginAvtivity extends Activity implements ItemDataHandler
{
	private EditText usernameField,passwordField;
	private static final String logTag = "LoginAvtivity";

@Override 
protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.activity_login_avtivity);
   usernameField = (EditText)findViewById(R.id.editText1);
   passwordField = (EditText)findViewById(R.id.editText2);
  
 
}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
   // Inflate the menu; this adds items to the action bar if it is present.
  // getMenuInflater().inflate(R.menu.login_avtivity, menu);
   return true;
}

public void loginPost(View view){
   String username = usernameField.getText().toString();
   String password = passwordField.getText().toString();
   //new SigningIn(this).execute(username,password);
   
   UserCredential userCred = new UserCredential(username,password);
	AuthenticatedUserInfo authUserInfo = new AuthenticatedUserInfo(userCred);
	UserLogInHandler handler = new UserLogInHandler(LoginAvtivity.this, authUserInfo);
	RequestService.getUserLoggedIn(handler, authUserInfo);

}

public void onRegisterClick(View view){
	Intent intentHome=new Intent(LoginAvtivity.this, UserRegisterActivity.class);
	//intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
	startActivity(intentHome);
}
private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {

	@Override
	public void onReceive(Context context, Intent intent) {
		LocalBroadcastManager.getInstance(LoginAvtivity.this).unregisterReceiver(mMessageReceiver);
		if (!LoginAvtivity.this.isFinishing()) {
			//UserProfileService.loadHome(LoginActivity.this);
			Intent intentHome=new Intent(LoginAvtivity.this, Next.class);
			intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intentHome);
		}
	}		
};


@Override
public void setItem(Object item, Object userData) {
	LogService.d(logTag,"inside setITem method" );
	if(item instanceof UserData) {
		Intent intent=new Intent(this, CatLogActivity.class);
		 startActivity(intent);
		 //finish();
	}
	// TODO Auto-generated method stub
	
}
@Override
public void onError(BaseResponse result) {
	// TODO Auto-generated method stub
	
}

}
