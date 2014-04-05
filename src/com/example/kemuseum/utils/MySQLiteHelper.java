package com.example.kemuseum.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "KeMuseum.db";
	
	// tambah versi apabila ada perubahan database
	private static final int DATABASE_VERSION = 1;
	
	// definsi nama tabel
	public static final String TABLE_MUSEUM = "museum";
	public static final String TABLE_RUANGAN = "ruangan";
	public static final String TABLE_BARANG = "barang";
	public static final String TABLE_PERTANYAAN = "pertanyaan";
	public static final String TABLE_KEINGINAN = "keinginan";
	
	// definisi nama kolom setiap tabel, sudah sesuai urutan
	// yang diawali _ artinya sebagai key
	public static final String MUSEUM_ID = "_id";
	public static final String MUSEUM_NAMA = "nama";
	public static final String MUSEUM_DESKRIPSI = "deskripsi";
	public static final String MUSEUM_KOORDINAT_KIRI_ATAS = "koordinat_kiri_atas";
	public static final String MUSEUM_KOORDINAT_KANAN_BAWAH = "koordinat_kanan_bawah";
	public static final String MUSEUM_STATUS_TERKUNCI = "status_terkunci";

	public static final String RUANGAN_ID_MUSEUM = "_id_museum";
	public static final String RUANGAN_ID = "_id";
	public static final String RUANGAN_NAMA = "nama";
	public static final String RUANGAN_DESKRIPSI = "deskripsi";
	public static final String RUANGAN_STATUS_TERKUNCI = "status_terkunci";
	public static final String RUANGAN_BANYAK_PERCOBAAN_BUKA_KUNCI = "banyak_percobaan_buka_kunci";
	public static final String RUANGAN_PRIORITAS = "prioritas";

	public static final String BARANG_ID_MUSEUM = "_id_museum";
	public static final String BARANG_ID_RUANGAN = "_id_ruangan";
	public static final String BARANG_ID = "_id";
	public static final String BARANG_NAMA_BERKAS_GAMBAR = "nama_berkas_gambar";
	public static final String BARANG_NAMA = "nama";
	public static final String BARANG_DESKRIPSI = "deskripsi";
	public static final String BARANG_KATEGORI = "kategori";

	public static final String PERTANYAAN_ID_MUSEUM = "_id_museum";
	public static final String PERTANYAAN_ID_RUANGAN = "_id_ruangan";
	public static final String PERTANYAAN_ID = "_id";
	public static final String PERTANYAAN_SOAL = "soal";
	public static final String PERTANYAAN_JAWABAN = "jawaban";

	public static final String KEINGINAN_ID = "_id";
	public static final String KEINGINAN_TANGGAL = "tanggal";
	public static final String KEINGINAN_NAMA = "nama";
	public static final String KEINGINAN_EMAIL = "email";
	public static final String KEINGINAN_DESKRIPSI = "deskripsi";
	
	// definisi setiap kolom
	public static final String[] COLUMN_MUSEUM = {MUSEUM_ID, 
		                                            MUSEUM_NAMA, 
		                                            MUSEUM_DESKRIPSI, 
		                                            MUSEUM_KOORDINAT_KIRI_ATAS,
		                                            MUSEUM_KOORDINAT_KANAN_BAWAH,
		                                            MUSEUM_STATUS_TERKUNCI};
	
	public static final String[] COLUMN_RUANGAN = {RUANGAN_ID_MUSEUM,
		    										 RUANGAN_ID,
		    										 RUANGAN_NAMA,
		    										 RUANGAN_DESKRIPSI,
		    										 RUANGAN_STATUS_TERKUNCI,
		    										 RUANGAN_BANYAK_PERCOBAAN_BUKA_KUNCI,
		    										 RUANGAN_PRIORITAS};
	
	public static final String[] COLUMN_BARANG = {BARANG_ID_MUSEUM,
					  								BARANG_ID_RUANGAN,
					  								BARANG_ID,
					  								BARANG_NAMA_BERKAS_GAMBAR,
					  								BARANG_NAMA,
					  								BARANG_DESKRIPSI,
					  								BARANG_KATEGORI};
	
	public static final String[] COLUMN_PERTANYAAN = {PERTANYAAN_ID_MUSEUM,
														PERTANYAAN_ID_RUANGAN,
														PERTANYAAN_ID,
														PERTANYAAN_SOAL,
														PERTANYAAN_JAWABAN};
	
	public static final String[] COLUMN_KEINGINAN = {KEINGINAN_ID,
														KEINGINAN_TANGGAL,
														KEINGINAN_NAMA,
														KEINGINAN_EMAIL,
														KEINGINAN_DESKRIPSI};
	
	// definisi setiap pembuatan tabel
	private static final String CREATE_MUSEUM = 
			"create table " + TABLE_MUSEUM + "(" +
				MUSEUM_ID + " integer not null," +
				MUSEUM_NAMA + " text," +
				MUSEUM_DESKRIPSI + " text," +
				MUSEUM_KOORDINAT_KIRI_ATAS + " text not null," +
				MUSEUM_KOORDINAT_KANAN_BAWAH + " text not null," +
				MUSEUM_STATUS_TERKUNCI + " integer not null," +
				"PRIMARY KEY(" + MUSEUM_ID + ")" +
			");";
			
	private static final String CREATE_RUANGAN =
			"create table " + TABLE_RUANGAN + "(" +
				RUANGAN_ID_MUSEUM + " integer not null," +
				RUANGAN_ID + " integer not null," +
				RUANGAN_NAMA + " text," +
				RUANGAN_DESKRIPSI + " text," +
				RUANGAN_STATUS_TERKUNCI + " integer," +
				RUANGAN_BANYAK_PERCOBAAN_BUKA_KUNCI + " integer," +
				RUANGAN_PRIORITAS + " integer," +
				"PRIMARY KEY(" + RUANGAN_ID_MUSEUM + "," + RUANGAN_ID + ")" +
			");";
			;
			
	private static final String CREATE_BARANG =
			"create table " + TABLE_BARANG + "(" +
				BARANG_ID_MUSEUM + " integer not null," +
				BARANG_ID_RUANGAN + " integer not null," +
				BARANG_ID + " integer not null," +
				BARANG_NAMA_BERKAS_GAMBAR + " text," +
				BARANG_NAMA + " text," +
				BARANG_DESKRIPSI + " text," +
				BARANG_KATEGORI + " text," +
				"PRIMARY KEY(" + BARANG_ID_MUSEUM + "," + BARANG_ID_RUANGAN + "," + BARANG_ID + ")" +
			");";
	
	private static final String CREATE_PERTANYAAN =
			"create table " + TABLE_PERTANYAAN + "(" +
				PERTANYAAN_ID_MUSEUM + " integer not null," +
				PERTANYAAN_ID_RUANGAN + " integer not null," +
				PERTANYAAN_ID + " integer not null," +
				PERTANYAAN_SOAL + " text," +
				PERTANYAAN_JAWABAN + " text," +
				"PRIMARY KEY(" + PERTANYAAN_ID_MUSEUM + "," + PERTANYAAN_ID_RUANGAN + "," + PERTANYAAN_ID + ")" +
			");";
	
	private static final String CREATE_KEINGINAN = 
			"create table " + TABLE_KEINGINAN + "(" +
				KEINGINAN_ID + " integer not null," +
				KEINGINAN_TANGGAL + " text," +
				KEINGINAN_NAMA + " text," +
				KEINGINAN_EMAIL + " text," +
				KEINGINAN_DESKRIPSI + " text," +
				"PRIMARY KEY(" + KEINGINAN_ID + ")" +
			");";
				
	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// dipanggil ketika device belum memiliki database
		
		db.execSQL(CREATE_MUSEUM);
		db.execSQL(CREATE_RUANGAN);
		db.execSQL(CREATE_BARANG);
		db.execSQL(CREATE_PERTANYAAN);
		db.execSQL(CREATE_KEINGINAN);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// dipanggil ketika database diperbaharui (update version)
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSEUM);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RUANGAN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARANG);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERTANYAAN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_KEINGINAN);
		onCreate(db);
	}

}
