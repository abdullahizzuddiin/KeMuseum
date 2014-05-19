package com.example.kemuseum.utils;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kemuseum.R;
import com.example.kemuseum.ViewGambarBarangFullscreen;
import com.example.kemuseum.model.Barang;

public class ExpandableListAdapterBarang extends BaseExpandableListAdapter {
	private Context context;
    private List<Barang> daftarBarang; 
    private MuseumManager mm;
 
    public ExpandableListAdapterBarang(Context context, List<Barang> daftarBarang) {
        this.context = context;
        this.daftarBarang = daftarBarang;
        mm = MuseumManager.getMuseumManager();
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return daftarBarang.get(groupPosition);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
    	final Barang barang = (Barang) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_layout_ruangan_expanded, null);
        }
 
        ImageView imageView = (ImageView) convertView.findViewById(R.id.barang_gambar);
        TextView textView = (TextView) convertView.findViewById(R.id.barang_info);
        
        Drawable gambar = mm.getDrawableImage(barang.getIdMuseum(), barang.getNamaBerkasGambar());
        if (gambar != null){
        	imageView.setImageDrawable(gambar);
        }else{
        	imageView.setImageResource(R.drawable.ic_launcher);
        }
        
        imageView.setOnTouchListener(new OnTouchListener(){
	        @Override
	        public boolean onTouch(View v, MotionEvent event){
				Intent i = new Intent(context, ViewGambarBarangFullscreen.class);
				i.putExtra("idMuseum", barang.getIdMuseum());
				i.putExtra("gambar", barang.getNamaBerkasGambar());
				context.startActivity(i);
		        return false;
	        }
	   });
        
        textView.setText(barang.getDeksipsi());
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/ubuntu.ttf");
		textView.setTypeface(font);
        return convertView;
    }
    
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return daftarBarang.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return daftarBarang.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        Barang barang = (Barang) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_layout_ruangan, null);
        }
 
        TextView textView = (TextView) convertView.findViewById(R.id.barang_nama);
        textView.setText(barang.getNama());
 
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}