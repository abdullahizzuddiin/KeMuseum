package com.example.kemuseum.model;

import java.util.List;

public class Barang {
	private int id;
	private String namaBerkasGambar;
	private String nama;
	private String deskripsi;
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
	
}
