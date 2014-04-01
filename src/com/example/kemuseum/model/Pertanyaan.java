package com.example.kemuseum.model;

public class Pertanyaan {
	private int id;
	private String soal;
	private String jawaban;
	
	public Pertanyaan(String soal, String jawaban){
		this.soal = soal;
		this.jawaban = jawaban;
	}
	
	public String getSoal(){
		return soal;
	}
	
	public String getJawaban(){
		return jawaban;
	}
}
