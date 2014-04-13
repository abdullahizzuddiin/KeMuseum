package com.example.kemuseum;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.kemuseum.controller.ControllerCapaian;
import com.example.kemuseum.model.Capaian;
import com.example.kemuseum.utils.ExpandableListAdapterCapaian;

public class ViewCapaian extends Activity {	
	private ControllerCapaian controller;
	private ExpandableListAdapterCapaian expandableAdapter;
	private ExpandableListView expandableListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_capaian);
		inisiasi();
		isiData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_info_capaian, menu);
		return true;
	}
	
	public void inisiasi()
	{
		controller = new ControllerCapaian();
		expandableListView = (ExpandableListView) findViewById(R.id.list_pencapaian);
	}
	
	private void isiData(){
		List<Capaian> daftarCapaian = controller.getDaftarCapaian();
		
		Log.d("asd", "gan " + daftarCapaian.size());
		expandableAdapter = new ExpandableListAdapterCapaian(this, daftarCapaian);
		expandableListView.setAdapter(expandableAdapter);
	}
}
