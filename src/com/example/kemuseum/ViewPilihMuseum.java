package com.example.kemuseum;

import com.example.kemuseum.R;

import android.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewPilihMuseum extends Activity {	
	LinearLayout llMusFas, llMusFat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pilih_museum);
		inisiasi();
		setClickListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_pencarian, menu);
		return true;
	}
	
	public void inisiasi()
	{
		llMusFas = (LinearLayout) findViewById(R.id.llMusFas);
		llMusFat = (LinearLayout) findViewById(R.id.llMusFat);
	}
	
    public void setClickListener()
    {
    	llMusFas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent (ViewPilihMuseum.this, ViewMuseumTerbuka.class);
				i.putExtra("Terbuka", "a");
				final int a = 1;
				startActivityForResult(i, a);
			}
		});
    	
    	llMusFat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent (ViewPilihMuseum.this, ViewMuseumTerkunci.class);
				i.putExtra("Terkunci", "a");
				final int a = 1;
				startActivityForResult(i, a);
			}
		});
    }
}
