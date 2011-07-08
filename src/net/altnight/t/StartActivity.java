package net.altnight.t;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class StartActivity extends Activity {
	/** Called when the activity is first created. */

	// twitter api key
	private String CONSUMER_KEY = "";
	private String CONSUMER_SECRET = "";

	private AccessToken accessToken = null;

	private final boolean CONNECTED = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		if (isConnected()) {
			try {
				tweet();
			} catch (TwitterException e) {
				e.printStackTrace();
			}
			finish();

		} else {
			try {
				connectOAuth();
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		}
	}

	private void connectOAuth() throws TwitterException {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.getOAuthRequestToken();
		twitter.getAuthorization();
	}
	private boolean isConnected() {
		if (CONNECTED){
			return true;
		} else{
			return false;
		}
	}

	public void tweet() throws TwitterException {
		// tweeting
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.updateStatus("testhogera------");

	}
}
