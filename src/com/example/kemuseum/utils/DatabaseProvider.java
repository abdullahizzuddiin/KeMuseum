package com.example.kemuseum.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kemuseum.model.Barang;
import com.example.kemuseum.model.Keinginan;
import com.example.kemuseum.model.Koordinat;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.model.Pertanyaan;
import com.example.kemuseum.model.Ruangan;

public class DatabaseProvider {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	
	public DatabaseProvider(Context context){
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public List<Museum> getObjekDaftarMuseum(){
		List<Museum> ret = new ArrayList<Museum>();
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_MUSEUM, 
				                       MySQLiteHelper.COLUMN_MUSEUM, 
				                       null, 
				                       null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			Museum m = cursorToMuseum(cursor);
			ret.add(m);
			cursor.moveToNext();
		}
		
		return ret;
	}

	private List<Ruangan> getObjekDaftarRuangan(int idMuseum){
		List<Ruangan> ret = new ArrayList<Ruangan>();
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_RUANGAN, 
						               MySQLiteHelper.COLUMN_RUANGAN, 
						               MySQLiteHelper.RUANGAN_ID_MUSEUM + " = " + idMuseum, 
						               null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			Ruangan r = cursorToRuangan(cursor);
			ret.add(r);
			cursor.moveToNext();
		}
		
		return ret;
	}
	
	private List<Barang> getObjekDaftarBarang(int idMuseum, int idRuangan){
		List<Barang> ret = new ArrayList<Barang>();
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_BARANG, 
						               MySQLiteHelper.COLUMN_BARANG, 
						               MySQLiteHelper.BARANG_ID_MUSEUM + " = " + idMuseum + " AND " + 
						               MySQLiteHelper.BARANG_ID_RUANGAN + " = " + idRuangan, 
						               
						               null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			Barang b = cursorToBarang(cursor);
			ret.add(b);
			cursor.moveToNext();
		}
		
		return ret;
	}
	
	private List<Pertanyaan> getObjekDaftarPertanyaan(int idMuseum, int idRuangan){
		List<Pertanyaan> ret = new ArrayList<Pertanyaan>();
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PERTANYAAN, 
						               MySQLiteHelper.COLUMN_PERTANYAAN, 
						               MySQLiteHelper.PERTANYAAN_ID_MUSEUM + " = " + idMuseum + " AND " + 
						               MySQLiteHelper.PERTANYAAN_ID_RUANGAN + " = " + idRuangan, 
	               
						               null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			Pertanyaan p = cursorToPertanyaan(cursor);
			ret.add(p);
			cursor.moveToNext();
		}
		
		return ret;
	}
	
	private List<Keinginan> getObjekDaftarKeinginan(){
		List<Keinginan> ret = new ArrayList<Keinginan>();
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_KEINGINAN, 
						               MySQLiteHelper.COLUMN_KEINGINAN, 
						               null, 
	               
						               null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			Keinginan k = cursorToKeinginan(cursor);
			ret.add(k);
			cursor.moveToNext();
		}
		
		return ret;
	}
	
	private Museum cursorToMuseum(Cursor c){
		int p = 0;
		
		// berurutan
		int id = c.getInt(p++);
		String nama = c.getString(p++);
		String deskripsi = c.getString(p++);
		Koordinat koordinatKiriAtas = new Koordinat(c.getString(p++));
		Koordinat koordinatKananBawah = new Koordinat(c.getString(p++));
		boolean statusTerkunci = (c.getInt(p++) != 0);
		
		List<Ruangan> daftarRuangan = this.getObjekDaftarRuangan(id);
		
		Museum m = new Museum(id, nama, deskripsi, koordinatKiriAtas, koordinatKananBawah, 
				               statusTerkunci, daftarRuangan);
		
		return m;
	}
	
	private Ruangan cursorToRuangan(Cursor c){
		int p = 0;
		
		// berurutan
		int idMuseum = c.getInt(p++);
		int id = c.getInt(p++);
		String nama = c.getString(p++);
		String deskripsi = c.getString(p++);
		boolean statusTerkunci = (c.getInt(p++) != 0);
		int banyakPercobaanBukaKunci = c.getInt(p++);
		int prioritas = c.getInt(p++);
		
		List<Barang> daftarBarang = this.getObjekDaftarBarang(idMuseum, id);
		List<Pertanyaan> daftarPertanyaan = this.getObjekDaftarPertanyaan(idMuseum, id);
		
		Ruangan r = new Ruangan(idMuseum, id, nama, deskripsi, 
				                 statusTerkunci, banyakPercobaanBukaKunci, prioritas, 
				                 daftarBarang, daftarPertanyaan);
		
		return r;
	}
	
	private Barang cursorToBarang(Cursor c){
		int p = 0;
		
		// berurutan
		int idMuseum = c.getInt(p++);
		int idRuangan = c.getInt(p++);
		int id = c.getInt(p++);
		String namaBerkasGambar = c.getString(p++);
		String nama = c.getString(p++);
		String deskripsi = c.getString(p++);
		String kategori = c.getString(p++);
		
		Barang b = new Barang(idMuseum, idRuangan, id, namaBerkasGambar, nama, deskripsi, kategori);
		
		return b;
	}
	
	private Pertanyaan cursorToPertanyaan(Cursor c){
		int p = 0;
		
		// berurutan
		int idMuseum = c.getInt(p++);
		int idRuangan = c.getInt(p++);
		int id = c.getInt(p++);
		String soal = c.getString(p++);
		String jawaban = c.getString(p++);
		
		Pertanyaan pp = new Pertanyaan(idMuseum, idRuangan, id, soal, jawaban);
		
		return pp;
	}
	
	private Keinginan cursorToKeinginan(Cursor c){
		int p = 0;
		
		// berurutan
		int id = c.getInt(p++);
		String tanggal = c.getString(p++);
		String nama = c.getString(p++);
		String email = c.getString(p++);
		String deskripsi = c.getString(p++);
		
		Keinginan k = new Keinginan(id, tanggal, nama, email, deskripsi);
		
		return k;
	}
}
