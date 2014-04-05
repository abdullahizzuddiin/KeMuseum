package com.example.kemuseum.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;

import com.example.kemuseum.model.Barang;
import com.example.kemuseum.model.Keinginan;
import com.example.kemuseum.model.Koordinat;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.model.Pertanyaan;
import com.example.kemuseum.model.Ruangan;

public class JSONParser {
	// definisi nama kolom setiap tabel, sudah sesuai urutan
	// yang diawali _ artinya sebagai key
	public static final String MUSEUM_ID = "_id";
	public static final String MUSEUM_NAMA = "nama";
	public static final String MUSEUM_DESKRIPSI = "deskripsi";
	public static final String MUSEUM_KOORDINAT_KIRI_ATAS = "koordinat_kiri_atas";
	public static final String MUSEUM_KOORDINAT_KANAN_BAWAH = "koordinat_kanan_bawah";
	public static final String MUSEUM_STATUS_TERKUNCI = "status_terkunci";
	public static final String MUSEUM_DAFTAR_RUANGAN = "daftar_ruangan";

	public static final String RUANGAN_ID_MUSEUM = "_id_museum";
	public static final String RUANGAN_ID = "_id";
	public static final String RUANGAN_NAMA = "nama";
	public static final String RUANGAN_DESKRIPSI = "deskripsi";
	public static final String RUANGAN_STATUS_TERKUNCI = "status_terkunci";
	public static final String RUANGAN_BANYAK_PERCOBAAN_BUKA_KUNCI = "banyak_percobaan_buka_kunci";
	public static final String RUANGAN_PRIORITAS = "prioritas";
	public static final String RUANGAN_DAFTAR_BARANG = "daftar_barang";
	public static final String RUANGAN_DAFTAR_PERTANYAAN = "daftar_pertanyaan";

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
	
	public static Museum toMuseum(String museumJSON){
		Museum m = null;
		try{
			m = toMuseum(new JSONObject(museumJSON));
		}catch (JSONException e){
		}
		return m;
	}
	
	public static Ruangan toRuangan(String ruanganJSON){
		Ruangan r = null;
		try{
			r = toRuangan(new JSONObject(ruanganJSON));
		}catch (JSONException e){
		}
		return r;
	}
	
	public static Barang toBarang(String barangJSON){
		Barang b = null;
		try{
			b = toBarang(new JSONObject(barangJSON));
		}catch (JSONException e){
		}
		return b;
	}
	
	public static Pertanyaan toPertanyaan(String pertanyaanJSON){
		Pertanyaan p = null;
		try{
			p = toPertanyaan(new JSONObject(pertanyaanJSON));
		}catch (JSONException e){
		}
		return p;
	}
	
	public static Keinginan toKeinginan(String keinginanJSON){
		Keinginan k = null;
		try{
			k = toKeinginan(new JSONObject(keinginanJSON));
		}catch (JSONException e){
		}
		return k;
	}
	
	private static Museum toMuseum(JSONObject obj) throws JSONException{
		// berurutan
		int id = obj.getInt(MUSEUM_ID);
		String nama = obj.getString(MUSEUM_NAMA);
		String deskripsi = obj.getString(MUSEUM_DESKRIPSI);
		Koordinat koordinatKiriAtas = new Koordinat(obj.getString(MUSEUM_KOORDINAT_KIRI_ATAS));
		Koordinat koordinatKananBawah = new Koordinat(obj.getString(MUSEUM_KOORDINAT_KANAN_BAWAH));
		boolean statusTerkunci = obj.getBoolean(MUSEUM_STATUS_TERKUNCI);
		
		List<Ruangan> daftarRuangan = new ArrayList<Ruangan>();
		
		JSONArray daftarRuanganJSON = new JSONArray(obj.get(MUSEUM_DAFTAR_RUANGAN).toString());
		for (int i = 0; i < daftarRuanganJSON.length(); i++){
			JSONObject ruanganJSON = daftarRuanganJSON.getJSONObject(i);
			daftarRuangan.add(toRuangan(ruanganJSON));
		}
		
		Museum m = new Museum(id, nama, deskripsi, koordinatKiriAtas, koordinatKananBawah, 
		               statusTerkunci, daftarRuangan);
		
		return m;
	}
	
	private static Ruangan toRuangan(JSONObject obj) throws JSONException {		
		int idMuseum = obj.getInt(RUANGAN_ID_MUSEUM);
		int id = obj.getInt(RUANGAN_ID);
		String nama = obj.getString(RUANGAN_NAMA);
		String deskripsi = obj.getString(RUANGAN_DESKRIPSI);
		boolean statusTerkunci = obj.getBoolean(RUANGAN_STATUS_TERKUNCI);
		int banyakPercobaanBukaKunci = obj.getInt(RUANGAN_BANYAK_PERCOBAAN_BUKA_KUNCI);
		int prioritas = obj.getInt(RUANGAN_PRIORITAS);
		
		List<Barang> daftarBarang = new ArrayList<Barang>();
		JSONArray daftarBarangJSON = new JSONArray(obj.get(RUANGAN_DAFTAR_BARANG).toString());
		for (int i = 0; i < daftarBarangJSON.length(); i++){
			JSONObject barangJSON = daftarBarangJSON.getJSONObject(i);
			daftarBarang.add(toBarang(barangJSON));
		}
		
		List<Pertanyaan> daftarPertanyaan = new ArrayList<Pertanyaan>();
		JSONArray daftarPertanyaanJSON = new JSONArray(obj.get(RUANGAN_DAFTAR_PERTANYAAN).toString());
		for (int i = 0; i < daftarPertanyaanJSON.length(); i++){
			JSONObject pertanyaanJSON = daftarPertanyaanJSON.getJSONObject(i);
			daftarPertanyaan.add(toPertanyaan(pertanyaanJSON));
		}
		
		Ruangan r = new Ruangan(idMuseum, id, nama, deskripsi, 
				                 statusTerkunci, banyakPercobaanBukaKunci, prioritas, 
				                 daftarBarang, daftarPertanyaan);
				
		return r;
	}
	
	private static Barang toBarang(JSONObject obj) throws JSONException{
		int idMuseum = obj.getInt(BARANG_ID_MUSEUM);
		int idRuangan = obj.getInt(BARANG_ID_RUANGAN);
		int id = obj.getInt(BARANG_ID);
		String namaBerkasGambar = obj.getString(BARANG_NAMA_BERKAS_GAMBAR);
		String nama = obj.getString(BARANG_NAMA);
		String deskripsi = obj.getString(BARANG_DESKRIPSI);
		String kategori = obj.getString(BARANG_KATEGORI);
		
		Barang b = new Barang(idMuseum, idRuangan, id, namaBerkasGambar, nama, deskripsi, kategori);
		
		return b;
	}
	
	private static Pertanyaan toPertanyaan(JSONObject obj) throws JSONException{
		int idMuseum = obj.getInt(PERTANYAAN_ID_MUSEUM);
		int idRuangan = obj.getInt(PERTANYAAN_ID_RUANGAN);
		int id = obj.getInt(PERTANYAAN_ID);
		String soal = obj.getString(PERTANYAAN_SOAL);
		String jawaban = obj.getString(PERTANYAAN_JAWABAN);
		
		Pertanyaan pp = new Pertanyaan(idMuseum, idRuangan, id, soal, jawaban);
		
		return pp;
	}
	
	private static Keinginan toKeinginan(JSONObject obj) throws JSONException{
		int id = obj.getInt(KEINGINAN_ID);
		String tanggal = obj.getString(KEINGINAN_TANGGAL);
		String nama = obj.getString(KEINGINAN_NAMA);
		String email = obj.getString(KEINGINAN_EMAIL);
		String deskripsi = obj.getString(KEINGINAN_DESKRIPSI);
		
		Keinginan k = new Keinginan(id, tanggal, nama, email, deskripsi);
		
		return k;
	}
}
