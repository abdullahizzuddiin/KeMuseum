package com.example.kemuseum.model;

import java.util.ArrayList;
import java.util.List;

public class MetaMuseum {
	private int id;
	private String nama;
	private String deskripsi;
	private boolean sudahDimiliki;
	
	public MetaMuseum(int id, String nama, String deskripsi, boolean sudahDimiliki){
		this.id = id;
		this.nama = nama;
		this.deskripsi = deskripsi;
		this.sudahDimiliki = sudahDimiliki;
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
	
	public boolean getSudahDilimiki(){
		return sudahDimiliki;
	}
	
	public void setSudahDimiliki(boolean nilai){
		sudahDimiliki = nilai;
	}
}
