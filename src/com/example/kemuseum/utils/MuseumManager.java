package com.example.kemuseum.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.kemuseum.model.Barang;
import com.example.kemuseum.model.Koordinat;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.model.Pertanyaan;

/**
 * singleton!
 */
public class MuseumManager {
	private static MuseumManager instance;

	private List<Museum> daftarMuseum;
	private String dataDir;
	private AssetManager assetManager;
	private Context applicationContext;

	private String FOLDER_GAMBAR = "img";
	private int BUFFER_SIZE = 2048;
	private boolean DEBUG_MODE = false;

	public static void createMuseumManager(AssetManager assetManager,
			Context applicationContext) {
		if (instance == null) {
			instance = new MuseumManager(assetManager, applicationContext);
		}
	}

	public static MuseumManager getMuseumManager() {
		if (instance == null) {
			Log.w("warning", "gan minta instance MuseumManager kosong");
		}
		return instance;
	}

	private MuseumManager(AssetManager assetManager, Context applicationContext) {
		this.assetManager = assetManager;
		this.applicationContext = applicationContext;
		dataDir = applicationContext.getFilesDir().getAbsolutePath();

		Log.d("asd", "gan masuk");
		// sementara begini dulu
		if (DEBUG_MODE) {
			// simpan file museum dummy.json dari folder assets ke internal
			// memory (dataDir)
			String fileName = "dummy.json";
			try {
				File dummyJSON = new File(dataDir, fileName);
				FileOutputStream fos = new FileOutputStream(
						dummyJSON.getAbsolutePath());
				InputStream is = new BufferedInputStream(
						assetManager.open(fileName));
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();

				int nRead;
				byte[] data = new byte[BUFFER_SIZE];
				while ((nRead = is.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, nRead);
				}
				buffer.flush();

				is.close();
				fos.write(buffer.toByteArray());
				fos.close();
				Log.d("MuseumManager", "gan " + fileName + " sukses!");
			} catch (FileNotFoundException e) {
				Log.d("MuseumManager", "gan " + fileName + " bermasalah!");
			} catch (IOException e) {
				Log.d("MuseumManager", "gan " + fileName
						+ " gak ditemukan dari asset!");
			}
		}

		// TODO loads database
		daftarMuseum = new ArrayList<Museum>();
		File museumJSON = new File(dataDir);
		String[] jsonList = museumJSON.list();
		for (String js : jsonList) {
			Log.d("MuseumManager", "gan parsing " + js);

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

				// Log.d("MuseumManager", "gan content:" + json);

				Museum m = JSONParser.toMuseum(json);
				daftarMuseum.add(m);

			} catch (Exception e) {
				Log.d("MuseumManager", "gan error parsing " + js);
			}
		}
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public Context getContext() {
		return applicationContext;
	}

	public List<Museum> getDaftarMuseum() {
		return daftarMuseum;
	}

	public Museum getMuseum(int idMuseum) {
		Museum ret = null;

		for (Museum m : daftarMuseum) {
			if (m.getId() == idMuseum) {
				ret = m;
				break;
			}
		}

		return ret;
	}

	public List<Barang> getDaftarBarang(int idMuseum, int idRuangan) {
		List<Barang> daftar = new ArrayList<Barang>();

		for (Museum m : daftarMuseum) {
			Log.d("Judin", "Judin Hanan"+ (m.getId() == idMuseum)+" "+m.getId()+" "+idMuseum);
			if (m.getId() == idMuseum) {
				List<Barang> temp = m.getDaftarBarang(idRuangan);
				daftar.addAll(temp);
			}
		}
		return daftar;
	}

	public void updateDatabase(Museum museum) {
		try {
			String fileName = "" + museum.getId();
			File museumJSON = new File(dataDir, fileName);
			FileOutputStream fos = new FileOutputStream(
					museumJSON.getAbsolutePath());

			Log.d("asd", "gan keupdate" + museumJSON.getAbsolutePath() + " "
					+ JSONParser.toJSON(museum).toString());

			fos.write(JSONParser.toJSON(museum).getBytes());
			fos.close();

		} catch (Exception e) {
			Log.d("MuseumManager", "gan museum " + museum.getId()
					+ " bermasalah pas mau update !");
		}
	}

	public void tambahMuseum(Museum museum) {
		try {
			String fileName = "" + museum.getId();
			File museumJSON = new File(dataDir, fileName);
			FileOutputStream fos = new FileOutputStream(
					museumJSON.getAbsolutePath());

			Log.d("asd", "gan jadi ada" + museumJSON.getAbsolutePath());

			fos.write(JSONParser.toJSON(museum).getBytes());
			fos.close();

			// langsung saja hidupkan!
			daftarMuseum.add(museum);

		} catch (Exception e) {
			Log.d("MuseumManager", "gan museum " + museum.getId()
					+ " bermasalah !");
		}
	}

	public void hapusMuseum(Museum museum) {
		// TODO iterasi 2
	}

	public boolean bukaKunciMuseum(int idMuseum, Koordinat koordinat) {
		Museum target = null;

		for (Museum m : daftarMuseum) {
			if (m.getId() == idMuseum) {
				target = m;
				break;
			}
		}

		boolean sukses = false;
		if (target != null) {
			if (target.cekDiDalam(koordinat)) {
				sukses = true;
				target.setStatusTerkunci(false);
				Log.d("asd", "gan berhasil buka "
						+ JSONParser.toJSON(target).toString());
				updateDatabase(target);
			}
		}

		return sukses;
	}

	public List<Pertanyaan> getDaftarPertanyaan(int idMuseum, int idRuangan) {
		List<Pertanyaan> daftar = null;

		for (Museum m : daftarMuseum){
			if (m.getId() == idMuseum) {
				daftar = m.getDaftarPertanyaan(idRuangan);
			}
		}
		return daftar;
	}

	public boolean cekJawaban(int idMuseum, int idRuangan, List<String> jawaban) {
		boolean hasil = false;

		for (Museum m : daftarMuseum) {
			if (m.getId() == idMuseum) {
				hasil = m.cekJawaban(idRuangan, jawaban);
			}
		}

		return hasil;
	}

	/**
	 * Mendapatkan daftar barang sebagai hasil pencarian dengan kata kunci dan
	 * lokasi museum tertentu.
	 * 
	 * @param kataKunci
	 *            kata kunci yang diinginkan
	 * @param idMuseum
	 *            nomor urut museum, -1 bila pencarian untuk semua museum yang
	 *            ada di device
	 * @return daftar barang yang mengandung kataKunci sebagai nama, deskripsi,
	 *         atau kategorinya
	 */
	public List<Barang> getHasilPencarian(String kataKunci, int idMuseum) {
		List<Barang> daftar = new ArrayList<Barang>();

		for (Museum m : daftarMuseum) {
			if ((idMuseum == -1) || (idMuseum == m.getId())) {
				// hanya bisa bila museum sudah terbuka!
				if (!m.getStatusTerkunci()) {
					List<Barang> temp = m.cariBarang(kataKunci);
					daftar.addAll(temp);
				}
			}
		}

		return daftar;
	}

	public File getFolderGambar(int idMuseum) {
		return applicationContext.getDir(FOLDER_GAMBAR + idMuseum,
				Context.MODE_PRIVATE);
	}

	public Drawable getDrawableImage(int idMuseum, String namaBerkasGambar) {
		Drawable ret = null;

		try {
			File f = new File(this.getFolderGambar(idMuseum), namaBerkasGambar);
			InputStream ims = new BufferedInputStream(new FileInputStream(f));

			ret = Drawable.createFromStream(ims, null);
		} catch (Exception e) {
		}

		return ret;
	}

	/**
	 * Cek apakah suatu museum sudah ada di dalam database lokal device
	 * 
	 * @param idMuseum
	 *            id museum yang ingin diperiksa
	 * @return true bila sudah ada, false bila belum ada
	 */
	public boolean cekMuseumSudahDimiliki(int idMuseum) {
		boolean ada = false;
		for (Museum m : daftarMuseum) {
			if (m.getId() == idMuseum) {
				ada = true;
				break;
			}
		}
		return ada;
	}

	public String getDataDir() {
		return dataDir;
	}
}
