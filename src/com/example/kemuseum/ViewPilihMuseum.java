package com.example.kemuseum;

import com.example.kemuseum.R;

import android.R.layout;
import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ViewPilihMuseum extends Activity {	
	LinearLayout llMusFas, llMusFat, llPilMus;
	DialogInterface.OnClickListener ViewMuseumTerkunci = null;
	DialogInterface.OnClickListener test = null;
	
	View woww =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pilih_museum);
		inisiasi();
		setClickListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_pencarian, menu);
		return true;
	}
	
	public void inisiasi()
	{
		llMusFas = (LinearLayout) findViewById(R.id.llMusFas);
		llMusFat = (LinearLayout) findViewById(R.id.llMusFat);
		llPilMus = (LinearLayout) findViewById(R.id.llViewPilMus);
		
	}
	
    public void setClickListener()
    {
    	test = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
//				try {
//					pb.setVisibility(View.VISIBLE);
//				} catch (Exception e) {
//					// TODO: handle exception
//					Log.d("coba", "test"+e.toString());
//				}
			}
		};
    	
    	llMusFas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent (ViewPilihMuseum.this, ViewMuseumTerbuka.class);
				i.putExtra("Terbuka", "a");
				final int a = 1;
				startActivityForResult(i, a);
			}
		});
    	
    	llMusFat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(0);
			}
		});
    	
    	ViewMuseumTerkunci = new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent i = new Intent (ViewPilihMuseum.this, ViewUnduhMuseum.class);
				i.putExtra("Unduh", "a");
				final int a = 1;
				startActivityForResult(i, a);
			}
		};
    	
    }
   
    
    protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0: {
			LayoutInflater inflater = LayoutInflater.from(this);
	    	View inflated = inflater.inflate(R.layout.activity_view_museum_terkunci, null);
	    	woww = inflated;
	    	OnTouchListener c = new OnTouchListener() {
				
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					return false;
				}
			};
			return new AlertDialog.Builder(this).
					setNegativeButton("Tidak",null).
					setPositiveButton("Checkin", ViewMuseumTerkunci).
					setView(woww).
					create();
		}
		
		}
		return null;
	}
}
