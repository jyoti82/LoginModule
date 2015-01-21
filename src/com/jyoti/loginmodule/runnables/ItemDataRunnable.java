package com.jyoti.loginmodule.runnables;

import java.lang.ref.WeakReference;

import com.jyoti.loginmodule.handlers.ItemDataHandler;
import com.jyoti.loginmodule.response.BaseResponse;


public class ItemDataRunnable<T> implements Runnable{

	
	private WeakReference<ItemDataHandler<T>> handlerObj;
	private T itemObj;
	private BaseResponse result;
	private Object userData;
	
	public ItemDataRunnable(WeakReference<ItemDataHandler<T>> c, T item, Object userdata) {
		handlerObj = c;
		itemObj = item;
		userData = userdata;
	}

	public ItemDataRunnable(WeakReference<ItemDataHandler<T>> c, BaseResponse result) {
		handlerObj = c;
		this.result = result;
		this.userData = result.getRequest().getUserData();
	}
	
	@Override
	public void run() {
		ItemDataHandler<T> actualObj = handlerObj.get();
		if (actualObj != null) {
			if (itemObj != null) {
				actualObj.setItem(itemObj, userData);
			}
			else {
				actualObj.onError(result);
			}
		}
	}

}
