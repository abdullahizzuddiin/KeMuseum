package com.example.kemuseum;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.kemuseum.controller.ControllerRuangan;
import com.example.kemuseum.model.Barang;
import com.example.kemuseum.utils.ExpandableListAdapterBarang;

public class ViewRuangan extends Activity {
	ExpandableListAdapterBarang expandableAdapter;
	ExpandableListView expandableListView;
	ControllerRuangan controller;
	ImageView Magni;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_ruangan);
	
		inisialisasi();
		isiData();
		setClickListener();
	}
	public void setClickListener()
    {
    	Magni.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ViewRuangan.this, ViewPencarian.class);
				i.putExtra("Pencarian", "a");
				final int a = 1;
				startActivityForResult(i, a);

				// showDialog(0);
			}
		});
    }
	private void inisialisasi(){
		Magni = (ImageView) findViewById(R.id.magni);
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
			
			expandableAdapter = new ExpandableListAdapterBarang(this, daftarBarang);
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
