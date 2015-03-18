package com.jyoti.loginmodule.adapters;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jyoti.loginmodule.CatLogActivity;
import com.jyoti.loginmodule.R;
import com.jyoti.loginmodule.models.Item;
import com.jyoti.loginmodule.response.BaseResponse;
import com.jyoti.loginmodule.services.LogService;

public class CatLogListAdapter extends BaseAdapter  {
	private WeakReference<CatLogActivity> mContext;
	List<Item> itemList = new ArrayList<Item>(); 
	public CatLogListAdapter(Context context, List<Item> itemList) {
		LogService.d("CatLogListAdapter", "inside constructor");
		mContext = new WeakReference<CatLogActivity>((CatLogActivity)context);
        this.itemList = itemList;
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemList.get(position);
	}
	private static class ItemHolder {
			public long itemId;
			public int itemQuantityAvail;
		    public TextView itemName;
		    public TextView itemPrice;
		    public TextView itemDescription;
		    public ImageView itemImage;
		    public ImageButton addTOCartButton;
		    
		}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return  position ;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LogService.d("getView", String.valueOf(position));
		
		 // 1.
		View itemView = convertView;
		ItemHolder holder = new ItemHolder();
		
        if (convertView == null) {
        	LayoutInflater inflater  = (LayoutInflater) mContext.get().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	itemView = inflater.inflate(R.layout.item_row, null);
        
	        TextView itemName =  (TextView) itemView.findViewById(R.id.itemName);
	        TextView itemPrice = (TextView) itemView.findViewById(R.id.itemPrice);
	        TextView itemDesc = (TextView) itemView.findViewById(R.id.itemDesc);
	        ImageView itemImage = (ImageView) itemView.findViewById(R.id.itemImage);
	        ImageButton addTOCartButton = (ImageButton) itemView.findViewById(R.id.addTOCartButton);
	        // 3.
	        holder.itemName = itemName;
	        holder.itemPrice = itemPrice;
	        holder.itemDescription = itemDesc;
	        holder.itemImage = itemImage;
	        holder.addTOCartButton = addTOCartButton;
	        
	        itemView.setTag(holder);
        }
        else
        	holder = (ItemHolder) itemView.getTag();
        LogService.d("getView1", String.valueOf(position));
        
        Item listItem = (Item) getItem(position);
        holder.itemId =  listItem.getId();
        holder.itemQuantityAvail = listItem.getQuantityAvailable();
        holder.itemName.setText( listItem.getName());
        holder.itemDescription.setText(listItem.getDescription());
        holder.itemPrice.setText(String.valueOf(listItem.getPrice()));
        holder.itemImage.setImageResource(R.drawable.ic_launcher);
        holder.addTOCartButton.setImageResource(R.drawable.ic_launcher);

        return itemView;
	}
	
	// This will be called on UI Thread
		public void onError(BaseResponse result) {
			CatLogActivity act = mContext.get();
			if ((act != null)&&(!act.isFinishing())) {
				act.removeLoader();
				act.onDetailsError( result.getMessage());			
			}
		}

		
		public void refreshContent() {
			CatLogActivity act = mContext.get();
			if ((act != null)&&(!act.isFinishing())) {
				notifyDataSetChanged();
				act.removeLoader();
			}
		}

}
