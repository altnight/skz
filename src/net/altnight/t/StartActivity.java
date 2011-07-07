package net.altnight.t;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.app.Activity;
import android.os.Bundle;

public class StartActivity extends Activity {
    /** Called when the activity is first created. */
	
	//twitter api key
	private String CONSUMER_KEY = "";
	private String CONSUMER_SECRET = "";
	
	private AccessToken accessToken = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        try {
			tweet();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		finish();
    }
    
    public void tweet() throws TwitterException{
    	//tweet‚·‚é
    	Twitter twitter = new TwitterFactory().getInstance();
    	twitter.updateStatus("testhogera------");
    	
    }
}
