package com.example.kemuseum;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kemuseum.controller.ControllerMuseum;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.utils.TextJustifyUtils;

public class ViewMuseumTerkunci extends Activity {
	private ProgressDialog progress;
	private ImageView checkin;
	private ControllerMuseum chekinCont;
	private int idMuseum;
	private View Tampilan;
	private TextView namaMuseum;
//	private TextView deskripsiMuseum;
	private WebView deskripsiMuseum;
	private TextView tvTop;
	private ImageView gambarMuseum;
	private Museum museum;
	private DialogInterface.OnClickListener pindahTampilan;

	private boolean dapatLokasi;
	private boolean berhasilCheckIn;
	
	private ViewMuseumTerkunci host = this;
	
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
//		namaMuseum = (TextView) findViewById(R.id.preview_museum_nama);
//		deskripsiMuseum = (TextView) findViewById(R.id.preview_museum_deskripsi);
		deskripsiMuseum = (WebView) findViewById(R.id.preview_museum_deskripsi);
		deskripsiMuseum.setBackgroundColor(Color.parseColor("#F6DEA0"));
		gambarMuseum = (ImageView) findViewById(R.id.preview_museum_gambar);
		
		chekinCont = new ControllerMuseum();
		tvTop = (TextView) findViewById(R.id.tvTop);
		Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/ubuntu.ttf");
		tvTop.setTypeface(font);
//		deskripsiMuseum.setTypeface(font);
		
		progress = new ProgressDialog(this);
		progress.setTitle("Mengambil posisi Anda");
		progress.setMessage("Mohon tunggu...");
	}

	private void isiData(){
		this.idMuseum = this.getIntent().getIntExtra("idMuseum", -1);
		museum = chekinCont.getMuseum(idMuseum);
		tvTop.setText(museum.getNama());
//		namaMuseum.setText(museum.getNama());
//		deskripsiMuseum.setText(museum.getDeskripsi());
		String htmlText = "<html><body style=\"text-align:justify\"> %s </body></Html>";
		String myData = museum.getDeskripsi();
		deskripsiMuseum.loadData(String.format(htmlText, myData), "text/html", "utf-8");
		gambarMuseum.setImageDrawable(chekinCont.getGambarMuseum(idMuseum));
	}
	
	public void setClickListener() {

		pindahTampilan = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent i = new Intent(ViewMuseumTerkunci.this,
						ViewMuseumTerbuka.class);
				i.putExtra("idMuseum", idMuseum);

				final int a = 1;
				startActivityForResult(i, a);
				finish();
			}
		};

		checkin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				progress.show();
				dapatLokasi = false;
				
				new Thread(new Runnable() {
					public void run() {
						berhasilCheckIn = chekinCont.bukaKunciMuseum(idMuseum);
						dapatLokasi = true;
						progress.dismiss();

						host.runOnUiThread(new Runnable() {
							public void run() {
								while (!dapatLokasi) {
								}
								if (berhasilCheckIn)
									showDialog(0);
								else
									showDialog(1);
							}
						});
					}
				}).start();
				/*
				new Thread(){
					public void run() {
						host.runOnUiThread(new Runnable(){
							public void run(){
								boolean berhasilCheckin = chekinCont.bukaKunciMuseum(idMuseum);
								progress.dismiss();
								
								if (berhasilCheckin)
									showDialog(0);
								else
									showDialog(1);
							}
						});
					}
				}.start();
				*/
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
