package com.example.kemuseum.controller;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.example.kemuseum.model.MetaMuseum;
import com.example.kemuseum.utils.JSONParser;
import com.example.kemuseum.utils.MuseumManager;

public class ControllerUnduhMuseum {
	private final int BUFFER_SIZE = 1<<15;
	private final String BASE_URL = "http://majapahit.cs.ui.ac.id/cp2013/";
	private MuseumManager museumManager;

	public ControllerUnduhMuseum(){
		museumManager = MuseumManager.getMuseumManager();
	}
	
	public List<MetaMuseum> getDaftarSemuaMuseum(){
		List<MetaMuseum> ret = new ArrayList<MetaMuseum>();
		
		try {
			URL url = new URL(BASE_URL + "daftarMuseum.txt");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);

			// connect
			urlConnection.connect();

			// Stream byte
			InputStream inputStream = urlConnection.getInputStream();
					
			int size = urlConnection.getContentLength();
			byte[] buffer = new byte[BUFFER_SIZE];
			
			int v;
			int p = 0;
			while ((v = inputStream.read()) != -1){
				buffer[p++] = (byte)v;
			}
			
			String s = new String(buffer);
			JSONArray daftar = new JSONArray(s);
			for (int i = 0; i < daftar.length(); i++){
				JSONObject memObject = daftar.getJSONObject(i);
				
				MetaMuseum mem = JSONParser.toMetaMuseum(memObject);				
				mem.setSudahDimiliki(museumManager.cekMuseumSudahDimiliki(mem.getId()));
				ret.add(mem);
			}
			
		} catch (final Exception e) {
			Log.d("asd", "gan " + e.toString());
		}
		
		return ret;
	}
}
