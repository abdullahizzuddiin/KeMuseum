package com.example.kemuseum;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.kemuseum.controller.ControllerWishlist;
import com.example.kemuseum.model.Wishlist;
import com.example.kemuseum.utils.ArrayAdapterWishlist;
import com.example.kemuseum.utils.WishlistManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ViewWishlist extends Activity {
	private EditText text_namaWishlist, text_namaPengirim, text_alamatEmail;
	private Button buttKirim;
	private ListView listViewWishlist;
	
	private String url = "http://kawung.cs.ui.ac.id/~abdullah.izzuddin/";
	private ControllerWishlist controllerWishlist;
	private List<Wishlist> daftarWishlist;
	private ArrayAdapterWishlist arrayAdapter;
	private Calendar c;
	private int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_wishlist);
		inisiasi();
		setClickListener();
		isiData();
		
		
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
		
		controllerWishlist = new ControllerWishlist();
		c = Calendar.getInstance();
	}
	
	public void setClickListener() {
		buttKirim.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				id = controllerWishlist.getWishlist().size();
				String tanggal = c.get(Calendar.DATE)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR);
				String nama = text_namaPengirim.getText().toString();
				String email = text_alamatEmail.getText().toString();
				String deskripsi = text_namaWishlist.getText().toString();
				Log.d("sa", "hehe"+" "+tanggal+" "+nama+" "+email+" "+deskripsi);
				
				Wishlist w = new Wishlist(id, tanggal, nama, email, deskripsi);
				controllerWishlist.tambahWishlist(w);
				arrayAdapter.notifyDataSetChanged();
//				isiData();
//				url="http://kawung.cs.ui.ac.id/~abdullah.izzuddin/museum.php";
//            	url +="?user="+text_namaWishlist.getText().toString();
//                getRequest(text_namaWishlist,url);
			}
		});
	}
	/**
	 * Method untuk Mengirimkan data ke server
	 * event by button login diklik
	 *
	 * @param view
	 */
	public void getRequest(EditText txtResult, String SUrl){
       Log.d("getRequest",url);
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(SUrl);
        try{
            HttpResponse response = client.execute(request);
            txtResult.setText(request(response));
        }catch(Exception ex){
            txtResult.setText("Failed Connect to server!");
        }

    }
	
	/**
	 * Method untuk Menerima data dari server
	 * @param response
	 * @return
	 */
	public static String request(HttpResponse response){
        String result = "";
        try{
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();
        }catch(Exception ex){
            result = "Error";
        }
        return result;
    }
}

