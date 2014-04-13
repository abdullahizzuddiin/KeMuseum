package com.example.kemuseum.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.kemuseum.model.Capaian;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.utils.MuseumManager;

public class ControllerCapaian {
	private MuseumManager museumManager;
	
	public ControllerCapaian() {
		museumManager = MuseumManager.getMuseumManager();		
	}
	
	public List<Capaian> getDaftarCapaian(){
		List<Museum> daftarMuseum = museumManager.getDaftarMuseum();
		List<Capaian> ret = new ArrayList<Capaian>();
		for (Museum m : daftarMuseum){
			ret.add(new Capaian(m));
		}
		return ret;
	}
}
