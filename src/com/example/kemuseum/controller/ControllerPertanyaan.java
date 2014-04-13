package com.example.kemuseum.controller;

import java.util.List;

import android.util.Log;

<<<<<<< HEAD
=======
import com.example.kemuseum.model.Barang;
>>>>>>> 665ffad7529ea91459db3463954a2f9774abd25f
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
	
	public boolean cekJawaban(int idMuseum, int idRuangan, List<String> jawaban){
		return museumManager.cekJawaban(idMuseum, idRuangan, jawaban);
	}
}
