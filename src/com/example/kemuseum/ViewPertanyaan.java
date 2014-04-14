package com.example.kemuseum;

import java.util.List;

import com.example.kemuseum.R;
import com.example.kemuseum.controller.ControllerPertanyaan;
import com.example.kemuseum.controller.ControllerPilihMuseum;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.model.Pertanyaan;
import com.example.kemuseum.utils.ArrayAdapterDaftarJawaban;
import com.example.kemuseum.utils.ArrayAdapterDaftarPertanyaan;
import com.example.kemuseum.utils.ArrayAdapterPilihMuseum;
import com.example.kemuseum.utils.ArrayAdapterPilihRuangan;

import android.R.array;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ViewPertanyaan extends Activity {
	private ListView listPertanyaan = null;
	private ListView listJawaban = null;
//	private RadioGroup grupJawaban = null;
	private ControllerPertanyaan controllerPertanyaan;
//	private ControllerJawaban controllerJawaban;
	private int idMuseum;
	private int idRuangan;
	private View woww;
	
	private ArrayAdapterDaftarPertanyaan arrayAdapter = null;
	private ArrayAdapterDaftarJawaban arrayAdapterJawaban = null;
	
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
		controllerPertanyaan = new ControllerPertanyaan();
//		controllerJawaban = new ControllerJawaban();
		listPertanyaan = (ListView) findViewById(R.id.list_viewPertanyaan);
		
//		radioJawaban = (RadioGroup) findVIewById(R.id.radio_groupJawaban);
		idMuseum = this.getIntent().getIntExtra("idMuseum", -1);
		idRuangan = this.getIntent().getIntExtra("idRuangan", -1);
	}
	
	public void isiData() {
		List<Pertanyaan> daftarPertanyaan = controllerPertanyaan.getDaftarPertanyaan(idMuseum, idRuangan);
		arrayAdapter = new ArrayAdapterDaftarPertanyaan(this, daftarPertanyaan);
		listPertanyaan.setAdapter(arrayAdapter);
		
//		List<Pertanyaan> daftarJawaban = controllerPertanyaan.getDaftarPertanyaan(idMuseum, idRuangan);
		
		
			
		listPertanyaan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
//				final Museum item = (Museum) parent.getItemAtPosition(position);
				showDialog(0);
				
			}
		});
			
	}
	
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0: {
			LayoutInflater inflater = LayoutInflater.from(this);
	    	View inflated = inflater.inflate(R.layout.activity_view_jawaban, null);
	    	woww = inflated;
	    	
	    	listJawaban = (ListView) inflated.findViewById(R.id.list_viewJawaban);
	    	List<Pertanyaan> daftarPertanyaan = controllerPertanyaan.getDaftarPertanyaan(idMuseum, idRuangan);
	    	arrayAdapterJawaban = new ArrayAdapterDaftarJawaban(this, daftarPertanyaan);
			listJawaban.setAdapter(arrayAdapterJawaban);
	    	
	    	OnTouchListener c = new OnTouchListener() {
				
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					return false;
				}
			};
			return new AlertDialog.Builder(this).
					setPositiveButton("Oke", null).
					setView(woww).
					create();
		}
		
		}
		return null;
	}
}
