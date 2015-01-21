package com.jyoti.loginmodule;

import java.util.ArrayList;
import java.util.List;

import com.jyoti.loginmodule.adapters.CatLogListAdapter;
import com.jyoti.loginmodule.models.Item;
import com.jyoti.loginmodule.services.LogService;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class CatLogActivity extends Activity {
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cat_log);
		LogService.d("CatLogActivity", "catlog activity created");
		ListView catLogList = (ListView) findViewById(R.id.catLogList);
		List<Item> itemList = new ArrayList<Item>(); 
		itemList.add(new Item("cat1","item1",500,100,"INR"));
		itemList.add(new Item("cat1","item2",500,100,"INR"));
		itemList.add(new Item("cat1","item3",500,100,"INR"));
		itemList.add(new Item("cat1","item4",500,100,"INR"));
		LogService.d("CatLogActivity", " list items ready created");
		CatLogListAdapter adapter = new CatLogListAdapter(this,itemList);
		catLogList.setAdapter(adapter);
		LogService.d("CatLogActivity", " list adapter set");
		//catLogList.setOnItemClickListener();
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
}
