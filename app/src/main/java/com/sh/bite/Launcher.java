package com.sh.bite;

import java.util.List;

import com.example.bite.R;
import com.sh.database.DashboardManager;
import com.sh.entities.Restaurant;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Launcher extends Activity {
	
	Restaurant currRestaurant = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		final Context ctx = getApplicationContext();
		
		Button btnRestaurant = (Button) findViewById(R.id.button1);
		final TextView txtRestaurant = (TextView) findViewById(R.id.textView1);

		Button btnMenu = (Button) findViewById(R.id.button2);
		final TextView txtMenu = (TextView) findViewById(R.id.textView2);
		
		btnRestaurant.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				Restaurant tempRestaurant = DashboardManager.getRestaurantFromName(Launcher.this, "Veggie Land");
				txtRestaurant.setText(tempRestaurant.getName());
				currRestaurant = tempRestaurant;
			}
		});
		
		btnMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				List<String> list = DashboardManager.getAllMenuForRestaurant(Launcher.this, currRestaurant.getId());
				txtMenu.setText(list.get(0));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
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
