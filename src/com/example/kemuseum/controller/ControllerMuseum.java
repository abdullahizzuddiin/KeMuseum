package com.example.kemuseum.controller;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.kemuseum.model.Koordinat;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.model.Ruangan;
import com.example.kemuseum.utils.MuseumManager;

public class ControllerMuseum {
	private MuseumManager museumManager;
	private LocationManager locationManager;
	private Location lokasiTerakhir;
	
	private final long TENGGANG_WAKTU = 0; // secepatnya!
	private final float TENGGANG_JARAK = 0; // seakurat mungkin!

	public ControllerMuseum() {
		museumManager = MuseumManager.getMuseumManager();
		locationManager = (LocationManager) museumManager.getContext()
				.getSystemService(Context.LOCATION_SERVICE);
		
		LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location lokasi) {
				lokasiTerakhir = lokasi;
				Log.d("asd", "gan lokasi terakhir: " + lokasiTerakhir.getLatitude() + " " + lokasiTerakhir.getLongitude());
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};

		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, TENGGANG_WAKTU,
				TENGGANG_JARAK, locationListener);
	}

	private Koordinat getKoordinatPengguna() {
		Koordinat ret = null;
		
		// busy waiting
		int cnt = 0;
		while (lokasiTerakhir == null){
			cnt++;
			if (cnt > 200000000){
				// DEBUG
				Log.d("asd", "gan digunakan koordinat default");
				return new Koordinat(1f, 1f);
			}
		}
		
		ret = new Koordinat((float) lokasiTerakhir.getLatitude(),
				(float) lokasiTerakhir.getLongitude());
		return ret;
	}

	public boolean bukaKunciMuseum(int idMuseum) {
		Koordinat posisi = this.getKoordinatPengguna();
		boolean hasil = museumManager.bukaKunciMuseum(idMuseum, posisi);
	
		return hasil;
	}

	public List<Ruangan> getDaftarRuangan(int idMuseum) {
		Museum m = museumManager.getMuseum(idMuseum);
		return m.getDaftarRuangan();
	}
	
	
	public Museum getMuseum(int idMuseum) {
		return museumManager.getMuseum(idMuseum);
	}

	public Drawable getGambarMuseum(int idMuseum) {
		Museum m = museumManager.getMuseum(idMuseum);
		Drawable ret = museumManager.getDrawableImage(idMuseum, m.getNamaBerkasGambarMuseum());
	
		return ret;
	}

	public Drawable getGambarDenah(int idMuseum) {
		Museum m = museumManager.getMuseum(idMuseum);
		Drawable ret = museumManager.getDrawableImage(idMuseum, m.getNamaBerkasGambarDenah());
	
		return ret;
	}

	public Ruangan getRuanganDariWarna(int idMuseum, int warna) {
		Museum m = museumManager.getMuseum(idMuseum);
		
		return m.getRuanganDariWarna(warna);
	}
}
