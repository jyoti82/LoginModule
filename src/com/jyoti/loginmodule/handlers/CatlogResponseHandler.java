package com.jyoti.loginmodule.handlers;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyoti.loginmodule.GatewayStatusCode;
import com.jyoti.loginmodule.MainApplication;
import com.jyoti.loginmodule.R;
import com.jyoti.loginmodule.models.Item;
import com.jyoti.loginmodule.response.BaseResponse;
import com.jyoti.loginmodule.runnables.ItemDataRunnable;
import com.jyoti.loginmodule.services.LogService;
import com.jyoti.loginmodule.utils.Utils;

public class CatlogResponseHandler implements ResponseHandler {
	private List<Item> catlogListParsed = null;
	private WeakReference<ItemDataHandler<List<Item>>> mContext;

	public CatlogResponseHandler(ItemDataHandler<List<Item>>  catLogActivity) {
		 mContext = new WeakReference<ItemDataHandler<List<Item>>>(catLogActivity);
	}
	@JsonCreator
	@Override
	public void onComplete(BaseResponse result) {
		String response="";
		ObjectMapper mapper = new ObjectMapper();
		if (result.getStatus() == GatewayStatusCode.Success) {
			JsonNode dataNode = result.getData();
			try {
				LogService.d("CatlogResponseHandler", dataNode.findPath("catlogItems").toString());
				response = dataNode.findPath("catlogItems").toString().replace('\\', ' ');
				LogService.d("CatlogResponseHandler", "response "+response);
				JSONArray catlogArray = new JSONArray(response);
				catlogListParsed = new ArrayList<Item>();
				for(int i=0; i<catlogArray.length();i++){
					JSONObject jsonObject = catlogArray.getJSONObject(i);
					Item item = new Item(jsonObject.getInt("itemId"),jsonObject.getString("itemName"),jsonObject.getString("itemDescription"),jsonObject.getString("image"),jsonObject.getInt("quantityAvailable"),jsonObject.getInt("price"));
					catlogListParsed.add(item);
					LogService.d("CatlogResponseHandler", "item "+ item.toString());
				}
				//catlogListParsed = mapper.readValue(dataNode.findPath("catlogItems").toString(),  mapper.getTypeFactory().constructCollectionType(List.class, Item.class));
				if(catlogListParsed == null) {
					result.setMessage(MainApplication.getContext().getString(R.string.NO_DATA_AVAILABLE_MESSAGE));
					postError(result);
					return;
				}
				
				//for(ItemList fList: catlogListParsed) {
						Utils.postToUIThread(new ItemDataRunnable<List<Item>>(mContext, catlogListParsed, result.getRequest().getUserData()));
					//	break;
				//} 						
				
				return;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// If reached here, some error in status code/parsing.
		postError(result);
		
	}

	private void postError(BaseResponse result) {
		Utils.postToUIThread(new ItemDataRunnable<List<Item>>(mContext, result));
	}

	@Override
	public void onError(BaseResponse result) {
		postError(result);
		
	}

}
