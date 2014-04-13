package com.example.kemuseum;

import java.util.List;

import com.example.kemuseum.R;
import com.example.kemuseum.controller.ControllerPertanyaan;
import com.example.kemuseum.controller.ControllerPilihMuseum;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.model.Pertanyaan;
import com.example.kemuseum.utils.ArrayAdapterDaftarPertanyaan;
import com.example.kemuseum.utils.ArrayAdapterPilihMuseum;
import com.example.kemuseum.utils.ArrayAdapterPilihRuangan;

import android.R.array;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewPertanyaan extends Activity {
	private ListView listPertanyaan = null;
	private ControllerPertanyaan controller;
	private int idMuseum;
	private int idRuangan;
	
	private ArrayAdapterDaftarPertanyaan arrayAdapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pertanyaan);
		inisiasi();
		isiData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_pertanyaan, menu);
		return true;
	}
	
	public void inisiasi() {
		controller = new ControllerPertanyaan();
		listPertanyaan = (ListView) findViewById(R.id.list_viewPertanyaan);
		idMuseum = this.getIntent().getIntExtra("idMuseum", -1);
		idRuangan = this.getIntent().getIntExtra("idRuangan", -1);
	}
	
	public void isiData() {
		List<Pertanyaan> daftarPertanyaan = controller.getDaftarPertanyaan(idMuseum, idRuangan);
		
		arrayAdapter = new ArrayAdapterDaftarPertanyaan(this, daftarPertanyaan);
		listPertanyaan.setAdapter(arrayAdapter);
		
		
//		listPertanyaan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, final View view,
//					int position, long id) {
//				final Museum item = (Museum) parent.getItemAtPosition(position);
//				
//			}
//		});
			
	}
}
