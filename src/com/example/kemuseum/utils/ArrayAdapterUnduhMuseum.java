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

public class ArrayAdapterUnduhMuseum extends ArrayAdapter<Museum> {
	private final Context context;
	private final List<Museum> daftarMuseum;
	private MuseumManager museumManager;
	
	public ArrayAdapterUnduhMuseum(Context context, List<Museum> daftarMuseum) {
		super(context, R.layout.row_layout_unduh_museum, daftarMuseum);
		this.context = context;
		this.daftarMuseum = daftarMuseum;
		museumManager = MuseumManager.getMuseumManager();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row_layout_unduh_museum, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		Museum mem = daftarMuseum.get(position);
		
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		textView.setText(mem.getNama());

		// tentukan icon sudah ada atau belum ada
		if (museumManager.cekMuseumSudahDimiliki(mem.getId())) {
			imageView.setImageResource(R.drawable.dummy_yes);
		} else {
			imageView.setImageResource(R.drawable.dummy_no);
		}

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
