package com.jyoti.loginmodule.requests;

import com.jyoti.loginmodule.R;
import com.jyoti.loginmodule.handlers.ResponseHandler;


public class CatlogItemRequest extends BaseRequest { 
	public CatlogItemRequest(ResponseHandler rhandler) {
		super(rhandler, GET, R.integer.catlog_list);
		//setCacheable(true);
		//setUrlStr(RequestService.getBaseUrl(true, true) + "/Program/Recommended/ForYou/" + UserProfileService.getProviderId() + "/" + UserProfileService.getServiceId());
		String urlStr = "http://10.0.2.2:8084/RestDemo/catlog/items";
		setUrlStr(urlStr);
	}
}
