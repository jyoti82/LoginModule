package com.jyoti.loginmodule.requests;

import java.io.UnsupportedEncodingException;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jyoti.loginmodule.Constants;
import com.jyoti.loginmodule.MainApplication;
import com.jyoti.loginmodule.handlers.ResponseHandler;
import com.jyoti.loginmodule.models.AuthenticatedUserInfo;
import com.jyoti.loginmodule.services.LogService;
import com.jyoti.loginmodule.services.RequestService;


public class UserLogInRequest extends BaseRequest {

	public UserLogInRequest(ResponseHandler rhandler,AuthenticatedUserInfo authUserInfo)
		{
		super(rhandler, POST, 1);
		setParamsType(JSON_ENCODED);
		setCacheable(false);
		//String urlStr = RequestService.getHost(false) + "/login";
		
		String urlStr = "http://10.0.2.2:8084/RestDemo/login/dologin";
		LogService.d("urlStr", urlStr);
		setUrlStr(urlStr);
		JsonNodeFactory nodeFactory = JsonNodeFactory.instance;
		ObjectNode node = nodeFactory.objectNode();
		String userName;
		try {
			userName = new String(authUserInfo.getUserCredential().getUserName().getBytes("UTF-8"), "ISO-8859-1");
			if (userName != null) node.put("UserName", userName);
			String password = new String(authUserInfo.getUserCredential().getPassword().getBytes("UTF-8"), "ISO-8859-1");
			if (password != null) node.put("Password", password);
			node.put("ClientUID", MainApplication.getUniqueAndroidDeviceId());
			//node.put("MsoName", authUserInfo.getMsoInfo().getMsoNameForServer());
			//node.put("AppName", Constants.LogOnAppName);
			setParameters(node);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated constructor stub
	}

}
