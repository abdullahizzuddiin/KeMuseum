package com.example.kemuseum;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.example.kemuseum.controller.ControllerUnduhMuseum;
import com.example.kemuseum.model.MetaMuseum;
import com.example.kemuseum.utils.ArrayAdapterPilihMuseum;

public class ViewUnduhMuseum extends Activity {
	private ProgressDialog progress;
	private List<MetaMuseum> daftarMuseumServer;
	private ControllerUnduhMuseum controller;
	private ListView listViewServer;
//	private ArrayAdapterUnduhMuseum;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_unduh_museum);

		inisialisasi();
		isiData();
	}
	
	private void inisialisasi(){
		controller = new ControllerUnduhMuseum();
		listViewServer = (ListView) findViewById(R.id.list_museum_server);
	}
	
	private void isiData(){
		progress = new ProgressDialog(this);
		progress.setTitle("Mengambil data dari server");
		progress.setMessage("Mohon tunggu...");
		progress.show();

		new Thread(new Runnable() {
			public void run() {
				daftarMuseumServer = controller.getDaftarSemuaMuseum();
				
//				arrayAdapter = new ArrayAdapterPilihMuseum(this, daftarMuseumServer);
//				listView.setAdapter(arrayAdapter);
				
				progress.dismiss();
			}
		}).start();
	}
	
	// sekedar buat testing
	private void pura2nunggu(){
		int nilai = 0;
		for (int i = 0; i < 50000; i++) {
			int j = i;
			while (j > 0) {
				j--;
				nilai += j;
			}
		}
		Log.d("asd", "gan akhirnya " + nilai);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_unduh_museum, menu);
		return true;
	}

}
