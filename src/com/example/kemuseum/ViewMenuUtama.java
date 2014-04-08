package com.example.kemuseum;


import com.example.kemuseum.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewMenuUtama extends Activity {
	LinearLayout llBody;
	Button ButtPilMus, ButtCapaian, ButtPengembang;
	ImageView Magni;
	View woww =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setClickListener();
        setContentView(R.layout.activity_main);
        inisiasi();
        setClickListener();
			
    }

    public void inisiasi() {
    	llBody = (LinearLayout) findViewById(R.id.llBody);
    	ButtPilMus = (Button) findViewById(R.id.ButtPilMus);
    	ButtCapaian = (Button) findViewById(R.id.ButtCapaian);
    	ButtPengembang = (Button) findViewById(R.id.ButtPengembang);
    	Magni = (ImageView) findViewById(R.id.magni);
    }
    
    public void setClickListener()
    {
    	Magni.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(0);
			}
		});
    }
    
    protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0: {
			LayoutInflater inflater = LayoutInflater.from(this);
	    	View inflated = inflater.inflate(R.layout.activity_view_pencarian, null);
	    	woww = inflated;
	    	OnTouchListener c = new OnTouchListener() {
				
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					return false;
				}
			};
			return new AlertDialog.Builder(this).
					setNegativeButton("Tidak",null).
					setPositiveButton("Oke", null).
					setView(woww).
					create();
		}
		
		}
		return null;
	}
    public void ButtPilMus_onClick(View view) {
    	switch (view.getId()) {
		case R.id.ButtPilMus:
				Intent i = new Intent (ViewMenuUtama.this, ViewPilihMuseum.class);
				i.putExtra("Pilih Museum", "a");
				final int a = 1;
				startActivityForResult(i, a);
			break;
    	}
    }
    
    public void ButtCapaian_onClick(View view) {
    	switch (view.getId()) {
		case R.id.ButtCapaian:
				Intent i = new Intent (ViewMenuUtama.this, ViewCapaian.class);
				i.putExtra("Capaian", "a");
				final int a = 1;
				startActivityForResult(i, a);
			break;
    	}
    }
    
    public void ButtPengembang_onClick(View view) {
    	switch (view.getId()) {
		case R.id.ButtPengembang:
				Intent i = new Intent (ViewMenuUtama.this, ViewInfoPengembang.class);
				i.putExtra("Pengembang", "a");
				final int a = 1;
				startActivityForResult(i, a);
			break;
    	}
    }
    
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	//super.onBackPressed();
    	Intent i = new Intent (ViewMenuUtama.this, ViewSplashScreen.class);
    	setResult(Activity.RESULT_OK, i);
    	finish();
    	
	
    }
}
