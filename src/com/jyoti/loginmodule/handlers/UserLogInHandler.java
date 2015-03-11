package com.jyoti.loginmodule.handlers;

import java.lang.ref.WeakReference;

import org.json.JSONException;

import android.os.Handler;
import android.os.Looper;

import com.fasterxml.jackson.databind.JsonNode;
import com.jyoti.loginmodule.MainApplication;
import com.jyoti.loginmodule.R;
import com.jyoti.loginmodule.models.AuthenticatedUserInfo;
import com.jyoti.loginmodule.models.UserCredential;
import com.jyoti.loginmodule.models.UserData;
import com.jyoti.loginmodule.response.BaseResponse;
import com.jyoti.loginmodule.runnables.ItemDataRunnable;
import com.jyoti.loginmodule.services.LogService;




public class UserLogInHandler implements ResponseHandler{

	private WeakReference<ItemDataHandler<Object>> mContext;
	private AuthenticatedUserInfo mAuthUserInfo;

	public UserLogInHandler(ItemDataHandler<Object> context, AuthenticatedUserInfo authUserInfo)
	{
		mContext = new WeakReference<ItemDataHandler<Object>>(context);
		mAuthUserInfo = authUserInfo;
	}

	@Override
	public void onComplete(BaseResponse result) {
		// TODO Auto-generated method stub
		String email,userId,securityToken;
		
		
		
			/*
		JSONObject authentication = result.getJSONObject();
		JSONObject dataNode = authentication.getJSONObject("Authentication");

		if (dataNode == null) {
			result.setMessage("server error");
			postError(result);
			return;
		}
		
		boolean failed = dataNode.has("ErrorResponse");
		if (result.getStatus() != GatewayStatusCode.Success || result.getStatus() == null || failed) {
			result.setStatus(GatewayStatusCode.ServiceError);
			result.setMessage("server error");
			postError(result);
			return;
		}
		
		//JsonNode accountInfoNode = dataNode.findPath("Authentication");
		
		
			email = dataNode.getString("Email");
			userId = dataNode.getString("UserId");
			securityToken = dataNode.getString("SecurityToken");
			*/
			JsonNode dataNode = result.getData();

			if (dataNode == null) {
				result.setMessage(MainApplication.getContext().getResources().getString(R.string.server_error));
				postError(result);
				return;
			}

			boolean failed = dataNode.has("ErrorResponse");
			if (failed) {
				Integer status = dataNode.findPath("Code").asInt(-1);
				result.setStatus(status);
				result.setMessage(MainApplication.getContext().getResources().getString(R.string.server_error));
				postError(result);
				return;
			}
			JsonNode accountInfoNode = dataNode.findPath("Authentication");
			 email = accountInfoNode.get("Email").textValue();
			 userId = String.valueOf(accountInfoNode.get("UserId").intValue());
			 securityToken = accountInfoNode.get("SecurityToken").textValue();
			 
	    UserCredential userCredential = new UserCredential(mAuthUserInfo.getUserCredential().getUserName(), mAuthUserInfo.getUserCredential().getPassword(), email, userId);
		AuthenticatedUserInfo completeAuthInfo = new AuthenticatedUserInfo(userCredential);
		UserData userData = new UserData(completeAuthInfo, securityToken);
		
		LogService.d("email",email);
		LogService.d("userId",userId);
		LogService.d("securityToken",securityToken);
		new Handler(Looper.getMainLooper()).post(new ItemDataRunnable<Object>(mContext, userData, result.getRequest().getUserData()));
	
		
	}

	@Override
	public void onError(BaseResponse result) {
		// TODO Auto-generated method stub
		
		
	}
	private void postError(BaseResponse result) {
		LogService.d("errpr post error","eroor");
		new Handler(Looper.getMainLooper()).post(new ItemDataRunnable<Object>(mContext, result));
		//Utils.postToUIThread(new ItemDataRunnable<Object>(mContext, result));
	}
}
