package com.example.kemuseum;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kemuseum.controller.ControllerMuseum;
import com.example.kemuseum.model.Museum;

public class ViewMuseumTerkunci extends Activity {
	private ImageView checkin = null;
	private ControllerMuseum chekinCont = null;
	private int idMuseum;
	private View Tampilan;
	private TextView namaMuseum;
	private TextView deskripsiMuseum;
	private ImageView gambarMuseum;
	private Museum museum;
	private DialogInterface.OnClickListener pindahTampilan = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_museum_terkunci);
		inisiasi();
		isiData();
		setClickListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_museum_terkunci, menu);
		return true;
	}

	private void inisiasi() {
		checkin = (ImageView) findViewById(R.id.checkin);
		namaMuseum = (TextView) findViewById(R.id.preview_museum_nama);
		deskripsiMuseum = (TextView) findViewById(R.id.preview_museum_deskripsi);
		gambarMuseum = (ImageView) findViewById(R.id.preview_museum_gambar);

		chekinCont = new ControllerMuseum();
	}

	private void isiData(){
		this.idMuseum = this.getIntent().getIntExtra("idMuseum", -1);
		museum = chekinCont.getMuseum(idMuseum);
		
		namaMuseum.setText(museum.getNama());
		deskripsiMuseum.setText(museum.getDeskripsi());
	}
	
	public void setClickListener() {

		pindahTampilan = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ViewMuseumTerkunci.this,
						ViewMuseumTerbuka.class);
				i.putExtra("idMuseum", idMuseum);

				final int a = 1;
				startActivityForResult(i, a);
			}
		};

		checkin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean berhasilCheckin = chekinCont.bukaKunciMuseum(idMuseum);
				if (berhasilCheckin)
					showDialog(0);
				else
					showDialog(1);
			}
		});
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0: {
			LayoutInflater inflater = LayoutInflater.from(this);
			Tampilan = inflater.inflate(R.layout.dummy_view, null);

			return new AlertDialog.Builder(this)
					.setPositiveButton("Tutup", pindahTampilan)
					.setMessage("Check-in Berhasil").setView(Tampilan).create();

		}

		case 1: {
			LayoutInflater inflater = LayoutInflater.from(this);
			Tampilan = inflater.inflate(R.layout.dummy_view, null);

			return new AlertDialog.Builder(this)
					.setPositiveButton("Tutup", null)
					.setMessage("Check-in Gagal").setView(Tampilan).create();

		}
		}
		return null;
	}

}
