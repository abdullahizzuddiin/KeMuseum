package com.example.kemuseum;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kemuseum.controller.ControllerUnduhMuseum;
import com.example.kemuseum.model.MetaMuseum;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.utils.ArrayAdapterUnduhMuseum;

public class ViewUnduhMuseum extends Activity {
	private ProgressDialog progress;
	private List<MetaMuseum> daftarMuseumServer;
	private ControllerUnduhMuseum controller;
	private ListView listViewServer;
	private ArrayAdapterUnduhMuseum arrayAdapter;
	private boolean selesaiUnduh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_unduh_museum);

		inisialisasi();
		isiData();
	}

	private void inisialisasi() {
		controller = new ControllerUnduhMuseum();
		listViewServer = (ListView) findViewById(R.id.list_museum_server);
	}

	private void isiData() {
		progress = new ProgressDialog(this);
		progress.setTitle("Mengambil data dari server");
		progress.setMessage("Mohon tunggu...");
		progress.show();

		selesaiUnduh = false;
		new Thread(new Runnable() {
			public void run() {
				daftarMuseumServer = controller.getDaftarSemuaMuseum();
				selesaiUnduh = true;
				progress.dismiss();
			}
		}).start();

		// busy waiting!
		while (!selesaiUnduh) {
		}
		;

		arrayAdapter = new ArrayAdapterUnduhMuseum(this, daftarMuseumServer);
		listViewServer.setAdapter(arrayAdapter);
		
		final ViewUnduhMuseum host = this;
		// bila ditap
		listViewServer
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent,
							final View view, int position, long id) {
						final MetaMuseum item = (MetaMuseum) parent
								.getItemAtPosition(position);

						// false -> belum ada
						if (!item.getSudahDilimiki()) {
							Log.d("asd", "gan mau download " + item.getNama());
							progress.setTitle("Mengambil data dari server");
							progress.setMessage("Mohon tunggu...");
							progress.show();

							selesaiUnduh = false;
							new Thread(new Runnable() {
								public void run() {
									controller.unduhMuseum(item);
									progress.dismiss();

//									host.onResume();
								}
							}).start();
						}
					}
				});
	}

	// sekedar buat testing
	private void pura2nunggu() {
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

	@Override
	public void onResume() {
		super.onResume();
		
		// update, mungkin ada perubahan
		arrayAdapter.notifyDataSetChanged();
	}
}
