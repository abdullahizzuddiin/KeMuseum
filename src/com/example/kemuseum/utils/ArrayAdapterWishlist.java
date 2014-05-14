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
import com.example.kemuseum.model.Wishlist;

public class ArrayAdapterWishlist extends ArrayAdapter<Wishlist>{
	private final Context context;
	private final List<Wishlist> daftarWishlist;
	
	
	public ArrayAdapterWishlist(Context context, List<Wishlist> daftarWishlist) {
		super(context, R.layout.row_layout_wishlist, daftarWishlist);
		this.context = context;
		this.daftarWishlist = daftarWishlist;
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.row_layout_wishlist, parent, false);
		TextView tanggal = (TextView) rowView.findViewById(R.id.tanggal);
		TextView wishlist = (TextView) rowView.findViewById(R.id.wishlist);
		TextView nama = (TextView) rowView.findViewById(R.id.nama);
		
		Wishlist w = daftarWishlist.get(position);
		
		tanggal.setText(w.getTanggal());
		nama.setText(w.getNama());
		wishlist.setText(w.getDeskripsi());
		return rowView;
	}
	

	@Override
	public boolean hasStableIds() {
		return true;
	}
}
