package com.example.kemuseum;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.example.kemuseum.utils.MuseumManager;

public class ViewGambarBarangFullscreen extends Activity implements
		OnTouchListener {
	private ImageView field;
	private MuseumManager museumManager;

	// kebutuhan untuk transformasi gambar
	// implementasi dari Ed Burnette
	private Matrix matrix;
	private Matrix savedMatrix;

	private final String TAG = "gan ";
	private final int NONE = 0;
	private final int DRAG = 1;
	private final int ZOOM = 2;
	private int mode;

	private PointF start;
	private PointF mid;
	private float oldDist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_gambar_barang_fullscreen);

		field = (ImageView) findViewById(R.id.gambar_fullscreen);
		museumManager = MuseumManager.getMuseumManager();

		initTransformasi();
		initGambar();
	}

	void initTransformasi() {
		matrix = new Matrix();
		savedMatrix = new Matrix();

		mode = NONE;
		start = new PointF();
		mid = new PointF();
		oldDist = 1f;
	}

	void initGambar() {
		Bundle data = this.getIntent().getExtras();
		int idMuseum = data.getInt("idMuseum");
		String namaBerkas = data.getString("gambar");
		Drawable gambar = museumManager.getDrawableImage(idMuseum, namaBerkas);
		if (gambar != null) {
			field.setImageDrawable(gambar);
		} else {
			field.setImageResource(R.drawable.ic_launcher);
		}
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		float widthGap = size.x - gambar.getIntrinsicWidth();
		float heightGap = size.y - gambar.getIntrinsicHeight();
		
		matrix.setTranslate(widthGap/2, heightGap/2);
		field.setImageMatrix(matrix);
		field.setOnTouchListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_gambar_barang_fullscreen, menu);
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		ImageView view = (ImageView) v;

		// Dump touch event to log
		// dumpEvent(event);

		// Handle touch events here...
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			savedMatrix.set(matrix);
			start.set(event.getX(), event.getY());
			Log.d(TAG, "mode=DRAG");
			mode = DRAG;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(event);
			Log.d(TAG, "oldDist=" + oldDist);
			if (oldDist > 10f) {
				savedMatrix.set(matrix);
				midPoint(mid, event);
				mode = ZOOM;
				Log.d(TAG, "mode=ZOOM");
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			mode = NONE;
			Log.d(TAG, "mode=NONE");
			break;
		case MotionEvent.ACTION_MOVE:
			if (mode == DRAG) {
				// ...
				matrix.set(savedMatrix);
				matrix.postTranslate(event.getX() - start.x, event.getY()
						- start.y);
			} else if (mode == ZOOM) {
				float newDist = spacing(event);
				Log.d(TAG, "newDist=" + newDist);
				if (newDist > 10f) {
					matrix.set(savedMatrix);
					float scale = newDist / oldDist;
					matrix.postScale(scale, scale, mid.x, mid.y);
				}
			}
			break;
		}

		view.setImageMatrix(matrix);
		return true; // indicate event was handled
	}

	/** Determine the space between the first two fingers */
	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	/** Calculate the mid point of the first two fingers */
	private void midPoint(PointF point, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x / 2, y / 2);
	}
}
