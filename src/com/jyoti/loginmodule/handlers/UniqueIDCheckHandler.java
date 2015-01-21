package com.jyoti.loginmodule.handlers;

import java.lang.ref.WeakReference;

import com.fasterxml.jackson.databind.JsonNode;
import com.jyoti.loginmodule.GatewayStatusCode;
import com.jyoti.loginmodule.R;
import com.jyoti.loginmodule.response.BaseResponse;
import com.jyoti.loginmodule.runnables.EntityDataRunnable;
import com.jyoti.loginmodule.services.RequestService;
import com.jyoti.loginmodule.utils.Utils;

public class UniqueIDCheckHandler implements ResponseHandler{

	private WeakReference<EntityDataHandler<Object>> mContext;
	private int type;

	public UniqueIDCheckHandler(EntityDataHandler<Object> entityHandler, int ltype) {
		mContext = new WeakReference<EntityDataHandler<Object>>(entityHandler);
		type = ltype;
	}
	
	public void execute(String value) {
		RequestService.checkForUniqueId(this, type, value, type);
	}
	
	public class UniqueEntity {
		private boolean isSuccess;
		private int type;
		private boolean isUnique;
		
		public UniqueEntity(int ltype) {
			setSuccess(false);
			setType(ltype);
			setUnique(false);
		}
		
		public UniqueEntity(int ltype, boolean isunique) {
			setSuccess(true);
			setType(ltype);
			setUnique(isunique);
		}

		public boolean isSuccess() {
			return isSuccess;
		}

		public void setSuccess(boolean isSuccess) {
			this.isSuccess = isSuccess;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public boolean isUnique() {
			return isUnique;
		}

		public void setUnique(boolean isUnique) {
			this.isUnique = isUnique;
		}
	}

	@Override
	public void onComplete(BaseResponse result) {
		if(result.getStatus() == GatewayStatusCode.Success) {
			JsonNode rootNode = result.getData();
			try {
				boolean isUnique = rootNode.findPath("isUnique").asBoolean();
				UniqueEntity uEntity = new UniqueEntity(type, isUnique);
				Utils.postToUIThread(new EntityDataRunnable<Object>(mContext, uEntity, result.getRequest().getUserData()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {			
			postError(result);
		}
	}


	@Override
	public void onError(BaseResponse result) {
		int rtype = result.getRequest().getRequestType();
		if(rtype == R.integer.request_unique_id) {
			postError(result);
		}
	}

	private void postError(BaseResponse result) {
		// Using success callback (setEntity) for sending error message.
		UniqueEntity uEntity = new UniqueEntity(type);
		Utils.postToUIThread(new EntityDataRunnable<Object>(mContext, uEntity, result.getRequest().getUserData()));
	}
}
