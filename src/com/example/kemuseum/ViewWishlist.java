package com.example.kemuseum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.kemuseum.controller.ControllerWishlist;
import com.example.kemuseum.model.Wishlist;
import com.example.kemuseum.utils.ArrayAdapterWishlist;
import com.example.kemuseum.utils.JSONTransmitter;
import com.example.kemuseum.utils.WishlistManager;

import android.app.Activity;
import android.hardware.Camera.Size;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ViewWishlist extends Activity {
	private EditText text_namaWishlist, text_namaPengirim, text_alamatEmail;
	private Button buttKirim;
	private ListView listViewWishlist;
	private String[] bulan;
	private String url = "http://caterpilight.com/wishlist.php";
	private ControllerWishlist controllerWishlist;
	private List<Wishlist> daftarWishlist;
	private ArrayAdapterWishlist arrayAdapter;
	private Calendar c;
	private static String json;
	private int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_wishlist);
		inisiasi();
		setClickListener();
		isiData();
		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
	}
	public void isiData() {
		daftarWishlist = controllerWishlist.getWishlist();
		arrayAdapter = new ArrayAdapterWishlist(this, daftarWishlist);
		listViewWishlist.setAdapter(arrayAdapter);
		
		try {
			Log.d("ViewWishlist", "muncul"+daftarWishlist.size());
			Log.d("ViewWishlist", "muncul"+daftarWishlist.get(daftarWishlist.size()-1).getDeskripsi());
		} catch (Exception e) {
			// TODO: handle exception
			Log.d("ViewWishlist", "muncul"+e.toString());
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_wishlist, menu);
		return true;
	}
	public void inisiasi()
	{
		text_namaWishlist = (EditText) findViewById(R.id.text_namaWishlist);
		text_namaPengirim = (EditText) findViewById(R.id.text_namaPengirim);
		text_alamatEmail = (EditText) findViewById(R.id.text_alamatEmail);
		listViewWishlist = (ListView) findViewById(R.id.listViewWishlist);
		buttKirim = (Button) findViewById(R.id.button_kirim);
		bulan = new String[]{"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agus", "Sept", "Okt", "Nov", "Des"};
		controllerWishlist = new ControllerWishlist();
		c = Calendar.getInstance();
	}
	
	public void setClickListener() {
		buttKirim.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				id = controllerWishlist.getWishlist().size();
				String tahun = c.get(Calendar.YEAR)+"";
				tahun = tahun.substring(2,4);
				
				String tanggal = c.get(Calendar.DATE)+"-"+bulan[c.get(Calendar.MONTH)]+"-"+ tahun;
				String nama = text_namaPengirim.getText().toString();
				String email = text_alamatEmail.getText().toString();
				String deskripsi = text_namaWishlist.getText().toString();
				Log.d("sa", "hehe"+" "+tanggal+" "+nama+" "+email+" "+deskripsi);
				
				Wishlist w = new Wishlist(id, tanggal, nama, email, deskripsi);
				controllerWishlist.tambahWishlist(w);
				arrayAdapter.notifyDataSetChanged();
				
				json = controllerWishlist.ambilJSON(w);
//					url +="?json="+URLEncoder.encode(json);
//					getRequest(url);
				try {
					postData(json);
				} catch (Exception e) {
					// TODO: handle exception
				}
//				new HttpAsyncTask().execute("http://caterpilight.com/wishlist.php");	
			}
		});
	}
	
	public void postData(String json) throws JSONException {
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(url);
	    try { 
	    	
	        StringEntity se = new StringEntity(json);
	        
	        se.setContentType("application/json;charset=UTF-8");
	        httppost.setEntity(se);
	        HttpResponse response = httpclient.execute(httppost); 
	        Log.d("postData", "haha "+ json);
	        if(response != null) {
	            InputStream is = response.getEntity().getContent();
	            //input stream is response that can be shown back on android
	        }

	    }catch (Exception e) {
	        e.printStackTrace();
	        Log.d("postData", "ex haha "+ json +" "+e.toString());
	    } 
	}
	
//	public boolean isConnected() {
//		ConnectivityManager connManager = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
//		NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) 
//            return true;
//        else
//            return false;
//	}
//	
//	public static String POST(String url, Wishlist w){
//        InputStream inputStream = null;
//        String result = "";
//        try {
// 
//            // 1. create HttpClient
//            HttpClient httpclient = new DefaultHttpClient();
// 
//            // 2. make POST request to the given URL
//            HttpPost httpPost = new HttpPost(url);
// 
//            // ** Alternative way to convert Person object to JSON string usin Jackson Lib 
//            // ObjectMapper mapper = new ObjectMapper();
//            // json = mapper.writeValueAsString(person); 
// 
//            // 5. set json to StringEntity
//            StringEntity se = new StringEntity(json);
// 
//            // 6. set httpPost Entity
//            httpPost.setEntity(se);
// 
//            // 7. Set some headers to inform server about the type of the content   
//            httpPost.setHeader("Accept", "application/json");
//            httpPost.setHeader("Content-type", "application/json");
// 
//            // 8. Execute POST request to the given URL
//            HttpResponse httpResponse = httpclient.execute(httpPost);
// 
//            // 9. receive response as inputStream
//            inputStream = httpResponse.getEntity().getContent();
// 
//            // 10. convert inputstream to string
//            if(inputStream != null)
//                result = convertInputStreamToString(inputStream);
//            else
//                result = "Did not work!";
// 
//        } catch (Exception e) {
//            Log.d("InputStream", e.getLocalizedMessage());
//        }
// 
//        // 11. return result
//        return result;
//    }
//	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        String line = "";
//        String result = "";
//        while((line = bufferedReader.readLine()) != null)
//            result += line;
// 
//        inputStream.close();
//        return result;
// 
//    }
//	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... urls) {
//            return POST(urls[0],daftarWishlist.get(daftarWishlist.size()-1));
//        }
//        // onPostExecute displays the results of the AsyncTask.
//        @Override
//        protected void onPostExecute(String result) {
//            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
//       }
//    }
	
	/**
	 * Method untuk Mengirimkan data ke server
	 * event by button login diklik
	 *
	 * @param view
	 */
	public void getRequest(String SUrl){
       Log.d("getRequest","haha "+ SUrl);
        HttpClient client = new DefaultHttpClient();
        Log.d("getRequest","haha 2"+ SUrl);
        try{
        	HttpGet request = new HttpGet(SUrl);
            HttpResponse response = client.execute(request);
            Log.d("getRequest","haha");
        }catch(Exception ex){
        	Log.d("getRequest","haha "+ex.toString());
        }

    }
//	
//	/**
//	 * Method untuk Menerima data dari server
//	 * @param response
//	 * @return
//	 */
//	public static String request(HttpResponse response){
//        String result = "";
//        try{
//            InputStream in = response.getEntity().getContent();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            StringBuilder str = new StringBuilder();
//            String line = null;
//            while((line = reader.readLine()) != null){
//                str.append(line + "\n");
//            }
//            in.close();
//            result = str.toString();
//        }catch(Exception ex){
//            result = "Error";
//        }
//        return result;
//    }
}

