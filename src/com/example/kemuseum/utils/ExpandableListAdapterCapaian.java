package com.example.kemuseum.utils;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.kemuseum.R;
import com.example.kemuseum.model.Capaian;

public class ExpandableListAdapterCapaian extends BaseExpandableListAdapter {
	private Context context;
    private List<Capaian> daftarCapaian; 
 
    public ExpandableListAdapterCapaian(Context context, List<Capaian> daftarCapaian) {
        this.context = context;
        this.daftarCapaian = daftarCapaian;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        List<String> capaianMuseum = daftarCapaian.get(groupPosition).getCapaianPerRuangan();
        return capaianMuseum.get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
    	final String capaian = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_layout_capaian_expanded, null);
        }
 
        TextView textView = (TextView) convertView.findViewById(R.id.capaian_info);
         
        textView.setText(capaian);
        return convertView;
    }
    
    @Override
    public int getChildrenCount(int groupPosition) {
        return daftarCapaian.get(groupPosition).getCapaianPerRuangan().size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return daftarCapaian.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return daftarCapaian.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        Capaian capaian = (Capaian) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_layout_capaian, null);
        }
 
        TextView textView = (TextView) convertView.findViewById(R.id.capaian_nama_museum);
        textView.setText(capaian.getNamaMuseum() + "(" + capaian.getProgress() + " %)");
 
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