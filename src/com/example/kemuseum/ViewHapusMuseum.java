package com.example.kemuseum;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kemuseum.controller.ControllerHapusMuseum;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.utils.ArrayAdapterHapusMuseum;

public class ViewHapusMuseum extends Activity {
	private List<Museum> daftarMuseumDevice;
	private ControllerHapusMuseum controller;
	private ListView listViewDevice;
	private ArrayAdapterHapusMuseum arrayAdapter;
	private TextView tvTop;
	
	private ViewHapusMuseum host = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_hapus_museum);
		
		inisiasi();
		isiData();
	}
	
	void inisiasi(){
		controller = new ControllerHapusMuseum();
		tvTop = (TextView) findViewById(R.id.tvTop);
		Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/ubuntu.ttf");
		tvTop.setTypeface(font);
		
		listViewDevice = (ListView) findViewById(R.id.list_museum_device);		
	}
	
	void isiData(){
		daftarMuseumDevice = controller.getDaftarMuseum();
		
		arrayAdapter = new ArrayAdapterHapusMuseum(this, daftarMuseumDevice);
		listViewDevice.setAdapter(arrayAdapter);
		listViewDevice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent,
					final View view, int position, long id) {
				final Museum item = (Museum) parent.getItemAtPosition(position);

				new AlertDialog.Builder(host)
			    .setTitle("Hapus Museum")
			    .setMessage("Apakah Anda yakin ingin menghapus museum ini?")
			    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            controller.hapusMuseum(item);
			            arrayAdapter.notifyDataSetChanged();
			        }
			     })
			    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			        	// gak ngapa2in
			        }
			     })
			    .setIcon(android.R.drawable.ic_dialog_alert)
			     .show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_hapus_museum, menu);
		return true;
	}

}
