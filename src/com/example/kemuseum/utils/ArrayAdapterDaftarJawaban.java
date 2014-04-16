package com.example.kemuseum.utils;

import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.kemuseum.R;
import com.example.kemuseum.model.Pertanyaan;

public class ArrayAdapterDaftarJawaban extends ArrayAdapter<Pertanyaan>{
	private final Context context;
	private final List<Pertanyaan> daftarJawaban;
	private final List<Integer> terjodohkanDengan;
	
	public ArrayAdapterDaftarJawaban(Context context, List<Pertanyaan> daftarJawaban, List<Integer> terjodohkanDengan) {
		super(context, R.layout.row_layout_daftar_jawaban, daftarJawaban);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.daftarJawaban = daftarJawaban;
		this.terjodohkanDengan = terjodohkanDengan;
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.row_layout_daftar_jawaban, parent, false);
		TextView radioJawaban = (TextView) rowView.findViewById(R.id.tvJawaban);
		TextView tvNomor = (TextView) rowView.findViewById(R.id.nomor);
		
		tvNomor.setText(position+"");
		tvNomor.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT));
		radioJawaban.setText(daftarJawaban.get(position).getJawaban());
		
		if (terjodohkanDengan.get(position) != -1){
			radioJawaban.setPaintFlags(radioJawaban.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		}
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
