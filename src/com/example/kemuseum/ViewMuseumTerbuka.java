package com.example.kemuseum;

import com.example.kemuseum.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ViewMuseumTerbuka extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_museum_terbuka);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_museum_terbuka, menu);
		return true;
	}

}
