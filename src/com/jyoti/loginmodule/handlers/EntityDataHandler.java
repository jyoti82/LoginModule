package com.jyoti.loginmodule.handlers;

import com.jyoti.loginmodule.response.BaseResponse;

public interface EntityDataHandler<T> {
	public void setEntity(T entity, Object userData);
	public void onError(BaseResponse result);
}
