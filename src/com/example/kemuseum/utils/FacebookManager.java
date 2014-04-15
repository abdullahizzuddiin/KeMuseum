package com.example.kemuseum.utils;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.Session;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.SessionState;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

public class FacebookManager {
	private Activity mActivity;

	public void updateStatus(final String name, final String caption,
			final String description, final String link,
			final String urlPicture, final Activity activity,
			final ListenerShareFacebook listenerShareFacebook) {
		mActivity = activity;

		// start Facebook Login
		Session.openActiveSession(activity, true, new Session.StatusCallback() {
			// callback when session changes state
			public void call(final Session session, SessionState state,
					Exception exception) {
				Log.d("asd", "gan " + session + " " + state + " " + exception);
				if (session.isOpened()) {

					publishFeedDialog(name, caption, description, link,
							urlPicture, listenerShareFacebook);
						
				}

			}
		});
	}

	private void publishFeedDialog(String name, String caption,
			String description, String link, String urlPicture,
			final ListenerShareFacebook listenerShareFacebook) {
		Bundle params = new Bundle();
		params.putString("name", name);
		params.putString("caption", caption);
		params.putString("description", description);
		params.putString("link", link);
		params.putString("picture", urlPicture);

		Session session = Session.getActiveSession();

		WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(mActivity,
				session, params)).setOnCompleteListener(
				new OnCompleteListener() {
					public void onComplete(Bundle values,
							FacebookException error) {
						if (error == null) {
							// When the story is posted, echo the success
							// and the post Id.
							final String postId = values.getString("post_id");
							if (postId != null) {
								listenerShareFacebook.onShareFacebookSuccess();
							} else {
								// User clicked the Cancel button
								listenerShareFacebook
										.onShareFacebookFailure("Publish cancelled");
							}
						} else if (error instanceof FacebookOperationCanceledException) {
							// User clicked the "x" button
							listenerShareFacebook
									.onShareFacebookFailure("Publish cancelled");
						} else {
							// Generic, ex: network error
							listenerShareFacebook
									.onShareFacebookFailure("Error posting story");
						}
					}

				}).build();
		feedDialog.show();
	}
}