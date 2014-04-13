package com.example.kemuseum.model;

import java.util.ArrayList;
import java.util.List;

public class Capaian {
	private String namaMuseum;
	private float progress;
	private List<String> capaianPerRuangan;
	
	public Capaian(Museum museum){
		this.namaMuseum = museum.getNama();
		this.capaianPerRuangan = generateCapaian(museum);
	}
	
	public String getNamaMuseum(){
		return namaMuseum;
	}
	
	public int getProgress(){
		// tanpa desimal
		return (int)(progress + 0.1f);
	}
	
	public List<String> getCapaianPerRuangan(){
		return capaianPerRuangan;
	}
	
	private List<String> generateCapaian(Museum museum){
		List<String> ret = new ArrayList<String>();
		
		List<Ruangan> daftarRuangan = museum.getDaftarRuangan();
		progress = 0;
		for (Ruangan r : daftarRuangan){
			String status = "Status: " + (r.getStatusTerkunci() ? "terkunci" : "terbuka");
			String percobaan = "Banyak percobaan: " + r.getBanyakPercobaanBukaKunci() + " kali";
			
			ret.add(r.getNama() + "\n" + status + "\n" + percobaan);
			
			if (!r.getStatusTerkunci()){
				progress++;
			}
		}
		// ambil presentasenya
		progress /= daftarRuangan.size();
		progress *= 100;
		
		return ret;
	}
}
