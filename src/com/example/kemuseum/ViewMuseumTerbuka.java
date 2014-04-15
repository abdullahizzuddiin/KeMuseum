package com.example.kemuseum;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.kemuseum.controller.ControllerMuseum;
import com.example.kemuseum.model.Ruangan;
import com.example.kemuseum.utils.ArrayAdapterPilihRuangan;

public class ViewMuseumTerbuka extends Activity {
	ArrayAdapterPilihRuangan arrayAdapter;
	ListView listView;
	ControllerMuseum controller;
	ImageView Magni;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_museum_terbuka);
	
		inisialisasi();
		setClickListener();
		isiData();
	}
	
	

	private void inisialisasi(){
		listView = (ListView) findViewById(R.id.list_view_ruangan);
		controller = new ControllerMuseum();
		Magni = (ImageView) findViewById(R.id.magni);
	}
	
	public void setClickListener() {
		Magni.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ViewMuseumTerbuka.this, ViewPencarian.class);
				i.putExtra("Pencarian", "a");
				final int a = 1;
				startActivityForResult(i, a);

				// showDialog(0);
			}
		});
	}
	private void isiData(){
		Intent i = this.getIntent();
		int idMuseum = i.getIntExtra("idMuseum", -1);
		
		// valid
		if (idMuseum != -1){
			List<Ruangan> daftarRuangan = controller.getDaftarRuangan(idMuseum);
			
			arrayAdapter = new ArrayAdapterPilihRuangan(this, daftarRuangan);
			listView.setAdapter(arrayAdapter);
			
			// bila ditap
			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, final View view,
						int position, long id) {
					final Ruangan item = (Ruangan) parent.getItemAtPosition(position);
					
					// true -> terkunci
					if (item.getStatusTerkunci()){
						Intent i = new Intent (ViewMuseumTerbuka.this, ViewPertanyaan.class);
						i.putExtra("Terkunci", "a");
						i.putExtra("idMuseum", item.getIdMuseum());
						i.putExtra("idRuangan", item.getId());
						Log.d("asd", "gan " + item.getId() + " " + item.getIdMuseum());
						final int a = 1;
						startActivityForResult(i, a);
					}else{
						Intent i = new Intent (ViewMuseumTerbuka.this, ViewRuangan.class);
						i.putExtra("Terbuka", "a");
						i.putExtra("idMuseum", item.getIdMuseum());
						i.putExtra("idRuangan", item.getId());
						final int a = 1;						
						startActivityForResult(i, a);
					}
				}
			});
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_museum_terbuka, menu);
		return true;
	}

}
