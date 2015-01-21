package com.jyoti.loginmodule.handlers;

import com.jyoti.loginmodule.response.BaseResponse;



public interface ResponseHandler {

	public void onComplete(BaseResponse result);
	public void onError(BaseResponse result);
}
