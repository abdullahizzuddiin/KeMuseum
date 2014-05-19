package com.example.kemuseum;

import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kemuseum.controller.ControllerWishlist;
import com.example.kemuseum.model.Wishlist;
import com.example.kemuseum.utils.ArrayAdapterWishlist;
import com.rogerlemmonapps.captcha.Captcha;

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
	
	private AlertDialog dialogCaptcha;
	private Captcha captchaGenerator;
	
	// keperluan dialog
	private ImageView gambarCaptcha;
	private Button tombolKumpul;
	private EditText jawabanPengguna;

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
		
		captchaGenerator = controllerWishlist.getCaptcha();
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
				// tunjukkan captcha dulu
				showDialog(0);				

			}
		});
	}

	protected Dialog onCreateDialog(int id) {
		final ViewWishlist host = this;
		switch (id) {
		case 0: {
			LayoutInflater inflater = LayoutInflater.from(this);
			View inflated = inflater.inflate(R.layout.activity_dialog_captcha,
					null);
			
			gambarCaptcha = (ImageView) inflated.findViewById(R.id.gambar_captcha);
			tombolKumpul = (Button) inflated.findViewById(R.id.tombol_konfirmasi_captcha);
			jawabanPengguna = (EditText) inflated.findViewById(R.id.isian_captcha);
			
			gambarCaptcha.setImageBitmap(captchaGenerator.getImage());

			tombolKumpul.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					boolean lulusCaptcha = captchaGenerator.checkAnswer(jawabanPengguna.getText().toString());
					dialogCaptcha.dismiss();
					
					String pesan;
					if (lulusCaptcha){ 
						// TODO Auto-generated method stub
						int id = controllerWishlist.getWishlist().size();
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
//							url +="?json="+URLEncoder.encode(json);
//							getRequest(url);
						try {
							postData(json);
						} catch (Exception e) {
							// TODO: handle exception
						}
						text_alamatEmail.setText("");
						text_namaPengirim.setText("");
						text_namaWishlist.setText("");
						
						pesan = "Wishlist tersimpan!";
					}else{
						pesan = "Wishlist gagal tersimpan!";
					}
					
					Context context = getApplicationContext();
					Toast toast = Toast.makeText(context, pesan, Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.TOP, 0, 70);
					toast.show();
				}
			});

			OnTouchListener c = new OnTouchListener() {
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					return false;
				}
			};
			
			dialogCaptcha = new AlertDialog.Builder(this).setView(inflated).create();
			return dialogCaptcha;
		}

		}
		return null;
	}
	protected void onPrepareDialog(int id, Dialog dialog) {
	    switch(id) {
	    case 0:
			captchaGenerator.refresh();
			jawabanPengguna.setText("");
			gambarCaptcha.setImageBitmap(captchaGenerator.getImage());
	    	
	        break;
	    }
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
}

