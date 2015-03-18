package com.jyoti.loginmodule;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.jyoti.loginmodule.adapters.CatLogListAdapter;
import com.jyoti.loginmodule.handlers.CatlogResponseHandler;
import com.jyoti.loginmodule.handlers.ItemDataHandler;
import com.jyoti.loginmodule.models.Item;
import com.jyoti.loginmodule.models.ItemList;
import com.jyoti.loginmodule.response.BaseResponse;
import com.jyoti.loginmodule.services.LogService;
import com.jyoti.loginmodule.services.RequestService;

public class CatLogActivity extends Activity implements ItemDataHandler<List<Item>>{
		private CatLogListAdapter adapter = null;
		private ListView catLogListView ;
		private List<Item> catLogListItem ;
		public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cat_log);
		
		LogService.d("CatLogActivity", "catlog activity created");
		catLogListView = (ListView) findViewById(R.id.catLogList);
		CatlogResponseHandler cHandler = new CatlogResponseHandler(this);
		RequestService.getCatlogItems(cHandler); 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cat_log, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void removeLoader() {
		findViewById(R.id.catlog_loader).setVisibility(View.INVISIBLE);
	}
	public void onDetailsError(String message) {
		boolean showAlert = false;
		// write here error code
	}

	@Override
	public void setItem(List<Item> catlogList, Object userData) {
		// TODO Auto-generated method stub
		LogService.d("CatLogActivity", "list size "+String.valueOf(catlogList.size()));
		catLogListItem = catlogList;
		loadCatlog();
		
	}

	private void loadCatlog() {
		
		LogService.d("CatLogActivity", " list items ready created");
		if(adapter == null) {		
			this.findViewById(R.id.catlog_loader).setVisibility(View.VISIBLE);
			adapter = new CatLogListAdapter(this,catLogListItem);			
		} 
		catLogListView.setAdapter(adapter);
		LogService.d("CatLogActivity", " list adapter set");
		//catLogList.setOnItemClickListener();
		catLogListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String detailUrl = null;
				String title = null;
				String movieId = null;
				try {
					// additem to cart
				} catch(Exception e) {

				}
				
			}
		});		
		removeLoader();	
	}

	@Override
	public void onError(BaseResponse result) {
		// TODO Auto-generated method stub
		if(!this.isFinishing()) {
			removeLoader();		
			//todo add alter dialogue
			//Utils.showAlert(this, result.getMessage());
		}
	}

}
