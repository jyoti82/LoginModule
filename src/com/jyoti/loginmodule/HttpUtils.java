package com.jyoti.loginmodule;

import java.io.IOException;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;

import com.jyoti.loginmodule.requests.BaseRequest;
import com.jyoti.loginmodule.services.LogService;

public class HttpUtils {

	
	public static HttpClient getHttpClient() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);

			//SSLSocketFactory sf = new CustomSSLSocketFactory(trustStore);
			//sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			//registry.register(new Scheme("https", sf, 443));

			HttpParams params = new BasicHttpParams();
			ClientConnectionManager cManager = new ThreadSafeClientConnManager(params, registry);
			
			// Timeout Parameters
			HttpConnectionParams.setConnectionTimeout(params, Constants.TIMEOUT_IN_SECONDS * 1000);
			HttpConnectionParams.setSoTimeout(params, Constants.TIMEOUT_IN_SECONDS * 1000);
			
			return new DefaultHttpClient(cManager, params);
		} catch (Exception e) {
			e.printStackTrace();
			return new DefaultHttpClient();
		}
	}

	public static HttpResponse get(String urlStr, String paramString, HashMap<String, String> headers) throws ClientProtocolException, IOException {
		HttpClient httpclient = getHttpClient();
		if(paramString != null) {
			urlStr = addQueryParamsToURL(urlStr, paramString);
		}
		HttpGet httpget = new HttpGet(urlStr);
		if (headers!=null) {
			for(String key : headers.keySet()) {
				String value = headers.get(key);
				httpget.setHeader(key, value);
			}
		}
		HttpResponse response = httpclient.execute(httpget);
		return response;
	}

	public static HttpResponse post(String urlStr, String paramString, int paramsType, HashMap<String, String> headers) throws ClientProtocolException, IOException, JSONException {
		HttpClient httpclient = getHttpClient();
		HttpPost httppost = new HttpPost(urlStr);
		if(paramString != null) {
			HttpEntity entity;
			StringEntity s= new StringEntity(paramString);
			s.setContentEncoding((Header) new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			if(paramsType == BaseRequest.JSON_ENCODED) {
				httppost.setHeader("HTTP.CONTENT_TYPE","application/json");
			}
			entity = s;
			httppost.setEntity(entity);
		}
		if (headers!=null) {
			for(String key : headers.keySet()) {
				String value = headers.get(key);
				httppost.setHeader(key, value);
				LogService.d("paramString", key +" " + value);
				
			}
		}
		HttpResponse response = httpclient.execute(httppost);
		return response;
	}
	
	public static HttpResponse put(String urlStr, String paramString, int paramsType, HashMap<String, String> headers) throws ClientProtocolException, IOException {
		HttpClient httpclient = getHttpClient();
		HttpPut httpput = new HttpPut(urlStr);
		if(paramString != null) {
			HttpEntity entity = new StringEntity(paramString);
			if(paramsType == BaseRequest.JSON_ENCODED) {
				httpput.setHeader("Content-type","application/json");
			}
			httpput.setEntity(entity);
		}
		if (headers!=null) {
			for(String key : headers.keySet()) {
				String value = headers.get(key);
				httpput.setHeader(key, value);
			}
		}
		HttpResponse response = httpclient.execute(httpput);
		return response;
	}
	
	public static HttpResponse delete(String urlStr, HashMap<String, String> headers) throws ClientProtocolException, IOException {
		HttpClient httpclient = getHttpClient();
		HttpDelete httpdelete = new HttpDelete(urlStr);
		if (headers!=null) {
			for(String key : headers.keySet()) {
				String value = headers.get(key);
				httpdelete.setHeader(key, value);
			}
		}
		HttpResponse response = httpclient.execute(httpdelete);
		return response;
	}
	
	public static String getResponseBody(HttpResponse response, String urlStr, BaseRequest request) {
		HttpEntity entity = response.getEntity();
		String responseBody = new String();

		if (entity != null) {
			try {
				responseBody = IOUtils.toString(entity.getContent());
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(request.getType() == BaseRequest.GET) {
			LogService.d("REQUEST", "GET " + urlStr);
		} else if(request.getType() == BaseRequest.POST) {
			LogService.d("REQUEST", "POST " + urlStr + "\nParameters: " + request.getParameters() + "\nType: " + request.getParamsType());
		} else if(request.getType() == BaseRequest.DELETE) {
			LogService.d("REQUEST", "DELETE " + urlStr);	
		} else if(request.getType() == BaseRequest.PUT) {
			LogService.d("REQUEST", "PUT " + urlStr+ "\nParameters: " + request.getParameters() + "\nType: " + request.getParamsType());
		}
		LogService.d("REQUEST", "REPLY: " + responseBody);
		return responseBody;
	}
	
	public static HashMap<String, String> getHeaders(HttpResponse response) {
		Header[] headers = response.getAllHeaders();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		for(Header header : headers) {
			headerMap.put(header.getName(), header.getValue());
		}
		return headerMap;
	}
	public static HashMap<String, String> setHeaders(Map<String, List<String>> receivedHeaders) {
		HashMap<String, String> recdHeaderMap = new HashMap <String, String>();
		for(String key : receivedHeaders.keySet()) {
			recdHeaderMap.put(key, receivedHeaders.get(key).toString());
		}
		return recdHeaderMap;
	}
	public static String addQueryParamsToURL(String urlStr, String paramString) {
		if(paramString != null) {
			if (urlStr.contains("?")) {
				urlStr += "&" + paramString;
			}
			else {
				urlStr += "?" + paramString;	
			}
		}
		LogService.d("Parameters", urlStr);
		return urlStr;
	}

}
