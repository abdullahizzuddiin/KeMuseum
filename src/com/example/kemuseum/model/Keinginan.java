package com.example.kemuseum.model;

import java.util.Date;

public class Keinginan {
	private int id;
	private String tanggal;
	private String nama;
	private String email;
	private String deskripsi;
	
	public Keinginan(int id, String tanggal, String nama, String email, String deskripsi){
		this.id = id;
		this.tanggal = tanggal;
		this.nama = nama;
		this.email = email;
		this.deskripsi = deskripsi;
	}
	
	public int getId(){
		return id;
	}
	
	public String getTanggal(){
		return tanggal;
	}
	
	public String getNama(){
		return nama;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getDeskripsi(){
		return deskripsi;
	}
}
