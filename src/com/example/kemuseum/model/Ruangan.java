package com.example.kemuseum.model;

import java.util.ArrayList;
import java.util.List;

public class Ruangan {
	private int id;
	private int idMuseum;
	private String nama;
	private String deskripsi;
	private int prioritas;
	private int banyakPercobaanBukaKunci;
	private boolean statusTerkunci;
	private List<Barang> daftarBarang;
	private List<Pertanyaan> daftarPertanyaan;
	private int warna;
	
	public Ruangan(int idMuseum, int id, String nama, String deskripsi, 
			        boolean statusTerkunci, int banyakPercobaanBukaKunci, int prioritas, int warna,
			        List<Barang> daftarBarang, List<Pertanyaan> daftarPertanyaan){
		this.idMuseum = idMuseum;
		this.id = id;
		this.nama = nama;
		this.deskripsi = deskripsi;
		this.statusTerkunci = statusTerkunci;
		this.banyakPercobaanBukaKunci = banyakPercobaanBukaKunci;
		this.prioritas = prioritas;
		this.warna = warna;
		this.daftarBarang = daftarBarang;
		this.daftarPertanyaan = daftarPertanyaan;
	}
	
	public int getId(){
		return id;
	}
	
	public int getIdMuseum(){
		return idMuseum;
	}
	
	public int getWarna(){
		return warna;
	}
	
	public String getNama(){
		return nama;
	}
	
	public String getDeskripsi(){
		return deskripsi;
	}
	
	public int getPrioritas(){
		return prioritas;
	}
	
	public boolean getStatusTerkunci(){
		return statusTerkunci;
	}
	
	public int getBanyakPercobaanBukaKunci(){
		return banyakPercobaanBukaKunci;
	}
	
	public List<Barang> getDaftarBarang(){
		return daftarBarang;
	}
	
	public List<Pertanyaan> getDaftarPertanyaan(){
		return daftarPertanyaan;
	}
	
//	public List<Pertanyaan> getDaftarJawaban(){
//		List<Pertanyaan> daftarJawaban = null;
//		
//		for (int i = 0; i < daftarPertanyaan.size(); i++){ 
//			Pertanyaan p = daftarPertanyaan.get(i);
//			daftarJawaban.add(p.getJawaban());
//		}
//		return daftarJawaban;
//	}
	public void setStatusTerkunci(boolean nilai){
		statusTerkunci = nilai;
	}
	
	public void setBanyakPercobaanBukaKunci(int nilai){
		banyakPercobaanBukaKunci = nilai;
	}
	
	public boolean cekJawaban(List<String> jawaban){
		boolean benarSemua = true;
		for (int i = 0; i < daftarPertanyaan.size(); i++){
			// cek kebenaran untuk setiap pertanyaan i dan jawaban i 
			Pertanyaan p = daftarPertanyaan.get(i);
			if (!p.getJawaban().equals(jawaban.get(i))){
				benarSemua = false;
				break;
			}
		}
		return benarSemua;
	}
	
	public List<Barang> cariBarang(String kataKunci){
		List<Barang> ret = new ArrayList<Barang>();
		
		for (Barang b : daftarBarang){
			if (b.mengandungKataKunci(kataKunci)){
				ret.add(b);
			}
		}
		
		return ret;
	}
}
