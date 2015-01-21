package com.jyoti.loginmodule.handlers;

import com.jyoti.loginmodule.response.BaseResponse;


public interface ItemDataHandler<T> {
	

	public void setItem(T item, Object userData);
	public void onError(BaseResponse result);

}
