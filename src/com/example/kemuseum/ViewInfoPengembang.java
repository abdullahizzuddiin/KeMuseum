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
import android.widget.TextView;

public class ViewInfoPengembang extends Activity {	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_info_pengembang);
		inisiasi();
		setClickListener();
	}
	
	public void inisiasi()
	{
	}
	
    public void setClickListener()
    {
    }
}
