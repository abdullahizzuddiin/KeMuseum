package com.example.kemuseum;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kemuseum.controller.ControllerPencarian;
import com.example.kemuseum.model.Barang;
import com.example.kemuseum.utils.ExpandableListAdapterPencarian;

public class ViewPencarian extends Activity {
	private Spinner pilihMuseum;
	private EditText fieldKataKunci;
	private TextView fieldPesanPencarian;
	private ControllerPencarian controller;
	private ExpandableListAdapterPencarian expandableAdapter;
	private ExpandableListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pencarian);
		inisiasi();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_pencarian, menu);
		return true;
	}
	
	private void inisiasi()
	{
		listView = (ExpandableListView) findViewById(R.id.list_hasil_pencarian);
		pilihMuseum = (Spinner) findViewById(R.id.spinner_museum);
		fieldKataKunci = (EditText) findViewById(R.id.text_kataKunci);
		fieldPesanPencarian = (TextView) findViewById(R.id.text_pesan_pencarian);
		
		controller = new ControllerPencarian();
		
		isiDataSpinner();
	}
	
	private void isiDataSpinner(){
		List<String> list = new ArrayList<String>();
		list.add("Semua museum");
		list.addAll(controller.getDaftarNamaMuseumTerbuka());
	
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		pilihMuseum.setAdapter(dataAdapter);
	}
	
    public void onClick(View view){
    	switch(view.getId()){
    	case R.id.button_cari:
    		String kataKunci = fieldKataKunci.getEditableText().toString();
    		String namaMuseum = String.valueOf(pilihMuseum.getSelectedItem());

    		List<Barang> hasilPencarian = controller.getHasilPencarian(kataKunci, namaMuseum);

    		String pesanPencarian = "";
    		if (hasilPencarian.size() > 0){
    			pesanPencarian = hasilPencarian.size() + " hasil ditemukan";
    		}else{
    			pesanPencarian = "Tidak ada barang yang ditemukan";
    		}
    		fieldPesanPencarian.setText(pesanPencarian);
    		
			expandableAdapter = new ExpandableListAdapterPencarian(this, hasilPencarian);
			listView.setAdapter(expandableAdapter);
	
    		break;
    	}
    }
}
