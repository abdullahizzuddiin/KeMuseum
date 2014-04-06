package com.example.kemuseum;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class ViewSplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_splash_screen);
		
		Thread timer = new Thread() {
            public void run() {
                try {
                    //berapalama splashscreen akan ditampilkan dalam milisecond
                sleep(3000);
            } catch (InterruptedException e) {
            // TODO: handle exception
                e.printStackTrace();
            } finally {
            	//activity yang akan dijalankan setelah splashscreen selesai
	                Intent i = new Intent(ViewSplashScreen.this, ViewMenuUtama.class);
	                //startActivity(i);
	                startActivityForResult(i, 1);
	            }
            }
        };
        timer.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_splash_screen, menu);
		return true;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	finish();
    	
    }

}
