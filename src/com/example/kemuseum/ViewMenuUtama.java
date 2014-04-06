package com.example.kemuseum;


import com.example.kemuseum.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewMenuUtama extends Activity {
	LinearLayout llBody;
	Button ButtPilMus, ButtCapaian, ButtPengembang;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setClickListener();
        setContentView(R.layout.activity_main);
        inisiasi();

			
    }

    public void inisiasi() {
    	llBody = (LinearLayout) findViewById(R.id.llBody);
    	ButtPilMus = (Button) findViewById(R.id.ButtPilMus);
    	ButtCapaian = (Button) findViewById(R.id.ButtCapaian);
    	ButtPengembang = (Button) findViewById(R.id.ButtPengembang);
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
