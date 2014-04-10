package com.example.kemuseum.controller;

import java.util.List;

import com.example.kemuseum.ViewPilihMuseum;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.utils.MuseumManager;

public class ControllerPilihMuseum {
	private MuseumManager museumManager;
	private ViewPilihMuseum viewPilihMuseum;
	
	public ControllerPilihMuseum(MuseumManager museumManager, ViewPilihMuseum viewPilihMuseum){
		this.museumManager = museumManager;
		this.viewPilihMuseum = viewPilihMuseum;
	}
	
	public List<Museum> getDaftarMuseum(){
		return museumManager.getDaftarMuseum();
	}
	
	public boolean cekStatusTerkunci(int idMuseum){
		Museum m = museumManager.getMuseum(idMuseum);
		return m.getStatusTerkunci();
	}
}
