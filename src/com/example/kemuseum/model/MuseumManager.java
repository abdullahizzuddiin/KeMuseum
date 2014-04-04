package com.example.kemuseum.model;

import java.util.ArrayList;
import java.util.List;

public class MuseumManager {
	private List<Museum> daftarMuseum;
	
	public MuseumManager(){
		daftarMuseum = new ArrayList<Museum>();
		
		// TODO loads database
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
