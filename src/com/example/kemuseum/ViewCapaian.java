package com.example.kemuseum;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.kemuseum.controller.ControllerCapaian;
import com.example.kemuseum.model.Capaian;
import com.example.kemuseum.utils.ExpandableListAdapterCapaian;
import com.example.kemuseum.utils.FacebookListener;
import com.example.kemuseum.utils.FacebookManager;
import com.example.kemuseum.utils.MuseumManager;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

public class ViewCapaian extends Activity {
	public static final String BUNDLE_NAMA_MUSEUM = "namaMuseum";
	public static final String BUNDLE_PRESENTASE = "presentase";

	public static final String SHARE_NAMA = "KeMuseum";
	public static final String SHARE_CAPTION = "";
	public static final String SHARE_DESKRIPSI = "";
	public static final String SHARE_LINK = "https://github.com/abdullahizzuddiin/KeMuseum";
	public static final String SHARE_URL_GAMBAR = "https://cdn1.iconfinder.com/data/icons/New-Social-Media-Icon-Set-V11/512/facebook.png";

	private TextView tvTop;
	private ControllerCapaian controller;
	private ExpandableListAdapterCapaian expandableAdapter;
	private ExpandableListView expandableListView;
	private FacebookManager facebookManager;
	private FacebookListener facebookListener;
	private final Activity host = this;

	// FACEBOOK BUTUH INI
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode,
				resultCode, data);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_capaian);
		inisiasi();
		isiData();
	}

	private void connectFB() {
		// start Facebook Login
		Session.openActiveSession(this, true, new Session.StatusCallback() {

			// callback when session changes state

			@Override
			public void call(Session session, SessionState state,
					Exception exception) {
				if (session.isOpened()) {

					// make request to the /me API
					// Request.executeMeRequestAsync(session,
					new Request.GraphUserCallback() {

						// callback after Graph API response with user // object

						@Override
						public void onCompleted(GraphUser user,
								Response response) {
							if (user != null) {

								Log.d("asd", "gan " + user.getName() + "!");
							}
						}
					};
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_info_capaian, menu);
		return true;
	}

	public void inisiasi() {
		controller = new ControllerCapaian();
		expandableListView = (ExpandableListView) findViewById(R.id.list_pencapaian);
		facebookManager = new FacebookManager();
		facebookListener = new FacebookListener();
		tvTop = (TextView) findViewById(R.id.tvTop);
		Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/ubuntu.ttf");
		tvTop.setTypeface(font);
		
	}

	private void isiData() {
		List<Capaian> daftarCapaian = controller.getDaftarCapaian();

		expandableAdapter = new ExpandableListAdapterCapaian(this,
				daftarCapaian, this);
		expandableListView.setAdapter(expandableAdapter);
	}

	public void share(Bundle data) {
		showDialog(0, data);
	}

	protected Dialog onCreateDialog(int id, final Bundle data) {
		switch (id) {
		case 0: {
			LayoutInflater inflater = LayoutInflater.from(this);
			View inflated = inflater.inflate(R.layout.activity_dialog_share,
					null);

			Button shareTwitter = (Button) inflated
					.findViewById(R.id.tombol_share_twitter);
			Button shareFacebook = (Button) inflated
					.findViewById(R.id.tombol_share_facebook);

			shareTwitter.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					String url = "http://www.twitter.com/intent/tweet?text=Aku sudah "
							+ data.getString(BUNDLE_PRESENTASE)
							+ " persen dalam menjelajahi "
							+ data.getString(BUNDLE_NAMA_MUSEUM) + " :)";
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					MuseumManager.getMuseumManager().getContext()
							.startActivity(i);

				}
			});
			shareFacebook.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					try {
						connectFB();
						facebookManager.updateStatus(SHARE_NAMA, SHARE_CAPTION,
								SHARE_DESKRIPSI, SHARE_LINK, SHARE_URL_GAMBAR,
								host, facebookListener);
					} catch (Exception e) {
						Log.d("asd", "gan " + e.toString());
					}
				}
			});

			OnTouchListener c = new OnTouchListener() {
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					return false;
				}
			};

			return new AlertDialog.Builder(this).setView(inflated).create();
		}

		}
		return null;
	}
}
