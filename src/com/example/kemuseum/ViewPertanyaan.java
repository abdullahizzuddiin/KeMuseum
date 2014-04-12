package com.example.kemuseum;

import java.util.List;

import com.example.kemuseum.R;
import com.example.kemuseum.controller.ControllerPertanyaan;
import com.example.kemuseum.controller.ControllerPilihMuseum;
import com.example.kemuseum.model.Pertanyaan;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class ViewPertanyaan extends Activity {
	private ListView listPertanyaan = null;
	private ControllerPertanyaan controller;
	private int idMuseum;
	private int idRuangan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pertanyaan);
		inisiasi();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_pertanyaan, menu);
		return true;
	}
	
	public void inisiasi() {
		listPertanyaan = (ListView) findViewById(R.id.list_viewPertanyaan);
		idMuseum = this.getIntent().getIntExtra("idMuseum", -1);
		idMuseum = this.getIntent().getIntExtra("idRuangan", -1);
	}
	
	public void isiData() {
		List<Pertanyaan> daftarPertanyaan = controller.getDaftarPertanyaan(idMuseum, idRuangan);
	}
}
