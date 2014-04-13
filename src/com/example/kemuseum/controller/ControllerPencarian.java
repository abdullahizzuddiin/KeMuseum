package com.example.kemuseum.controller;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.example.kemuseum.model.Barang;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.utils.MuseumManager;

public class ControllerPencarian {
	private MuseumManager museumManager;

	public ControllerPencarian() {
		museumManager = MuseumManager.getMuseumManager();
	}

	public List<String> getDaftarNamaMuseumTerbuka() {
		List<String> ret = new ArrayList<String>();
		List<Museum> daftarMuseum = museumManager.getDaftarMuseum();

		for (Museum m : daftarMuseum) {
			if (!m.getStatusTerkunci()) {
				ret.add(m.getNama());
			}
		}

		return ret;
	}

	public List<Barang> getHasilPencarian(String kataKunci, String namaMuseum) {
		// -1, artinya untuk semua museum
		int idMuseum = -1;
		List<Museum> daftar = museumManager.getDaftarMuseum();
		for (Museum m : daftar) {
			if (m.getNama().equalsIgnoreCase(namaMuseum)) {
				idMuseum = m.getId();
				break;
			}
		}

		// perhatikan bahwa tidak masalah bila idMuseum = -1
		List<Barang> ret = museumManager.getHasilPencarian(kataKunci, idMuseum);
		return ret;
	}
}
