package com.example.kemuseum.utils;

import java.util.List;

import com.example.kemuseum.model.Museum;
import com.example.kemuseum.model.Pertanyaan;

import com.example.kemuseum.R;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class ArrayAdapterDaftarPertanyaan extends ArrayAdapter<Pertanyaan>{
	private final Context context;
	private final List<Pertanyaan> daftarPertanyaan;
	private final List<Pertanyaan> daftarJawaban;
	private final List<Integer> dijodohkanDengan;
	
	public ArrayAdapterDaftarPertanyaan(Context context, List<Pertanyaan> daftarPertanyaan, List<Pertanyaan> daftarJawaban, List<Integer> dijodohkanDengan) {
		super(context, R.layout.row_layout_daftar_pertanyaan, daftarPertanyaan);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.daftarPertanyaan = daftarPertanyaan;
		this.daftarJawaban = daftarJawaban;
		this.dijodohkanDengan = dijodohkanDengan;
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.row_layout_daftar_pertanyaan, parent, false);
		View ViewJawaban = inflater.inflate(R.layout.row_layout_daftar_jawaban, parent, false);
		TextView tvLabel = (TextView) rowView.findViewById(R.id.label);
		TextView tvNomor = (TextView) rowView.findViewById(R.id.nomor);
		TextView tvJawaban = (TextView) rowView.findViewById(R.id.jawaban);
		
		tvNomor.setText(position+1+"");
		tvNomor.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT));
		tvLabel.setText(daftarPertanyaan.get(position).getSoal());	
		Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/ubuntu.ttf");
		tvLabel.setTypeface(font);

		String jawaban = "";
		int posJawaban = dijodohkanDengan.get(position);
		if (posJawaban != -1){
			jawaban = daftarJawaban.get(posJawaban).getJawaban();
		}
		tvJawaban.setText("Jawabannya: " + jawaban);

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
