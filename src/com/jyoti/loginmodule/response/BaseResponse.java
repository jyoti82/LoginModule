package com.jyoti.loginmodule.response;

import java.util.HashMap;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.jyoti.loginmodule.GatewayStatusCode;
import com.jyoti.loginmodule.requests.BaseRequest;
import com.jyoti.loginmodule.utils.Utils;


public class BaseResponse {

	
	private Integer status;
	private String message;
	private JsonNode data; // This is for all Json data response from server.
	private byte[] dataBytes; // byte data is for Images
	private BaseRequest request;
	private HashMap<String, String> header;
	private JSONObject dataArray; 
	public BaseResponse(Integer code, String message, JsonNode dataNode, BaseRequest requestObj) {
		setStatus(code);
		setMessage(message);
		setData(dataNode);
		setRequest(requestObj);
	}
	
	public BaseResponse(byte[] dataStr, BaseRequest requestObj) {
		setByteData(dataStr);
		setRequest(requestObj);
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		if (status == null) {
			this.status = GatewayStatusCode.Unknown;
		}
		else {
			this.status = status;
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JsonNode getData() {
		return data;
	}
	public void setData(JsonNode data) {
		if (Utils.isNullOrEmpty(data)) {
			data = null;
		}
		this.data = data;
	}
	/*
	public void setData(JsonNode data) {
		
		if (data == null || data.isNull()) {
				data = null;
		}
		else if (data.isObject()) {
			if (data.size() == 0) {
				this.data = data;
			}
		}
		
	}
	*/
	public byte[] getByteData() {
		return dataBytes;
	}
	
	public void setByteData(byte[] data) {
		this.dataBytes = data;
	}

	public BaseRequest getRequest() {
		return request;
	}

	public void setRequest(BaseRequest request) {
		this.request = request;
	}

	public HashMap<String, String> getHeaders() {
		return header;
	}

	public void setHeaders(HashMap<String, String> header) {
		this.header = header;
	}

	public void setJSONObject(JSONObject data2) {
		// TODO Auto-generated method stub
		this.dataArray = data2;
	}
	public JSONObject getJSONObject(){
		return this.dataArray;
	}
}
