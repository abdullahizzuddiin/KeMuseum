package com.example.kemuseum.controller;

import java.util.List;

import com.example.kemuseum.model.Koordinat;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.model.MuseumManager;
import com.example.kemuseum.model.Ruangan;

public class ControllerMuseum {
	private MuseumManager museumManager;
	
	private Koordinat getKoordinatPengguna(){
		// TODO
		return null;
	}
	
	public boolean bukaKunciMuseum(int idMuseum){
		Koordinat posisi = this.getKoordinatPengguna();
		return museumManager.bukaKunciMuseum(idMuseum, posisi);
	}
	
	public List<Ruangan> getDaftarRuangan(int idMuseum){
		Museum m = museumManager.getMuseum(idMuseum);
		return m.getDaftarRuangan();
	}
}
