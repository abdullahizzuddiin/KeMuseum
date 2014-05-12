package com.example.kemuseum.utils;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kemuseum.R;
import com.example.kemuseum.model.Museum;

public class ArrayAdapterHapusMuseum extends ArrayAdapter<Museum> {
	private final Context context;
	private final List<Museum> daftarMuseum;
	
	public ArrayAdapterHapusMuseum(Context context, List<Museum> daftarMuseum) {
		super(context, R.layout.row_layout_hapus_museum, daftarMuseum);
		this.context = context;
		this.daftarMuseum = daftarMuseum;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row_layout_hapus_museum, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		Museum mem = daftarMuseum.get(position);
		
		textView.setText(mem.getNama());

		return rowView;
	}
	
	@Override
	public long getItemId(int position) {
		final Museum item = getItem(position);
		return (long)item.getId();
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}
}
