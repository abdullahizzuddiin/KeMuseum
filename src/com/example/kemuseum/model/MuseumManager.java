package com.example.kemuseum.model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;

public class MuseumManager {
	private List<Museum> daftarMuseum;
	private String dataDir;
	private AssetManager assetManager;
	private Context applicationContext;
	
	private boolean DEBUG_MODE = true;
	
	public MuseumManager(AssetManager assetManager, Context applicationContext){
		this.assetManager = assetManager;
		this.applicationContext = applicationContext;
		dataDir = applicationContext.getFilesDir().getAbsolutePath();
		
		// TODO loads database
		if (DEBUG_MODE){
			// simpan file museum dummy.json dari folder assets ke internal memory (dataDir)
			
		}
	}
	
	public List<Museum> getDaftarMuseum(){
		return daftarMuseum;
	}
	
	public List<Barang> getDaftarBarang(int idMuseum, int idRuangan){
		List<Barang> daftar = null;
		
		for (Museum m : daftarMuseum){
			if (m.getId() == idMuseum){
				List<Barang> temp = m.getDaftarBarang(idRuangan);
				daftar.addAll(temp);
			}
		}
		
		return daftar;
	}
	
	public void tambahMuseum(Museum museum){
		// TODO
	}
	
	public void hapusMuseum(Museum museum){
		// TODO
	}
	
	public boolean bukaKunciMuseum(int idMuseum, Koordinat koordinat){
		Museum target = null;
		
		for (Museum m : daftarMuseum){
			if (m.getId() == idMuseum){
				target = m;
				break;
			}
		}
		
		boolean sukses = false;
		if (target != null){
			if (target.cekDiDalam(koordinat)){
				sukses = true;
				
				// TODO: write to database
			}
		}
		
		return sukses;
	}
	
	public List<Pertanyaan> getDaftarPertanyaan(int idMuseum, int idRuangan){
		List<Pertanyaan> daftar = null;
		
		for (Museum m : daftarMuseum){
			if (m.getId() == idMuseum){
				daftar = m.getDaftarPertanyaan(idRuangan);
			}
		}
		
		return daftar;
	}
	
	public boolean cekJawaban(int idMuseum, int idRuangan, List<String> jawaban){
		boolean hasil = false;
		
		for (Museum m : daftarMuseum){
			if (m.getId() == idMuseum){
				hasil = m.cekJawaban(idRuangan, jawaban);
			}
		}
		
		return hasil;
	}
	
	/**
	 * Mendapatkan daftar barang sebagai hasil pencarian dengan kata kunci dan lokasi 
	 * museum tertentu.
	 * 
	 * @param kataKunci kata kunci yang diinginkan
	 * @param idMuseum nomor urut museum, -1 bila pencarian untuk semua museum yang ada di device
	 * @return daftar barang yang mengandung kataKunci sebagai nama, deskripsi, atau kategorinya
	 */
	public List<Barang> getHasilPencarian(String kataKunci, int idMuseum){
		List<Barang> daftar = new ArrayList<Barang>();
		
		for (Museum m : daftarMuseum){
			if ((idMuseum == -1) || (idMuseum == m.getId())){
				List<Barang> temp = m.cariBarang(kataKunci);
				daftar.addAll(temp);
			}
		}
		
		return daftar;
	}
}
