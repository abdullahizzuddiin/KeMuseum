// http://ristekfasilkom.com/wp-content/uploads/2014/04/pahe.zip
// http://ristekfasilkom.com/wp-content/uploads/2014/04/1.zip
// http://ristekfasilkom.com/wp-content/uploads/2014/04/daftarMuseum.txt

package com.example.kemuseum.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.example.kemuseum.model.Museum;
import com.example.kemuseum.utils.JSONParser;
import com.example.kemuseum.utils.MuseumManager;

public class ControllerUnduhMuseum {
	private final int BUFFER_SIZE = 1 << 15;
	private final String BASE_URL = "http://ristekfasilkom.com/wp-content/uploads/2014/05/";
	private MuseumManager museumManager;

	public ControllerUnduhMuseum() {
		museumManager = MuseumManager.getMuseumManager();
	}

	public List<Museum> getDaftarSemuaMuseum() {
		List<Museum> ret = new ArrayList<Museum>();

		try {
			URL url = new URL(BASE_URL + "deskripsiMuseum.txt");
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();

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
			while ((v = inputStream.read()) != -1) {
				buffer[p++] = (byte) v;
			}
			
			String s = new String(buffer);
			JSONArray daftar = new JSONArray(s);
			for (int i = 0; i < daftar.length(); i++) {
				JSONObject memObject = daftar.getJSONObject(i);

				Museum m = JSONParser.toMuseum(memObject.toString());
				ret.add(m);
			}
			
			Log.d("asd", "gan " + s);
			
		} catch (final Exception e) {
			Log.d("asd", "gan " + e.toString());
		}

		return ret;
	}

	public Museum unduhMuseum(Museum meta){
		try{
			String json = this.bacaBerkas(meta.getId() + ".txt");
			unduhDanEkstrakBerkas(meta.getId(), meta.getId() + ".zip");

			Museum m = JSONParser.toMuseum(json);
			museumManager.tambahMuseum(m);
			
			Log.d("asd", "gan " + m.getNamaBerkasGambarMuseum() + " " + m.getNamaBerkasGambarDenah());
			
		}catch (Exception e){
			Log.d("asd", "gan error pas mau unduh! " + e.toString());
		}
		return null;
	}
	
	public String bacaBerkas(String namaBerkas) throws Exception {
		URL url = new URL(BASE_URL + namaBerkas);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();

		urlConnection.setRequestMethod("GET");
		urlConnection.setDoOutput(true);
		urlConnection.connect();

		Log.d("asd", "gan siap2 download " + namaBerkas);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		InputStream is = urlConnection.getInputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		
		int nRead;		
		while ((nRead = is.read(buffer, 0, buffer.length)) != -1) {
			bos.write(buffer, 0, nRead);
			bos.flush();
		}
		bos.close();

		String ret = new String(bos.toByteArray());
		return ret;
	}

	public void unduhDanEkstrakBerkas(int idMuseum, String namaBerkas) throws Exception {
		URL url = new URL(BASE_URL + namaBerkas);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();

		urlConnection.setRequestMethod("GET");
		urlConnection.setDoOutput(true);
		urlConnection.connect();

		Log.d("asd", "gan siap2 download & extract" + namaBerkas);

		InputStream is = urlConnection.getInputStream();
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is));
		byte[] buffer = new byte[BUFFER_SIZE];

		ZipEntry ze;
		int count;
		int p = 0;

		while ((ze = zis.getNextEntry()) != null) {
			namaBerkas = ze.getName();

			if (ze.isDirectory()) {
				File fmd = new File(museumManager.getFolderGambar(idMuseum).getAbsoluteFile(), namaBerkas);
				fmd.mkdirs();
				continue;
			}

			File f = new File(museumManager.getFolderGambar(idMuseum).getAbsoluteFile(), namaBerkas);
			FileOutputStream fos = new FileOutputStream(f);
			while ((count = zis.read(buffer)) != -1) {
				fos.write(buffer, 0, count);
			}
			
			p++;
			if (p % 50 == 0){
				Log.d("asd", "gan jadi ada " + f.getAbsolutePath());
			}
			fos.close();
			zis.closeEntry();
		}

		zis.close();
	}

	public boolean sudahDimiliki(Museum item) {
		return museumManager.cekMuseumSudahDimiliki(item.getId());
	}
}
