package utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "KeMuseum.db";
	private static final int DATABASE_VERSION = 1;
/*	
	public static final String MUSEUM_ID
	public static final String MUSEUM_NAMA
	public static final String MUSEUM_DESKRIPSI
	public static final String MUSEUM_KOORDINAT_KIRI_ATAS
	public static final String MUSEUM_KOORDINAT_KANAN_BAWAH
	public static final String MUSEUM_STATUS_TERKUNCI

	public static final String RUANGAN_MUSEUM_ID
	public static final String RUANGAN_ID
	public static final String RUANGAN_NAMA
	public static final String RUANGAN_DESKRIPSI
	public static final String RUANGAN_STATUS_TERKUNCI
	public static final String RUANGAN_BANYAK_PERCOBAAN_BUKA_KUNCI
	public static final String RUANGAN_PRIORITAS

	public static final String BARANG_ID_MUSEUM
	public static final String BARANG_ID_RUANGAN
	public static final String BARANG_ID
	public static final String BARANG_NAMA_BERKAS_GAMBAR
	public static final String BARANG_NAMA
	public static final String BARANG_DESKRIPSI
	public static final String BARANG_KATEGORI
	

	public static final String PERTANYAAN_ID_MUSEUM
	public static final String PERTANYAAN_ID_RUANGAN
	public static final String PERTANYAAN_ID
	public static final String PERTANYAAN_SOAL
	public static final String PERTANYAAN_JAWABAN

	public static final String KEINGINAN_ID
	public static final String KEINGINAN_TANGGAL
	public static final String KEINGINAN_NAMA
	public static final String KEINGINAN_EMAIL
	public static final String KEINGINAN_DESKRIPSI
*/	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// dipanggil ketika device belum memiliki database
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// dipanggil ketika database diperbaharui (update version)
		
	}

}
