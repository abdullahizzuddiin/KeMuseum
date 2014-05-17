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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
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
	
	private String url = "http://kawung.cs.ui.ac.id/~abdullah.izzuddin/";
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
						int id = controllerWishlist.getWishlist().size();
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

