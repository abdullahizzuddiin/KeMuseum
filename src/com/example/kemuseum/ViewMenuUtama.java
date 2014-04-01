package com.example.kemuseum;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewMenuUtama extends Activity {
	LinearLayout llHeaderTulisan, llPilMuseum, llCapaian, llCari, llAbout;
	TextView tvHeaderTulisan;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setClickListener();
        setContentView(R.layout.activity_main);
        inisiasi();
        setClickListener();

			
    }

    public void inisiasi() {
    	llHeaderTulisan = (LinearLayout) findViewById(R.id.llHeaderTulisan);
    	llPilMuseum = (LinearLayout) findViewById(R.id.llPilMuseum);
    	llCapaian = (LinearLayout) findViewById(R.id.llCapaian);
    	llCari = (LinearLayout) findViewById(R.id.llCari);
    	llAbout = (LinearLayout) findViewById(R.id.llAbout);
    	tvHeaderTulisan = (TextView) findViewById(R.id.tvHeaderTulisan);
    }
    
    public void setClickListener()
    {
    	llCari.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ViewMenuUtama.this, ViewPencarian.class);
				startActivity(i);
			}
		});
    	
    	llCapaian.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ViewMenuUtama.this, ViewCapaian.class);
				startActivity(i);
			}
		});
    	
    	llPilMuseum.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ViewMenuUtama.this, ViewPilihMuseum.class);
				startActivity(i);
			}
		});
    	
    	llAbout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ViewMenuUtama.this, ViewInfoPengembang.class);
				startActivity(i); 
			}
		});
    }
    
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    
}
