package com.example.kemuseum.utils;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kemuseum.R;
import com.example.kemuseum.model.Ruangan;


public class ArrayAdapterPilihRuangan extends ArrayAdapter<Ruangan> {
	private final Context context;
	private final List<Ruangan> daftarRuangan;

	public ArrayAdapterPilihRuangan(Context context, List<Ruangan> daftarRuangan) {
		super(context, R.layout.row_layout_pilih_museum, daftarRuangan);
		Log.d("asd", "gan kebikin ");
		this.context = context;
		this.daftarRuangan = daftarRuangan;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Log.d("asd", "gan view " + position);
		View rowView = inflater.inflate(R.layout.row_layout_pilih_museum, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		textView.setText(daftarRuangan.get(position).getNama());

		// tentukan icon terkunci atau tidak
		boolean statusTerkunci = daftarRuangan.get(position).getStatusTerkunci();
		if (statusTerkunci) {
			imageView.setImageResource(R.drawable.dummy_no);
		} else {
			imageView.setImageResource(R.drawable.dummy_yes);
		}

		return rowView;
	}
	
	@Override
	public long getItemId(int position) {
		Log.d("asd", "gan " + position);
		final Ruangan item = getItem(position);
		return (long)item.getId();
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

}
