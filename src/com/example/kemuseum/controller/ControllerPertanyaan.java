package com.example.kemuseum.controller;

import java.util.List;

import com.example.kemuseum.model.Pertanyaan;
import com.example.kemuseum.utils.MuseumManager;

public class ControllerPertanyaan {
	private MuseumManager museumManager;
	
	public ControllerPertanyaan(){
		this.museumManager = MuseumManager.getMuseumManager();
	}
	
	public List<Pertanyaan> getDaftarPertanyaan(int idMuseum, int idRuangan){
		return museumManager.getDaftarPertanyaan(idMuseum, idRuangan);
	}
	
	//belom bener
//	public List<Pertanyaan> getDaftarJawaban(int idMuseum, int idRuangan) {
//		List<Pertanyaan> list = museumManager.getDaftarPertanyaan(idMuseum, idRuangan);
//		List<String>
//		for (Pertanyaan p : list) {
//			
//		}
//		return museumManager.getDaftarPertanyaan(idMuseum, idRuangan);
//	}
	public boolean cekJawaban(int idMuseum, int idRuangan, List<String> jawaban){
		return museumManager.cekJawaban(idMuseum, idRuangan, jawaban);
	}
}
