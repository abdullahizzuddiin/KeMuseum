package com.example.kemuseum;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kemuseum.controller.ControllerUnduhMuseum;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.utils.ArrayAdapterUnduhMuseum;

public class ViewUnduhMuseum extends Activity {
	private ProgressDialog progress;
	private List<Museum> daftarMuseumServer;
	private ControllerUnduhMuseum controller;
	private ListView listViewServer;
	private ArrayAdapterUnduhMuseum arrayAdapter;
	private boolean selesaiUnduh;

	private ViewUnduhMuseum host = this;

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
		new DownloadMuseumListTask().execute();
		
		host.runOnUiThread(new Runnable() {
			public void run() {
				while (!selesaiUnduh) {
				}
				aturReaksi();
			}
		});
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
		if (arrayAdapter != null){
			arrayAdapter.notifyDataSetChanged();
		}
	}

	private void aturReaksi(){
		arrayAdapter = new ArrayAdapterUnduhMuseum(this, daftarMuseumServer);
		listViewServer.setAdapter(arrayAdapter);
		listViewServer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent,
					final View view, int position, long id) {
				final Museum item = (Museum) parent
						.getItemAtPosition(position);

				// false -> belum ada
				if (!controller.sudahDimiliki(item)) {
					Log.d("asd",
							"gan mau download " + item.getNama());
					progress.setTitle("Mengambil data dari server");
					progress.setMessage("Mohon tunggu...");
					progress.show();

					selesaiUnduh = false;
					new Thread(new Runnable() {
						public void run() {
							controller.unduhMuseum(item);
							progress.dismiss();
							selesaiUnduh = true;

							host.runOnUiThread(new Runnable() {
								public void run() {
									while (!selesaiUnduh) {
									}
									;
									host.onResume();
								}
							});
						}
					}).start();

				}
			}
		});
	}
	
	class DownloadMuseumListTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void... v) {
			daftarMuseumServer = controller.getDaftarSemuaMuseum();
			progress.dismiss();
			selesaiUnduh = true;
			
			return null;
		}
	}
}
