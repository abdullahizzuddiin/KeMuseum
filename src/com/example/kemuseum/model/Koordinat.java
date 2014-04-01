package com.example.kemuseum.model;

public class Koordinat {
	private float latitude;
	private float longitude;
	
	public Koordinat(float latitude, float longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public float getLatitude(){
		return latitude;
	}
	
	public float getLongitude(){
		return longitude;
	}
	
	public boolean diDalam(Koordinat titikSudut1, Koordinat titikSudut2){
		float kiri = Math.min(titikSudut1.getLongitude(), titikSudut2.getLongitude());
		float kanan = Math.max(titikSudut1.getLongitude(), titikSudut2.getLongitude());
		float atas = Math.min(titikSudut1.getLatitude() , titikSudut2.getLatitude());
		float bawah = Math.max(titikSudut1.getLatitude() , titikSudut2.getLatitude());
		
		return (kiri < latitude) && (latitude < kanan)
			 && (atas < longitude) && (longitude < bawah);
	}
}
