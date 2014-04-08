package com.example.kemuseum;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.kemuseum.model.Museum;
import com.example.kemuseum.model.MuseumManager;
import com.example.kemuseum.utils.JSONParser;

public class ViewDummy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_dummy);
		
		MuseumManager mm = new MuseumManager(getAssets(), getApplicationContext());
		
		// percobaan tambah museum dari JSON
		String zz = "{\"_id\" : 2,\"nama\" : \"nama museum 2\",\"deskripsi\" : \"deskripsi museum 2\",\"koordinat_kiri_atas\" : \"0.809676 0.700976\",\"koordinat_kanan_bawah\" : \"0.121479 0.088795\",\"status_terkunci\" : true,\"daftar_ruangan\" : [{\"_id_museum\" : 2,\"_id\" : 0,\"nama\" : \"nama ruangan 2 0\",\"deskripsi\" : \"deskripsi ruangan 2 0\",\"status_terkunci\" : false,\"banyak_percobaan_buka_kunci\" : 1502820864,\"prioritas\" : 142559277,\"daftar_barang\" : [{\"_id_museum\" : 2,\"_id_ruangan\" : 0,\"_id\" : 0,\"nama_berkas_gambar\" : \"nama berkas gambar 2 0 0\",\"nama\" : \"nama barang 2 0 0\",\"deskripsi\" : \"deskripsi barang 2 0 0\",\"kategori\" : \"kategori barang 2 0 0\"},{\"_id_museum\" : 2,\"_id_ruangan\" : 0,\"_id\" : 1,\"nama_berkas_gambar\" : \"nama berkas gambar 2 0 1\",\"nama\" : \"nama barang 2 0 1\",\"deskripsi\" : \"deskripsi barang 2 0 1\",\"kategori\" : \"kategori barang 2 0 1\"},{\"_id_museum\" : 2,\"_id_ruangan\" : 0,\"_id\" : 2,\"nama_berkas_gambar\" : \"nama berkas gambar 2 0 2\",\"nama\" : \"nama barang 2 0 2\",\"deskripsi\" : \"deskripsi barang 2 0 2\",\"kategori\" : \"kategori barang 2 0 2\"},{\"_id_museum\" : 2,\"_id_ruangan\" : 0,\"_id\" : 3,\"nama_berkas_gambar\" : \"nama berkas gambar 2 0 3\",\"nama\" : \"nama barang 2 0 3\",\"deskripsi\" : \"deskripsi barang 2 0 3\",\"kategori\" : \"kategori barang 2 0 3\"}],\"daftar_pertanyaan\" : [{\"_id_museum\" : 2,\"_id_ruangan\" : 0,\"_id\" : 0,\"soal\" : \"soal pertanyaan 2 0 0\",\"jawaban\" : \"jawaban pertanyaan 2 0 0\"},{\"_id_museum\" : 2,\"_id_ruangan\" : 0,\"_id\" : 1,\"soal\" : \"soal pertanyaan 2 0 1\",\"jawaban\" : \"jawaban pertanyaan 2 0 1\"},{\"_id_museum\" : 2,\"_id_ruangan\" : 0,\"_id\" : 2,\"soal\" : \"soal pertanyaan 2 0 2\",\"jawaban\" : \"jawaban pertanyaan 2 0 2\"}]},{\"_id_museum\" : 2,\"_id\" : 1,\"nama\" : \"nama ruangan 2 1\",\"deskripsi\" : \"deskripsi ruangan 2 1\",\"status_terkunci\" : true,\"banyak_percobaan_buka_kunci\" : 1380759627,\"prioritas\" : 2127304342,\"daftar_barang\" : [{\"_id_museum\" : 2,\"_id_ruangan\" : 1,\"_id\" : 0,\"nama_berkas_gambar\" : \"nama berkas gambar 2 1 0\",\"nama\" : \"nama barang 2 1 0\",\"deskripsi\" : \"deskripsi barang 2 1 0\",\"kategori\" : \"kategori barang 2 1 0\"},{\"_id_museum\" : 2,\"_id_ruangan\" : 1,\"_id\" : 1,\"nama_berkas_gambar\" : \"nama berkas gambar 2 1 1\",\"nama\" : \"nama barang 2 1 1\",\"deskripsi\" : \"deskripsi barang 2 1 1\",\"kategori\" : \"kategori barang 2 1 1\"},{\"_id_museum\" : 2,\"_id_ruangan\" : 1,\"_id\" : 2,\"nama_berkas_gambar\" : \"nama berkas gambar 2 1 2\",\"nama\" : \"nama barang 2 1 2\",\"deskripsi\" : \"deskripsi barang 2 1 2\",\"kategori\" : \"kategori barang 2 1 2\"}],\"daftar_pertanyaan\" : [{\"_id_museum\" : 2,\"_id_ruangan\" : 1,\"_id\" : 0,\"soal\" : \"soal pertanyaan 2 1 0\",\"jawaban\" : \"jawaban pertanyaan 2 1 0\"},{\"_id_museum\" : 2,\"_id_ruangan\" : 1,\"_id\" : 1,\"soal\" : \"soal pertanyaan 2 1 1\",\"jawaban\" : \"jawaban pertanyaan 2 1 1\"},{\"_id_museum\" : 2,\"_id_ruangan\" : 1,\"_id\" : 2,\"soal\" : \"soal pertanyaan 2 1 2\",\"jawaban\" : \"jawaban pertanyaan 2 1 2\"},{\"_id_museum\" : 2,\"_id_ruangan\" : 1,\"_id\" : 3,\"soal\" : \"soal pertanyaan 2 1 3\",\"jawaban\" : \"jawaban pertanyaan 2 1 3\"},{\"_id_museum\" : 2,\"_id_ruangan\" : 1,\"_id\" : 4,\"soal\" : \"soal pertanyaan 2 1 4\",\"jawaban\" : \"jawaban pertanyaan 2 1 4\"}]}]}";
		Museum mo = JSONParser.toMuseum(zz);
		mm.tambahMuseum(mo);
		
		Log.d("ViewDummy", "gan mulai");
		List<Museum> lm = mm.getDaftarMuseum();
		for (Museum m : lm){
			if (m == null) continue;
			
			Log.d("ViewDummy", "gan " + m.getNama() + " " + m.getDeskripsi());
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.dummy, menu);
//		return true;
//	}
}
