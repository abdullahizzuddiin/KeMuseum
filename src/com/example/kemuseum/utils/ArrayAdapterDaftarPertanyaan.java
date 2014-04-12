package com.example.kemuseum.utils;

import java.util.List;

import com.example.kemuseum.model.Museum;
import com.example.kemuseum.model.Pertanyaan;

import com.example.kemuseum.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArrayAdapterDaftarPertanyaan extends ArrayAdapter<Pertanyaan>{
	private final Context context;
	private final List<Pertanyaan> daftarPertanyaan;
	
	public ArrayAdapterDaftarPertanyaan(Context context, List<Pertanyaan> daftarPertanyaan) {
		super(context, R.layout.row_layout_daftar_pertanyaan, daftarPertanyaan);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.daftarPertanyaan = daftarPertanyaan;
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.row_layout_daftar_pertanyaan, parent, false);
		TextView tvLabel = (TextView) rowView.findViewById(R.id.label);
		TextView tvNomor = (TextView) rowView.findViewById(R.id.nomor);
		
		tvNomor.setText(daftarPertanyaan.get(position)+"");
		tvLabel.setText(daftarPertanyaan.get(position).getSoal());
		return rowView;
	}
	
	public long getItemId(int position) {
		final Pertanyaan item = getItem(position);
		return (long)item.getId();
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}
}
