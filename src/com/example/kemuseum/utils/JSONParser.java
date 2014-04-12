package com.example.kemuseum.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.kemuseum.model.Barang;
import com.example.kemuseum.model.Keinginan;
import com.example.kemuseum.model.Koordinat;
import com.example.kemuseum.model.MetaMuseum;
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
	
	public static final String META_MUSEUM_ID = "_id";
	public static final String META_MUSEUM_NAMA = "nama";
	public static final String META_MUSEUM_DESKRIPSI = "deskripsi";
	public static final String META_MUSEUM_SUDAH_DIMILIKI = "sudah_dimiliki";
	
	/**
	 * Diberikan string JSON, return objek Museum dari JSON itu
	 * 
	 * @param museumJSON string yang merepresentasikan JSON-nya
	 * @return objek Museum yang bersangkutan, dan null bila JSON tidak valid
	 */
	public static Museum toMuseum(String museumJSON){
		Museum m = null;
		try{
			m = toMuseum(new JSONObject(museumJSON));
		}catch (JSONException e){
			Log.d("JSONParser", "gan string -> json bermasalah:" + museumJSON);
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
	
	/**
	 * Diberikan objek JSON, mengembalikan objek konkrit Museum
	 * @param obj objek JSON
	 * @return objek Museum yang bersangkutan
	 * @throws JSONException bila JSON tidak valid
	 */
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
	
	public static MetaMuseum toMetaMuseum(JSONObject obj) throws JSONException{
		int id = obj.getInt(META_MUSEUM_ID);
		String nama = obj.getString(META_MUSEUM_NAMA);
		String deskripsi = obj.getString(META_MUSEUM_DESKRIPSI);
	
		boolean status = false;
		if (obj.has(META_MUSEUM_SUDAH_DIMILIKI)){
			obj.getBoolean(META_MUSEUM_SUDAH_DIMILIKI);
		}
		
		MetaMuseum mem = new MetaMuseum(id, nama, deskripsi, status);
		
		return mem;
	}
	
	/**
	 * Diberikan sebuah objek Museum, mengembalikan string JSON untuk objek tersebut
	 * 
	 * @param m Objek Museum yang ingin dibuat JSON-nya
	 * @return string JSON dari objek yang bersangkutan, dan string kosong bila ada masalah
	 */
	public static String toJSON(Museum m){
		String s = "";
		
		try {
			JSONObject obj = new JSONObject();
			obj.put(MUSEUM_ID, m.getId());
			obj.put(MUSEUM_NAMA, m.getNama());
			obj.put(MUSEUM_DESKRIPSI, m.getDeskripsi());
			obj.put(MUSEUM_KOORDINAT_KIRI_ATAS, m.getStringKoordinatKiriAtas());
			obj.put(MUSEUM_KOORDINAT_KANAN_BAWAH, m.getStringKoordinatKananBawah());
			obj.put(MUSEUM_STATUS_TERKUNCI, m.getStatusTerkunci());
			
			JSONArray daftarRuanganJSON = new JSONArray();
			List<Ruangan> daftarRuangan = m.getDaftarRuangan();
			for (Ruangan r : daftarRuangan){
				JSONObject rj = new JSONObject(toJSON(r));
				daftarRuanganJSON.put(rj);
			}
			obj.put(MUSEUM_DAFTAR_RUANGAN, daftarRuanganJSON);
			
			s = obj.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		return s;
	}
	
	public static String toJSON(Ruangan r){
		String s = "";
		
		try {
			JSONObject obj = new JSONObject();
			obj.put(RUANGAN_ID_MUSEUM, r.getIdMuseum());
			obj.put(RUANGAN_ID, r.getId());
			obj.put(RUANGAN_NAMA, r.getNama());
			obj.put(RUANGAN_DESKRIPSI, r.getDeskripsi());
			obj.put(RUANGAN_STATUS_TERKUNCI, r.getStatusTerkunci());
			obj.put(RUANGAN_BANYAK_PERCOBAAN_BUKA_KUNCI, r.getBanyakPercobaanBukaKunci());
			obj.put(RUANGAN_PRIORITAS, r.getPrioritas());
			
			JSONArray daftarBarangJSON = new JSONArray();
			List<Barang> daftarBarang = r.getDaftarBarang();
			for (Barang b : daftarBarang){
				JSONObject bj = new JSONObject(toJSON(b));
				daftarBarangJSON.put(bj);
			}
			obj.put(RUANGAN_DAFTAR_BARANG, daftarBarangJSON);
			
			JSONArray daftarPertanyaanJSON = new JSONArray();
			List<Pertanyaan> daftarPertanyaan = r.getDaftarPertanyaan();
			for (Pertanyaan p : daftarPertanyaan){
				JSONObject pj = new JSONObject(toJSON(p));
				daftarPertanyaanJSON.put(pj);
			}
			obj.put(RUANGAN_DAFTAR_PERTANYAAN, daftarPertanyaanJSON);
			
			s = obj.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		return s;		
	}
	
	public static String toJSON(Barang b){
		String s = "";
		
		try {
			JSONObject obj = new JSONObject();
			obj.put(BARANG_ID_MUSEUM, b.getIdMuseum());
			obj.put(BARANG_ID_RUANGAN, b.getIdRuangan());
			obj.put(BARANG_ID, b.getId());
			obj.put(BARANG_NAMA_BERKAS_GAMBAR, b.getNamaBerkasGambar());
			obj.put(BARANG_NAMA, b.getNama());
			obj.put(BARANG_DESKRIPSI, b.getDeksipsi());
			obj.put(BARANG_KATEGORI, b.getKategori());
			
			s = obj.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		return s;
	}
	
	public static String toJSON(Pertanyaan p){
		String s = "";
		
		try {
			JSONObject obj = new JSONObject();
			obj.put(PERTANYAAN_ID_MUSEUM, p.getIdMuseum());
			obj.put(PERTANYAAN_ID_RUANGAN, p.getIdRuangan());
			obj.put(PERTANYAAN_ID, p.getId());
			obj.put(PERTANYAAN_SOAL, p.getSoal());
			obj.put(PERTANYAAN_JAWABAN, p.getJawaban());
			
			s = obj.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		return s;
	}
	
	public static String toJSON(Keinginan k){
		String s = "";
		
		try {
			JSONObject obj = new JSONObject();
			obj.put(KEINGINAN_ID, k.getId());
			obj.put(KEINGINAN_TANGGAL, k.getTanggal());
			obj.put(KEINGINAN_NAMA, k.getNama());
			obj.put(KEINGINAN_EMAIL, k.getEmail());
			obj.put(KEINGINAN_DESKRIPSI, k.getDeskripsi());
			
			s = obj.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		return s;
	}
}
