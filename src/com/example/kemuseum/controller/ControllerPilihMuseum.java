package com.example.kemuseum.controller;

import java.util.List;

import com.example.kemuseum.ViewPilihMuseum;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.utils.MuseumManager;

public class ControllerPilihMuseum {
	private MuseumManager museumManager;
	
	public ControllerPilihMuseum(){
		museumManager = MuseumManager.getMuseumManager();
	}
	
	public List<Museum> getDaftarMuseum(){
		return museumManager.getDaftarMuseum();
	}
	
	public boolean cekStatusTerkunci(int idMuseum){
		Museum m = museumManager.getMuseum(idMuseum);
		return m.getStatusTerkunci();
	}
}
