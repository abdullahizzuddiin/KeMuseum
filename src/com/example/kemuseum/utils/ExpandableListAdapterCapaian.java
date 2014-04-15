package com.example.kemuseum.utils;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.kemuseum.R;
import com.example.kemuseum.ViewCapaian;
import com.example.kemuseum.model.Capaian;

public class ExpandableListAdapterCapaian extends BaseExpandableListAdapter {
	private Context context;
	private List<Capaian> daftarCapaian;
	private ViewCapaian host;

	public ExpandableListAdapterCapaian(Context context,
			List<Capaian> daftarCapaian, Activity host) {
		this.context = context;
		this.daftarCapaian = daftarCapaian;
		this.host = (ViewCapaian) host;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		List<String> capaianMuseum = daftarCapaian.get(groupPosition)
				.getCapaianPerRuangan();
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
			convertView = infalInflater.inflate(
					R.layout.row_layout_capaian_expanded, null);
		}

		TextView textView = (TextView) convertView
				.findViewById(R.id.capaian_info);

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
		final Capaian capaian = (Capaian) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.row_layout_capaian,
					null);
		}

		TextView textView = (TextView) convertView
				.findViewById(R.id.capaian_nama_museum);
		textView.setText(capaian.getNamaMuseum() + "(" + capaian.getProgress()
				+ " %)");

		Button tombolShare = (Button) convertView
				.findViewById(R.id.capaian_tombol_share);
		tombolShare.setFocusable(false);
		tombolShare.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					Log.d("asd", "gan siap2");
					Bundle data = new Bundle();
					data.putString(host.BUNDLE_PRESENTASE, "" + capaian.getProgress());
					data.putString(host.BUNDLE_NAMA_MUSEUM, capaian.getNamaMuseum());
					host.share(data);
					/*
					 * Intent sharingIntent = new Intent(Intent.ACTION_SEND);
					 * 
					 * sharingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					 * sharingIntent.setType("text/plain");
					 * sharingIntent.putExtra(
					 * android.content.Intent.EXTRA_TEXT, "Aku sudah " +
					 * capaian.getProgress() + " persen dalam menjelajahi " +
					 * capaian.getNamaMuseum() + " :)"); sharingIntent.putExtra(
					 * android.content.Intent.EXTRA_SUBJECT, "judul");
					 * 
					 * MuseumManager .getMuseumManager() .getContext()
					 * .startActivity( Intent.createChooser(sharingIntent,
					 * "Share using").setFlags( Intent.FLAG_ACTIVITY_NEW_TASK));
					 */
				} catch (Exception e) {
					Log.d("asd", "gan " + e.toString());
				}
			}
		});

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