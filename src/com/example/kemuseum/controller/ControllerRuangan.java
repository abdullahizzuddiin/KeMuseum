package com.example.kemuseum.controller;

import java.util.List;

import com.example.kemuseum.model.Barang;
import com.example.kemuseum.utils.MuseumManager;

public class ControllerRuangan {
	private MuseumManager museumManager;
	
	public ControllerRuangan(MuseumManager museumManager){
		this.museumManager = museumManager;
	}
	
	public List<Barang> getDaftarBarang(int idMuseum, int idRuangan){
		return museumManager.getDaftarBarang(idMuseum, idRuangan);
	}
}
