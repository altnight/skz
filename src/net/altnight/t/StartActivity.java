package net.altnight.t;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends Activity {
	// put your keys
	final String consumerKey = "";
	final String consumerSecret = "";
	final String accessToken = "";
	final String accessTokenSecret = "";

	private static final boolean CONNECTED = false;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button tweet = (Button) findViewById(R.id.sendButton);
		tweet.setOnClickListener(new TweetButton());

		if (isConnected(CONNECTED)) {

		} else {
			try {
				connectOAuth();
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		}

	}

	private void connectOAuth() throws TwitterException {
		SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
		String oatuhToken = pref.getString("oauth_token", "");
		String oauthTokenSecret = pref.getString("oauth_token_secret", "");
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.getOAuthRequestToken();
		twitter.getAuthorization();
	}

	private boolean isConnected(boolean CONNECTED) {
		if (CONNECTED) {
			CONNECTED = true;
			return true;
		} else {
			CONNECTED = false;
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
		tweetText.setMaxLines(2);

		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(accessToken,
				accessTokenSecret));

		twitter.updateStatus(tweetText.getText().toString());
		Toast.makeText(this, "送信したかもしれないです！", Toast.LENGTH_LONG).show();

	}
}
