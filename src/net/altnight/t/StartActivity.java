package net.altnight.t;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.app.Activity;
import android.os.Bundle;

public class StartActivity extends Activity {
	/** Called when the activity is first created. */

	// twitter api key
	// private String CONSUMER_KEY = "";
	// private String CONSUMER_SECRET = "";

	// private Twitter twitter = new TwitterFactory().getInstance();

	// private AccessToken accessToken = null;
	// private RequestToken reqestToken = null;
	// private boolean CONNECTED = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// if (isConnected(CONNECTED)) {
		// try {
		// tweet();
		// } catch (TwitterException e) {
		// e.printStackTrace();
		// }
		// finish();
		//
		// } else {
		// try {
		// connectOAuth();
		// } catch (TwitterException e) {
		// e.printStackTrace();
		// }
		// }
	}

	// private void connectOAuth() throws TwitterException {
	// SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
	// String oatuhToken = pref.getString("oauth_token", "");
	// String oauthTokenSecret = pref.getString("oauth_token_secret", "");
	// Twitter twitter = new TwitterFactory().getInstance();
	// twitter.getOAuthRequestToken();
	// twitter.getAuthorization();
	// }

	// private boolean isConnected(boolean CONNECTED) {
	// if (CONNECTED) {
	// CONNECTED = true;
	// return true;
	// } else {
	// CONNECTED = false;
	// return false;
	// }
	// }

	public void tweet() throws TwitterException {
		// tweeting
		final String consumerKey = "";
		final String consumerSecret = "";
		final String accessToken = "";
		final String accessTokenSecret = "";

		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
		twitter.updateStatus("もう一個テスト");

	}
}
