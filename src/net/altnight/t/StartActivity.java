package net.altnight.t;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthSupport;
import twitter4j.auth.RequestToken;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends Activity {
	// put your keys
	final private String consumerKey = "";
	final private String consumerSecret = "";
	final String accessToken = "";
	final String accessTokenSecret = "";
	final String CALLBACK_URL = "myapp://oauth";

	private final String CONNECTED = "";

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button tweet = (Button) findViewById(R.id.sendButton);
		tweet.setOnClickListener(new TweetButton());

		SharedPreferences.Editor editor = (Editor) getSharedPreferences(
				"status", MODE_PRIVATE);

		if ((isConnected(CONNECTED)) || (editor.equals("enabled"))) {
			// pass
		} else {
			try {
				connectOAuth();
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		}

	}

	private void connectOAuth() throws TwitterException {
		// SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
		// String oatuhToken = pref.getString("oauth_token", "");
		// String oauthTokenSecret = pref.getString("oauth_token_secret", "");
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		RequestToken requestToken = twitter.getOAuthRequestToken();
		AccessToken accessToken = null;
		// requestToken = twitter.getOAuthRequestToken(CALLBACK_URL);
		// accessToken = twitter.getOAuthAccessToken(requestToken);
		String str = requestToken.getAuthorizationURL();
		Uri uri = Uri.parse(str);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivityForResult(intent, 0);
		// String CONNECTED = "enabled";
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			super.onActivityResult(requestCode, resultCode, data);

			AccessToken accessToken = null;
			OAuthSupport twitter = null;
			RequestToken requestToken = null;
			Intent intent = null;
			try {
				accessToken = twitter.getOAuthAccessToken(requestToken, intent
						.getExtras().getString("oauth_verifier"));
			} catch (TwitterException e) {
				e.printStackTrace();
			}

			SharedPreferences pref = getSharedPreferences("Twitter_seting",
					MODE_PRIVATE);

			SharedPreferences.Editor editor = pref.edit();
			editor.putString("oauth_token", accessToken.getToken());
			editor.putString("oauth_token_secret", accessToken.getTokenSecret());
			editor.putString("status", "enabled");

			editor.commit();

		}

	}

	private boolean isConnected(String CONNECTED) {
		if (CONNECTED.equals("enabled")) {
			return true;
		} else {
			return false;
		}
	}

	public class TweetButton implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			Toast.makeText(StartActivity.this, "送信したい……", Toast.LENGTH_SHORT)
					.show();
			try {
				tweet();
			} catch (TwitterException e) {
				e.printStackTrace();
			}

		}
		// finish();

	}

	public void tweet() throws TwitterException {

		EditText tweetText = (EditText) findViewById(R.id.tweetText);
		// tweetText.setText("defalut");
		tweetText.setHint("hit-a-hint");
		tweetText.setMaxLines(3);

		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		// twitter.setOAuthAccessToken(accessToken);

		SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
		String oatuhToken = pref.getString("oauth_token", "");
		String oauthTokenSecret = pref.getString("oauth_token_secret", "");
		// Configuration config = new ConfigurationBuilder().build();

		// CONSUMER KEYとCONSUMER SECRET、
		// access_tokenとaccess_token_secretを使って、
		// twitterインスタンスを取得する。
		// Twitter twitter = new TwitterFactory(config)
		// .getInstance(new OAuthAuthorization(config, CONSUMER_KEY,
		// CONSUMER_SECRET, new AccessToken(oauthToken,
		// oauthTokenSecret)));
		twitter.setOAuthAccessToken(new AccessToken(accessToken,
				accessTokenSecret));

		twitter.updateStatus(tweetText.getText().toString());
		Toast.makeText(this, "送信したかもしれないです！", Toast.LENGTH_LONG).show();

	}
}
