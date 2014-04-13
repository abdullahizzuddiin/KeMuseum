package com.example.kemuseum;

import com.example.kemuseum.R;
import com.example.kemuseum.controller.ControllerMuseum;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class ViewMuseumTerkunci extends Activity {
	private ImageView checkin = null;
	private ControllerMuseum chekinCont = null;
	private int idMuseum;
	private View Tampilan;
	private DialogInterface.OnClickListener pindahTampilan = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_museum_terkunci);
		inisiasi();
		setClickListener();
		this.idMuseum = this.getIntent().getIntExtra("idMuseum", -1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_museum_terkunci, menu);
		return true;
	}

	public void inisiasi() {
		checkin = (ImageView) findViewById(R.id.checkin);
		chekinCont = new ControllerMuseum();

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
