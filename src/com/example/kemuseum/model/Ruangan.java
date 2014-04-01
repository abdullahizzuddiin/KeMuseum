package com.example.kemuseum.model;

import java.util.ArrayList;
import java.util.List;

public class Ruangan {
	private int id;
	private String nama;
	private String deskripsi;
	private int prioritas;
	private boolean statusTerkunci;
	private List<Barang> daftarBarang;
	private List<Pertanyaan> daftarPertanyaan;
	
	public Ruangan(int id, String nama, String deskripsi, 
			        int prioritas, boolean statusTerkunci){
		this.id = id;
		this.nama = nama;
		this.deskripsi = deskripsi;
		this.prioritas = prioritas;
		this.statusTerkunci = statusTerkunci;
		daftarBarang = new ArrayList<Barang>();
		daftarPertanyaan = new ArrayList<Pertanyaan>();
	}
	
	public int getId(){
		return id;
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
	
	public List<Barang> getDaftarBarang(){
		return daftarBarang;
	}
	
	public List<Pertanyaan> getDaftarPertanyaan(){
		return daftarPertanyaan;
	}
	
	public void tambahBarang(Barang barang){
		daftarBarang.add(barang);
	}
	
	public void tambahPertanyaan(Pertanyaan pertanyaan){
		daftarPertanyaan.add(pertanyaan);
	}
	
	public void setStatusTerkunci(boolean nilai){
		statusTerkunci = nilai;
	}
	
	public boolean cekJawaban(List<String> jawaban){
		boolean benarSemua = true;
		for (int i = 0; i < daftarPertanyaan.size(); i++){
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
			
		}
		
		return ret;
	}
}
