package com.example.kemuseum;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewInfoPengembang extends Activity {
	LinearLayout llHeaderTulisan, llPilMuseum, llCapaian, llCari, llAbout;
	TextView tvHeaderTulisan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_info_pengembang);
		inisiasi();
	}
	
	public void inisiasi() {
    	llHeaderTulisan = (LinearLayout) findViewById(R.id.llHeaderTulisan);
    	llPilMuseum = (LinearLayout) findViewById(R.id.llPilMuseum);
    	llCapaian = (LinearLayout) findViewById(R.id.llCapaian);
    	llCari = (LinearLayout) findViewById(R.id.llCari);
    	llAbout = (LinearLayout) findViewById(R.id.llAbout);
    	tvHeaderTulisan = (TextView) findViewById(R.id.tvHeaderTulisan);
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_info_pengembang, menu);
		return true;
	}

}
