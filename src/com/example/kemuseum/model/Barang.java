package com.example.kemuseum.model;

import android.annotation.SuppressLint;
import java.util.List;

public class Barang {
	// nomor id barang
	private int id;
	
	// nama berkas gambar pada folder assets
	private String namaBerkasGambar;
	
	// nama dan deskripsi barang
	private String nama;
	private String deskripsi;
	
	// kategori barang, dipisahkan koma. Contoh: "kesenian,kebudayaan,majapahit,kalung"
	private String kategori;
	
	public Barang(int id, String namaBerkasGambar, String nama, String deskripsi, String kategori){
		this.id = id;
		this.namaBerkasGambar = namaBerkasGambar;
		this.nama = nama;
		this.deskripsi = deskripsi;
		this.kategori = kategori;
	}
	
	public int getId(){
		return id;
	}
	
	public String getNamaBerkasGambar(){
		return namaBerkasGambar;
	}
	
	public String getNama(){
		return nama;
	}
	
	public String getDeksipsi(){
		return deskripsi;
	}
	
	public boolean mengandungKataKunci(String kataKunci){
		boolean ada = false;
		
		// semua pemrosesan dalam lower case
		String lcKunci = kataKunci.toLowerCase();
		
		ada = nama.toLowerCase().contains(lcKunci) 
		   || deskripsi.toLowerCase().contains(lcKunci)
		   || kategori.toLowerCase().contains(lcKunci);
		
		return ada;
	}
}
