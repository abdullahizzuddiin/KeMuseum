package com.example.kemuseum.model;

public class Pertanyaan {
	private int id;
	private int idRuangan;
	private int idMuseum;
	private String soal;
	private String jawaban;
	
	public Pertanyaan(int idMuseum, int idRuangan, int id, String soal, String jawaban){
		this.id = id;
		this.idRuangan = idRuangan;
		this.idMuseum = idMuseum;
		this.soal = soal;
		this.jawaban = jawaban;
	}
	
	public int getId(){
		return id;
	}
	
	public int getIdRuangan(){
		return idRuangan;
	}
	
	public int getIdMuseum(){
		return idMuseum;
	}
	
	public String getSoal(){
		return soal;
	}
	
	public String getJawaban(){
		return jawaban;
	}
}
