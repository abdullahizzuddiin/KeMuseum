package com.example.kemuseum.utils;

import java.util.List;

import com.example.kemuseum.model.Pertanyaan;

import com.example.kemuseum.R;
import android.content.Context;
import android.widget.ArrayAdapter;

public class ArrayAdapterDaftarPertanyaan extends ArrayAdapter<Pertanyaan>{
	private final Context context;
	private final List<Pertanyaan> daftarPertanyaan;
	
	public ArrayAdapterDaftarPertanyaan(Context context, List<Pertanyaan> daftarPertanyaan) {
		super(context, R.layout.row_layout_daftar_pertanyaan, daftarPertanyaan);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.daftarPertanyaan = daftarPertanyaan;
	}

}
