package com.example.kemuseum;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kemuseum.controller.ControllerPilihMuseum;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.utils.ArrayAdapterPilihMuseum;

public class ViewPilihMuseum extends Activity {
	private ArrayAdapterPilihMuseum arrayAdapter = null;

	private LinearLayout llMusFas, llMusFat, llPilMus;
	private TextView tvTop;
	private DialogInterface.OnClickListener ViewMuseumTerkunci = null;
	private DialogInterface.OnClickListener test = null;
	private ControllerPilihMuseum controller;
	private ListView listView;
	ImageView Magni;

	private View woww = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pilih_museum);

		inisiasi();
		isiData();
		setClickListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_pencarian, menu);
		return true;
	}

	public void inisiasi() {
		Magni = (ImageView) findViewById(R.id.magni);
		llPilMus = (LinearLayout) findViewById(R.id.llViewPilMus);
		listView = (ListView) findViewById(R.id.list_view);
		controller = new ControllerPilihMuseum();
		tvTop = (TextView) findViewById(R.id.tvTop);
		
		Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/ubuntu.ttf");
		tvTop.setTypeface(font);
		
		
	}

	public void isiData() {
		List<Museum> daftarMuseum = controller.getDaftarMuseum();

		arrayAdapter = new ArrayAdapterPilihMuseum(this, daftarMuseum);
		listView.setAdapter(arrayAdapter);

		// bila ditap
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {

				try {
					final Museum item = (Museum) parent
							.getItemAtPosition(position);
					// true -> terkunci
					if (item.getStatusTerkunci()) {
						Intent i = new Intent(ViewPilihMuseum.this,
								ViewMuseumTerkunci.class);
						i.putExtra("Terkunci", "a");
						i.putExtra("idMuseum", item.getId());
						final int a = 1;
						startActivityForResult(i, a);
					} else {
						Intent i = new Intent(ViewPilihMuseum.this,
								ViewMuseumTerbuka.class);
						i.putExtra("Terbuka", "a");
						i.putExtra("idMuseum", item.getId());
						final int a = 1;
						startActivityForResult(i, a);
					}
				} catch (Exception e) {
					Log.d("asd", "gan " + e.toString());
				}
			}
		});
	}

	public void onClick(View view) {
		Intent i;
		final int a = 1;
		
		switch (view.getId()) {
		case (R.id.button_download):
			i = new Intent(ViewPilihMuseum.this, ViewUnduhMuseum.class);
			i.putExtra("Unduh", "a");
			startActivityForResult(i, a);
			break;
		case (R.id.button_hapus):
			i = new Intent(ViewPilihMuseum.this, ViewHapusMuseum.class);
			i.putExtra("Hapus", "a");
			startActivityForResult(i, a);
			break;
		}
	}

	public void setClickListener() {
		Magni.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ViewPilihMuseum.this, ViewPencarian.class);
				i.putExtra("Pencarian", "a");
				final int a = 1;
				startActivityForResult(i, a);

				// showDialog(0);
			}
		});
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0: {
			LayoutInflater inflater = LayoutInflater.from(this);
			View inflated = inflater.inflate(
					R.layout.activity_view_museum_terkunci, null);
			woww = inflated;
			OnTouchListener c = new OnTouchListener() {

				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					return false;
				}
			};
			return new AlertDialog.Builder(this)
					.setNegativeButton("Tidak", null)
					.setPositiveButton("Checkin", ViewMuseumTerkunci)
					.setView(woww).create();
		}

		}
		return null;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		// update, mungkin ada perubahan
		arrayAdapter.notifyDataSetChanged();
	}
}
