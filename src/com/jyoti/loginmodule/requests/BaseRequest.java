package com.jyoti.loginmodule.requests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.JsonNode;
import com.jyoti.loginmodule.Constants;
import com.jyoti.loginmodule.handlers.ResponseHandler;


public class BaseRequest {

	public static final int GET = 0;
	public static final int POST = 1;
	public static final int PUT = 2;
	public static final int DELETE = 3;
	public static final int URL_ENCODED = 0;
	public static final int JSON_ENCODED = 1;
	
	private int type;
	private String urlStr;
	private int paramstype;
	private String paramString;
	private int requestType;
	private ResponseHandler handler;
	private int sequenceNumber;
	private boolean cacheable;
	private boolean refetch;
	private int cacheExpiryHours = Constants.DEFAULT_CACHE_EXPIRY_HOURS;
	private HashMap<String, String> headerMap= new HashMap<String, String>();
	private Object userData;
	
	public BaseRequest(ResponseHandler rhandler, int requestHTTPType, int requestIdType) {
		setHandler(rhandler);
		setType(requestHTTPType);
		setRequestType(requestIdType);
		setParamsType(URL_ENCODED);
		setCacheable(true);
		setRefetch(false);
	}

	public String getUrlStr() {
		return urlStr;
	}

	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public ResponseHandler getHandler() {
		return handler;
	}

	public void setHandler(ResponseHandler handler) {
		this.handler = handler;
	}

	public byte[] getBytes() {
		String urlBytesStr = urlStr;
		if(paramString != null) {
			urlBytesStr += "?" + paramString;
		}
		return urlBytesStr.getBytes();
	}

	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	public int getSN() {
		return sequenceNumber;
	}

	public void setSN(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public int getParamsType() {
		return paramstype;
	}

	public void setParamsType(int paramstype) {
		this.paramstype = paramstype;
	}

	public boolean getCacheable() {
		return cacheable;
	}

	public void setCacheable(boolean cacheable) {
		this.cacheable = cacheable;
	}

	public boolean isRefetch() {
		return refetch;
	}

	public void setRefetch(boolean refetch) {
		this.refetch = refetch;
	}
	
	public void setParameters(JsonNode jsonData) {
		paramString = jsonData.toString();
	}
	
	public void setParameters(List<NameValuePair> parameters) {
		paramString = URLEncodedUtils.format(parameters, "UTF-8");
	}
	
	public void addParameter(String key, int value) {
		if(getType() == JSON_ENCODED) {
			if((paramString == null)||(paramString.isEmpty())) {
				paramString =  "{\"" + key + "\":" + value + "}"; 
			} else {
				String paramJson = paramString.substring(paramString.indexOf("{") + 1, paramString.lastIndexOf("}"));
				if(!paramJson.isEmpty()) {
					paramJson = "{" + paramJson + ",\"" + key + "\":" + value + "}";
				}
				paramString = paramJson;
			}
			return;
		}
		if(paramString == null) {
			paramString = URLEncodedUtils.format(Arrays.asList(new BasicNameValuePair(key, String.valueOf(value))), "UTF-8");
		} else {
			paramString += "&" + URLEncodedUtils.format(Arrays.asList(new BasicNameValuePair(key, String.valueOf(value))), "UTF-8");
		}
	}
	
	public void addParameter(String key, String value) {
		if(getParamsType() == JSON_ENCODED) {
			if((paramString == null)||(paramString.isEmpty())) {
				paramString =  "{\"" + key + "\":\"" + value + "\"}"; 
			} else {
				String paramJson = paramString.substring(paramString.indexOf("{") + 1, paramString.lastIndexOf("}"));
				if(!paramJson.isEmpty()) {
					paramJson = "{" + paramJson + ",\"" + key + "\":\"" + value + "\"}";
				}
				paramString = paramJson;
			}
			return;
		}
		if(paramString == null) {
			paramString = URLEncodedUtils.format(Arrays.asList(new BasicNameValuePair(key, value)), "UTF-8");
		} else {
			paramString += "&" + URLEncodedUtils.format(Arrays.asList(new BasicNameValuePair(key, value)), "UTF-8");
		}
	}
	
	public String getParameters() {
		return paramString;
	}

	public void setHeader(String key, String value) {
		if(!(value.isEmpty() || value==null) && !(key.isEmpty() || key==null))
		 {
			headerMap.put(key, value);
		}
	}
	
	public HashMap<String, String> getHeaders(){
		return headerMap;
	}
	
	public int getCacheExpiryInHours() {
		return cacheExpiryHours;
	}

	public void setCacheExpiryInHours(int cacheExpiryHours) {
		this.cacheExpiryHours = cacheExpiryHours;
	}

	public Object getUserData() {
		return userData;
	}

	public void setUserData(Object userData) {
		this.userData = userData;
	}

}
