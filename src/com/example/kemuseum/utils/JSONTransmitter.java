package com.example.kemuseum.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class JSONTransmitter extends AsyncTask<JSONObject, JSONObject, JSONObject>{
	String url = "http://caterpilight.com/wishlist.php";
	@Override
	protected JSONObject doInBackground(JSONObject... data) {
		// TODO Auto-generated method stub
		JSONObject json = data[0];
        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 100000);
 
        JSONObject jsonResponse = null;
        HttpPost post = new HttpPost(url);
        try {
            StringEntity se = new StringEntity("json="+json.toString());
            post.addHeader("content-type", "application/x-www-form-urlencoded");
            post.setEntity(se);
             
            HttpResponse response;
            response = client.execute(post);
            String resFromServer = org.apache.http.util.EntityUtils.toString(response.getEntity());
 
            jsonResponse=new JSONObject(resFromServer);
            Log.d("Response from server","Haha "+ jsonResponse.getString("msg"));
        } catch (Exception e) {
        	Log.d("Response", "aha "+e.toString());
        	}
         
        return jsonResponse;
	}

}
