package com.example.kemuseum.controller;

import java.util.List;

import com.example.kemuseum.model.Museum;
import com.example.kemuseum.utils.MuseumManager;

public class ControllerHapusMuseum {
	private MuseumManager museumManager;
	
	public ControllerHapusMuseum(){
		museumManager = MuseumManager.getMuseumManager();
	}
	
	public List<Museum> getDaftarMuseum(){
		return museumManager.getDaftarMuseum();
	}
	
	public void hapusMuseum(Museum m){
		museumManager.hapusMuseum(m);
	}
}
