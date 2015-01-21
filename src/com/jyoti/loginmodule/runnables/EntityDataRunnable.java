package com.jyoti.loginmodule.runnables;

import java.lang.ref.WeakReference;

import com.jyoti.loginmodule.handlers.EntityDataHandler;
import com.jyoti.loginmodule.response.BaseResponse;

public class EntityDataRunnable<T> implements Runnable {
	
	private WeakReference<EntityDataHandler<T>> handlerObj;
	private T entityObj;
	private BaseResponse result;
	private Object userData;
	
	public EntityDataRunnable(WeakReference<EntityDataHandler<T>> c, T entity, Object userdata) {
		handlerObj = c;
		entityObj = entity;
		userData = userdata;
	}

	public EntityDataRunnable(WeakReference<EntityDataHandler<T>> c, BaseResponse result) {
		handlerObj = c;
		this.result = result;
		this.userData = this.result.getRequest().getUserData();
	}
	
	@Override
	public void run() {
		EntityDataHandler<T> actualObj = handlerObj.get();
		if (actualObj != null) {
			if (entityObj != null) {
				actualObj.setEntity(entityObj, userData);
			}
			else {
				actualObj.onError(result);
			}
		}
	}

}
