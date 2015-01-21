package com.jyoti.loginmodule;


import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.util.Log;

public class HTTPPoster
{
	public static HttpResponse doPost(String url, JSONObject c) throws ClientProtocolException, IOException 
	{
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		 Log.i("url", url);
		request.setHeader("json",c.toString());

	    HttpEntity entity;
	    StringEntity s = new StringEntity(c.toString());
	    Log.i("s", s.toString());
	    s.setContentEncoding((Header) new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	    entity = s;
	    request.setEntity(entity);
	    Log.i("entity", entity.toString());
	    HttpResponse response;
	    Log.i("request", request.toString());
	    response = httpclient.execute(request);
	    Log.i("response", response.toString());
	    return response;
	}
}