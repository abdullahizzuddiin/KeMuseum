package com.example.kemuseum.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.example.kemuseum.model.Wishlist;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class WishlistManager {
	private static WishlistManager instance;
	private AssetManager assetManager;
	private Context applicationContext;
	private List<Wishlist> daftarKeinginan;

	private boolean DEBUG_MODE = false;
	private int BUFFER_SIZE = 2048;
	private String dataDir;

	public static void createWishlistManager(AssetManager assetManager,
			Context applicationContext) {
		if (instance == null) {
			instance = new WishlistManager(assetManager, applicationContext);
		}
	}

	private WishlistManager(AssetManager assetManager,
			Context applicationContext) {
		this.assetManager = assetManager;
		this.applicationContext = applicationContext;

		// mencari lokasi tempat memyimpan jsonnya
		dataDir = applicationContext.getFilesDir().getAbsolutePath();

		// Load Databasenya
		daftarKeinginan = new ArrayList<Wishlist>();
		File keinginanJSON = new File(dataDir);
		String[] jsonList = keinginanJSON.list();
		for (String js : jsonList) {
			// ini museum, lewatin aja
			if (!js.contains("json")) continue;
			
			Log.d("MuseumManager", "gan wishlist parsing " + js);
			
			try {
				File f = new File(dataDir, js);
				InputStream is = new BufferedInputStream(new FileInputStream(f));
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();

				int nRead;
				byte[] data = new byte[BUFFER_SIZE];
				while ((nRead = is.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, nRead);
				}
				buffer.flush();

				String json = buffer.toString();
				Wishlist w = JSONParser.toKeinginan(json);
				Log.d("asd", "gan wishlist buka " + json);
				daftarKeinginan.add(w);
			} catch (Exception e) {
				// TODO: handle exception
				Log.d("WishlistManager", "gan wishlist error parsing " + js);
			}
		}

	}

	public static WishlistManager getWishlistManager() {
		if (instance == null) {
			Log.w("warning", "WishlistManager kosong");
		}
		return instance;
	}

	public void tambahWishlist(Wishlist w) {
		try {
			String fileName = "json" + w.getId();
			File wishlistJSON = new File(dataDir, fileName);
			FileOutputStream fos = new FileOutputStream(
					wishlistJSON.getAbsolutePath());
			
			fos.write(JSONParser.toJSON(w).getBytes());
			fos.close();

			daftarKeinginan.add(w);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public List<Wishlist> getDaftarKeinginan() {
		return daftarKeinginan;
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public Context getContext() {
		return applicationContext;
	}

	public String getDataDir() {
		return dataDir;
	}
}
