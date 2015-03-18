package com.jyoti.loginmodule.services;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyoti.loginmodule.Constants;
import com.jyoti.loginmodule.GatewayStatusCode;
import com.jyoti.loginmodule.HttpUtils;
import com.jyoti.loginmodule.handlers.CatlogResponseHandler;
import com.jyoti.loginmodule.handlers.ResponseHandler;
import com.jyoti.loginmodule.handlers.UserLogInHandler;
import com.jyoti.loginmodule.models.AuthenticatedUserInfo;
import com.jyoti.loginmodule.requests.BaseRequest;
import com.jyoti.loginmodule.requests.CatlogItemRequest;
import com.jyoti.loginmodule.requests.UniqueIDRequest;
import com.jyoti.loginmodule.requests.UserLogInRequest;
import com.jyoti.loginmodule.response.BaseResponse;



public class RequestService {
	private static String baseUrl = Constants.applicationConfig.getDefaultBaseUrl();
	private static NumberGenerator dCounter = new NumberGenerator(10000,50000);
	private static final String logTag = "REQUESTSERVICE";
	
	public static String getHost( boolean useSsl) {
		String protocol = "http://";
		
			return protocol+baseUrl;
		
	}
	public static void getUserLoggedIn(UserLogInHandler handler,
			AuthenticatedUserInfo authUserInfo) {
		// TODO Auto-generated method stub
		BaseRequest request = new UserLogInRequest(handler, authUserInfo);
		 getData(request);
		
	}
	public static int checkForUniqueId(ResponseHandler handler, int idType, String idValue, Object userData) {
		BaseRequest request = new UniqueIDRequest(handler, idType, idValue);
		request.setUserData(userData);
		return getData(request);
	}
	
	public static int getCatlogItems(ResponseHandler cHandler) {
		// TODO Auto-generated method stub
		BaseRequest request = new CatlogItemRequest(cHandler);
		return getData(request);
		
	}
	private static int getData(BaseRequest requestObject) {
		// TODO Auto-generated method stub
		int key = dCounter.nextInt();
		requestObject.setSN(key);
		FetchDataTask task = new FetchDataTask();
		task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestObject);
		return key;
	
	}
	
	

	private static class FetchDataTask extends AsyncTask<BaseRequest, Void, BaseResponse> {
		protected void onPostExecute(BaseResponse result) {
			LogService.d(logTag, "Complete triggered for request." + String.valueOf(result.getRequest().getSN()));
			
		}

		protected void onCancelled(BaseResponse result) {
		}

		@Override
		protected BaseResponse doInBackground(BaseRequest... requests) {
			LogService.d(logTag, "Process request " + String.valueOf(requests[0].getSN()));
			boolean fromCache = false;
			String md5 = null;
			String result = null;

			//if(requests[0].getCacheable()) md5 = Utils.md5(requests[0].getBytes());
			BaseResponse resultObj = new BaseResponse(null, null, null, requests[0]);
			LogService.d("request types", String.valueOf(requests[0].getType()));
			LogService.d("parameter string ", String.valueOf(requests[0].getParameters()));
			if(isCancelled()) return resultObj;

			
				
				if(requests != null) {
					HttpResponse response = null;
					try {
						if(requests[0].getType() == BaseRequest.GET) {
							response = HttpUtils.get(requests[0].getUrlStr(), requests[0].getParameters(), requests[0].getHeaders());
						} else if(requests[0].getType() == BaseRequest.POST) {
							response = HttpUtils.post(requests[0].getUrlStr(), requests[0].getParameters(), requests[0].getParamsType(), requests[0].getHeaders());
						} else if(requests[0].getType() == BaseRequest.DELETE) {
							response = HttpUtils.delete(requests[0].getUrlStr(), requests[0].getHeaders());
						} else if(requests[0].getType() == BaseRequest.PUT) {
							response = HttpUtils.put(requests[0].getUrlStr(), requests[0].getParameters(), requests[0].getParamsType(), requests[0].getHeaders());
						}
						result = HttpUtils.getResponseBody(response, requests[0].getUrlStr(), requests[0]);
						resultObj.setHeaders(HttpUtils.getHeaders(response));
					} catch(UnknownHostException e) {
						e.printStackTrace();
						resultObj.setMessage("server error");
					} catch (SocketTimeoutException e) {
						e.printStackTrace();
						resultObj.setMessage("request timed out error");
					} catch (ConnectionPoolTimeoutException e) {
						e.printStackTrace();
						resultObj.setMessage("request timed out");
					} catch (ConnectTimeoutException e) {
						e.printStackTrace();
						resultObj.setMessage("server error");
					} catch (IOException e) {
						e.printStackTrace();
						resultObj.setMessage("server error");
					}
					catch (Exception e) {
						e.printStackTrace();
						resultObj.setMessage("generic exception error");
					}
					// Request failed.
					if (result == null && !isCancelled()) {
						requests[0].getHandler().onError(resultObj);
						return resultObj;
					}
				}
			
				
			// Process Data Response
			
			if (RequestService.processDataResponse(result, resultObj)) {
				LogService.d("processDataResponse response", "success");
				if ((resultObj.getStatus() == GatewayStatusCode.Success) && requests[0].getCacheable() && !fromCache ) {
					//DiskCacheService.saveData(md5, result, expiryDate);
				}
				requests[0].getHandler().onComplete(resultObj);
			}
			else {
				LogService.d("processDataResponse response", "fail");
				requests[0].getHandler().onError(resultObj);
			}
			return resultObj;
		}
	}
	public static boolean processDataResponse(String result, BaseResponse response) {
		boolean isSuccess = false;
		Integer finalStatusCode;
		/*
		try {
			JSONArray jArray = new JSONArray(result);
			
			JSONObject jsonResponse = jArray.getJSONObject(0);
			JSONObject status = jsonResponse.getJSONObject("status");
			JSONObject data = jsonResponse.getJSONObject("data");
			finalStatusCode = GatewayStatusCode.ConvertToGatewayCode(status.getInt("code"));
			Log.isLoggable("finalStatusCode", finalStatusCode);
			if (finalStatusCode == GatewayStatusCode.InternalServerError || finalStatusCode == GatewayStatusCode.NotFoundServerError) {
				response.setMessage("server error");
				return false;
			}
			else {
				if(!status.getString("message").isEmpty())
				response.setMessage(status.getString("message"));
					
			}
			LogService.d("messageNode",status.getString("message"));
			LogService.d("data",data.toString());
			response.setStatus(finalStatusCode);
			response.setJSONObject(data);
			isSuccess = true;
		}
		
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage("server error");
		}
		
	         catch(Exception e){
	        	 e.printStackTrace();
	 			response.setMessage("server error1");
	        	 //return new String("Exception: " + e.getMessage());
	         }
		*/
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		LogService.d("response", result);
		try {
			rootNode = mapper.readTree(result.getBytes());
			//Integer finalStatusCode;
			String message = null;
			JsonNode dataNode = null;
			try {
				Integer status = mapper.readValue(rootNode.findPath("Status").findPath("Code").toString(), Integer.class);
				Log.isLoggable("ststus", status);
				finalStatusCode = GatewayStatusCode.ConvertToGatewayCode(status);
				Log.isLoggable("finalStatusCode", finalStatusCode);

				JsonNode messageNode = rootNode.findPath("Status").findPath("Message");
				LogService.d("messageNode",messageNode.textValue().toString());
				if (messageNode != null) {
					message = messageNode.toString();
				}
				dataNode = rootNode.findPath("Data");
				LogService.d("dataNode", dataNode.textValue().toString());
				if(dataNode.isNull()) {
					response.setStatus(finalStatusCode);
					response.setMessage("server error");
					return false;
				}
				
				response.setStatus(finalStatusCode);
			} catch(Exception e) {
				// For json data containing format other than Gateway eg. advertising
				LogService.d("exception", e.toString());
				dataNode = rootNode;
				finalStatusCode = GatewayStatusCode.Success;
				response.setStatus(finalStatusCode);
			}
			// Error Code 500, Currently showing as Server Error!
			if (finalStatusCode == GatewayStatusCode.InternalServerError || finalStatusCode == GatewayStatusCode.NotFoundServerError) {
				response.setMessage("server error");
			}
			else {
				response.setMessage(message);
			}
			response.setData(dataNode);
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("server error");
		}
		
		
		return isSuccess;
	}
	private static class NumberGenerator {
		private int number, min, max;

		public NumberGenerator(int fromInt, int toInt) {
			min = fromInt;
			max = toInt;
			number = min - 1;
		}

		public int nextInt() {
			number++;
			if(number > max) {
				number = min - 1;
			}
			return number;
		}
	}
	
}
