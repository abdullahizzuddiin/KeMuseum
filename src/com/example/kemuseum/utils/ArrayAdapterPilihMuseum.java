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

public class ArrayAdapterPilihMuseum extends ArrayAdapter<Museum> {
	private final Context context;
	private final List<Museum> daftarMuseum;

	public ArrayAdapterPilihMuseum(Context context, List<Museum> daftarMuseum) {
		super(context, R.layout.row_layout_pilih_museum, daftarMuseum);
		this.context = context;
		this.daftarMuseum = daftarMuseum;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.row_layout_pilih_museum, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		textView.setText(daftarMuseum.get(position).getNama());

		// tentukan icon terkunci atau tidak
		boolean statusTerkunci = daftarMuseum.get(position).getStatusTerkunci();
		if (statusTerkunci) {
			imageView.setImageResource(R.drawable.dummy_no);
		} else {
			imageView.setImageResource(R.drawable.dummy_yes);
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
