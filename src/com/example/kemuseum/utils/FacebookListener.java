package com.example.kemuseum.utils;

import android.util.Log;

public class FacebookListener implements ListenerShareFacebook{

	@Override
	public void onShareFacebookSuccess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShareFacebookFailure(String string) {
		// TODO Auto-generated method stub
		Log.d("asd", "gan " + string);
	}

}
