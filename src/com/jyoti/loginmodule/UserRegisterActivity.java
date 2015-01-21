package com.jyoti.loginmodule;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.jyoti.loginmodule.handlers.EntityDataHandler;
import com.jyoti.loginmodule.handlers.ItemDataHandler;
import com.jyoti.loginmodule.handlers.UniqueIDCheckHandler;
import com.jyoti.loginmodule.requests.UniqueIDRequest;
import com.jyoti.loginmodule.response.BaseResponse;
import com.jyoti.loginmodule.services.LogService;
import com.jyoti.loginmodule.utils.Utils;
import com.jyoti.loginmodule.utils.ValidationUtil;

public class UserRegisterActivity extends Activity implements ItemDataHandler,EntityDataHandler<Object>{
	private static final String logTag = "UserRegisterActivity";
	private EditText userName,password,confirmPassword,mobileNumber,emailId,address;
	private Button createAccount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_register);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		userName = (EditText) findViewById(R.id.register_username);
		password = (EditText) findViewById(R.id.register_password);
		confirmPassword =  (EditText) findViewById(R.id.register_retype_password);
		mobileNumber = (EditText) findViewById(R.id.register_mobile);
		emailId = (EditText) findViewById(R.id.register_email);
		address = (EditText) findViewById(R.id.register_address);
		createAccount = (Button)findViewById(R.id.register_create_account);
		
		confirmPassword.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				checkFields();
				return false;
			}
		});
		
		createAccount.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				createAccount.setPressed(true);
				checkFields();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.user_register, menu);
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

	
	
	
	public void checkFields() {
		LogService.d(logTag, "in checkFields");
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

		String usernameStr = userName.getText().toString();
		String emailidStr = emailId.getText().toString();
		String passwordStr = password.getText().toString();
		String confirmPasswordStr = confirmPassword.getText().toString();
		String mobile = mobileNumber.getText().toString();
		String add = address.getText().toString();
		if(ValidationUtil.emptyField(usernameStr) || ValidationUtil.usernameValidation(usernameStr)) {
			Utils.showAlert(UserRegisterActivity.this, getResources().getString(R.string.invalid_username));
		} else if(ValidationUtil.emptyField(emailidStr) || ValidationUtil.emailValidation(emailidStr)) {
			Utils.showAlert(UserRegisterActivity.this, getResources().getString(R.string.invalid_emailid));
		} else if(ValidationUtil.emptyField(mobile) || ValidationUtil.mobileValidation(mobile)){
			Utils.showAlert(UserRegisterActivity.this, getResources().getString(R.string.invalid_mobile));
		}else if (ValidationUtil.emptyField(add) ){
			Utils.showAlert(UserRegisterActivity.this, getResources().getString(R.string.address_empty));
		}else if(!passwordStr.equals(confirmPasswordStr)) {
			Utils.showAlert(UserRegisterActivity.this, getResources().getString(R.string.password_doesnt_match));
		} else if(ValidationUtil.emptyField(passwordStr) || ValidationUtil.passwordValidation(passwordStr)) {
			Utils.showAlert(UserRegisterActivity.this, getResources().getString(R.string.invalid_password));
		} else {
			checkForUniqueUsername();
		}
	}
	
	public void checkForUniqueUsername() {
		LogService.d(logTag, "in checkForUniqueUsername");
		//setLoaderVisibility(View.VISIBLE);
		UniqueIDCheckHandler usernameChecker = new UniqueIDCheckHandler(this, UniqueIDRequest.USERNAME);
		usernameChecker.execute(userName.getText().toString());
	}
	
	public void checkForUniqueEmail() {
		LogService.d(logTag, "in checkForUniqueEmail");
		//setLoaderVisibility(View.VISIBLE);
		UniqueIDCheckHandler emailChecker = new UniqueIDCheckHandler(this, UniqueIDRequest.EMAIL);
		emailChecker.execute(emailId.getText().toString());
	}

	@Override
	public void setEntity(Object entity, Object userData) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void setItem(Object item, Object userData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(BaseResponse result) {
		// TODO Auto-generated method stub
		
	}
}
