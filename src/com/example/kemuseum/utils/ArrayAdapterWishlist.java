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
import com.example.kemuseum.model.Wishlist;

public class ArrayAdapterWishlist extends ArrayAdapter<Pertanyaan>{
	private final Context context;
	private final List<Wishlist> daftarWishlist;
	
	
	public ArrayAdapterWishlist(Context context, List<Wishlist> daftarWishlist) {
		super(context, R.layout.row_layout_wishlist);
		// TODO Auto-generated constructor stub
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
		
		wishlist.setText(daftarWishlist.get(position).getDeskripsi());
		tanggal.setText(daftarWishlist.get(position).getTanggal());
		
		return rowView;
	}
	

	@Override
	public boolean hasStableIds() {
		return true;
	}
}