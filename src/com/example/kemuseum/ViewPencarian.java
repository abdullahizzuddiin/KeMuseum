package com.example.kemuseum;

import com.example.kemuseum.R;

import android.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewPencarian extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pencarian);
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
	}
	
    public void setClickListener()
    {
    }
}
