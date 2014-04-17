package com.example.kemuseum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.kemuseum.controller.ControllerPertanyaan;
import com.example.kemuseum.model.Pertanyaan;
import com.example.kemuseum.utils.ArrayAdapterDaftarJawaban;
import com.example.kemuseum.utils.ArrayAdapterDaftarPertanyaan;

public class ViewPertanyaan extends Activity {
	private ListView listPertanyaan = null;
	private ListView listJawaban = null;
	private Button tombolKumpul;

	private ControllerPertanyaan controllerPertanyaan;
	private int idMuseum;
	private int idRuangan;
	private View woww;

	private ArrayAdapterDaftarPertanyaan arrayAdapter = null;
	private ArrayAdapterDaftarJawaban arrayAdapterJawaban = null;
	private List<Integer> terjodohkanDengan;
	private List<Integer> dijodohkanDengan;
	private int nomorSoalSekarang;
	private final ViewPertanyaan host = this;

	private List<Pertanyaan> daftarPertanyaan;
	private List<Pertanyaan> daftarJawaban;

	private AlertDialog alert;
	private final int TIDAK_TERJODOHKAN = -1;
	private final int TIDAK_DIJODOHKAN = -1;

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
		listPertanyaan = (ListView) findViewById(R.id.list_viewPertanyaan);
		tombolKumpul = (Button) findViewById(R.id.button_submit);

		// radioJawaban = (RadioGroup) findVIewById(R.id.radio_groupJawaban);
		idMuseum = this.getIntent().getIntExtra("idMuseum", -1);
		idRuangan = this.getIntent().getIntExtra("idRuangan", -1);

	}

	public void isiData() {
		daftarPertanyaan = controllerPertanyaan.getDaftarPertanyaan(idMuseum,
				idRuangan);
		
		daftarJawaban = new ArrayList<Pertanyaan>();
		for (int i = 0; i < daftarPertanyaan.size(); i++){
			daftarJawaban.add(daftarPertanyaan.get(i));
		}
		// acak jawaban
		Collections.shuffle(daftarJawaban);

		terjodohkanDengan = new ArrayList<Integer>();
		dijodohkanDengan = new ArrayList<Integer>();
		for (int i = 0; i < daftarPertanyaan.size(); i++) {
			terjodohkanDengan.add(TIDAK_TERJODOHKAN);
			dijodohkanDengan.add(TIDAK_DIJODOHKAN);
		}

		arrayAdapter = new ArrayAdapterDaftarPertanyaan(this, daftarPertanyaan,
				daftarJawaban, dijodohkanDengan);
		listPertanyaan.setAdapter(arrayAdapter);

		listPertanyaan
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent,
							final View view, int position, long id) {
						Log.d("asd", "gan soal " + position);
						nomorSoalSekarang = position;

						AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
								host);
						alertBuilder.setView(getViewJawaban());
						alert = alertBuilder.create();
						alert.show();
					}
				});

	}

	private View getViewJawaban() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View inflated = inflater.inflate(R.layout.activity_view_jawaban, null);
		woww = inflated;

		listJawaban = (ListView) inflated.findViewById(R.id.list_viewJawaban);
		arrayAdapterJawaban = new ArrayAdapterDaftarJawaban(this,
				daftarJawaban, terjodohkanDengan);
		listJawaban.setAdapter(arrayAdapterJawaban);

		OnTouchListener c = new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				return false;
			}
		};

		listJawaban
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent,
							final View view, int position, long id) {
						host.pilih(position);
						alert.dismiss();
					}
				});

		return woww;
	}

	public void pilih(int posJawaban) {
		// lepas kemelekatan
		int id = terjodohkanDengan.get(posJawaban);
		if (id != TIDAK_TERJODOHKAN) {
			dijodohkanDengan.set(id, TIDAK_DIJODOHKAN);
		}
		id = dijodohkanDengan.get(nomorSoalSekarang);
		if (id != TIDAK_DIJODOHKAN) {
			terjodohkanDengan.set(id, TIDAK_TERJODOHKAN);
		}

		dijodohkanDengan.set(nomorSoalSekarang, posJawaban);
		terjodohkanDengan.set(posJawaban, nomorSoalSekarang);

		arrayAdapterJawaban.notifyDataSetChanged();
		arrayAdapter.notifyDataSetChanged();

		Log.d("asd", "gan dijodohkan " + nomorSoalSekarang + " "
				+ daftarJawaban.get(posJawaban).getJawaban());
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case (R.id.button_submit):
			List<String> jawaban = new ArrayList<String>();
			for (int i = 0; i < daftarPertanyaan.size(); i++) {
				int posJawaban = dijodohkanDengan.get(i);
				if (posJawaban == TIDAK_DIJODOHKAN) {
					jawaban.add("");
				} else {
					jawaban.add(daftarJawaban.get(posJawaban).getJawaban());
				}
			}

			boolean benarSemua = controllerPertanyaan.cekJawaban(idMuseum,
					idRuangan, jawaban);
			showDialog(benarSemua ? 0 : 1);
		}
	}
	
	protected Dialog onCreateDialog(int id) {
		OnClickListener pindahTampilan = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent i = new Intent(ViewPertanyaan.this,
						ViewRuangan.class);
				i.putExtra("idMuseum", idMuseum);
				i.putExtra("idRuangan", idRuangan);
				final int a = 1;
				startActivityForResult(i, a);
			}
		};
		
		View Tampilan;
		switch (id) {
		case 0: {
			LayoutInflater inflater = LayoutInflater.from(this);
			Tampilan = inflater.inflate(R.layout.dummy_view, null);

			return new AlertDialog.Builder(this)
					.setPositiveButton("Tutup", pindahTampilan)
					.setMessage("Jawaban benar semua!").setView(Tampilan).create();

		}

		case 1: {
			LayoutInflater inflater = LayoutInflater.from(this);
			Tampilan = inflater.inflate(R.layout.dummy_view, null);

			return new AlertDialog.Builder(this)
					.setPositiveButton("Tutup", null)
					.setMessage("Masih ada jawaban yang salah...").setView(Tampilan).create();

		}
		}
		return null;
	}
}
