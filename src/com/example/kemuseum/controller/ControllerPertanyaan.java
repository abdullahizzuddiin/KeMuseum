package com.example.kemuseum.controller;

import java.util.List;

import com.example.kemuseum.model.MuseumManager;
import com.example.kemuseum.model.Pertanyaan;

public class ControllerPertanyaan {
	private MuseumManager museumManager;
	
	public ControllerPertanyaan(MuseumManager museumManager){
		this.museumManager = museumManager;
	}
	
	public List<Pertanyaan> getDaftarPertanyaan(int idMuseum, int idRuangan){
		return museumManager.getDaftarPertanyaan(idMuseum, idRuangan);
	}
	
	public boolean cekJawaban(int idMuseum, int idRuangan, List<String> jawaban){
		return museumManager.cekJawaban(idMuseum, idRuangan, jawaban);
	}
}
