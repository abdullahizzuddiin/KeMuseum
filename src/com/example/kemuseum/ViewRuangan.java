package com.example.kemuseum;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.example.kemuseum.controller.ControllerRuangan;
import com.example.kemuseum.model.Barang;
import com.example.kemuseum.model.Ruangan;
import com.example.kemuseum.utils.ExpandableListAdapter;

public class ViewRuangan extends Activity {
	ExpandableListAdapter expandableAdapter;
	ExpandableListView expandableListView;
	ControllerRuangan controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_ruangan);
	
		inisialisasi();
		isiData();
	}

	private void inisialisasi(){
		expandableListView = (ExpandableListView) findViewById(R.id.list_barang);
		controller = new ControllerRuangan();
	}
	
	private void isiData(){
		Intent i = this.getIntent();
		int idMuseum = i.getIntExtra("idMuseum", -1);
		int idRuangan = i.getIntExtra("idRuangan", -1);
		// valid
		if ((idMuseum != -1) && (idRuangan != -1)){
			List<Barang> daftarBarang = controller.getDaftarBarang(idMuseum, idRuangan);
			
			expandableAdapter = new ExpandableListAdapter(this, daftarBarang);
			expandableListView.setAdapter(expandableAdapter);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_ruangan, menu);
		return true;
	}

}
