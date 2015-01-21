package com.jyoti.loginmodule.requests;

import com.jyoti.loginmodule.R;
import com.jyoti.loginmodule.handlers.ResponseHandler;

public class UniqueIDRequest extends BaseRequest{
	
	public final static int EMAIL = 0;
	public final static int USERNAME = 1;
	
	public UniqueIDRequest(ResponseHandler rhandler, int idType, String idValue) {
		super(rhandler, GET, R.integer.request_unique_id);
		setCacheable(false);

		String typeStr = "emailaddress";
		if(idType == USERNAME) {
			typeStr = "username";
		}
		setUrlStr("http://10.0.2.2/login/public/unique.php");
		addParameter("idtype", idValue);
		addParameter("idvalue", typeStr);
	}

}
