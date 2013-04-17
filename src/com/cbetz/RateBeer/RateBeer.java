package com.cbetz.RateBeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.util.Log;

import com.cbetz.RateBeer.parsers.BeerParser;
import com.cbetz.RateBeer.types.Beer;


public class RateBeer {
	private static final String API_ENDPOINT = "http://www.ratebeer.com";
	private static final String BEER_UPC = "/json/upc.asp";	
	private String key;
	
	public RateBeer(String key) {
		setKey(key);
	}
	
	public Beer beerSearchByUPC(String upc) {
		Beer beer = null;
		
		
		JSONArray array = getResponse(BEER_UPC + "?k=" + key + "&upc=" + upc);
		try {
			beer = new BeerParser().parse(array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		
		
		return beer;
	}
	
	private JSONArray getResponse(String url) /*throws UntappdException*/ {
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse httpresponse;
		JSONArray array = null;
		try {
			HttpGet httpget = new HttpGet(API_ENDPOINT + url);
			
/*			//add the authentication parameters to the header 
			String header;
			try {
				header = Base64.encodeToString((username + ":" + Util.md5(password)).getBytes("UTF-8"), Base64.NO_WRAP);
				httpget.addHeader("Authorization", "Basic " + header);
			} catch (UnsupportedEncodingException e) {
				Log.e("getResponse", e.getStackTrace().toString());
			}*/
			
			httpresponse = httpclient.execute(httpget);
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpresponse.getEntity().getContent(), "UTF-8"));
			StringBuilder builder = new StringBuilder();
			for (String line = null; (line = reader.readLine()) != null;) {
				builder.append(line).append("\n");
			}
			JSONTokener tokener = new JSONTokener(builder.toString());
			array = new JSONArray(tokener);
/*			if (object.getInt("http_code") == 401) {
				throw new UntappdCredentialsException(object.getString("error"));
			} else if (object.getInt("http_code") != 200) {
				throw new UntappdException(object.getString("error"));
			}*/
			return array;
		} catch (ClientProtocolException e) {
			Log.e("getResponse", e.getStackTrace().toString());
		} catch (IOException e) {
			Log.e("getResponse", e.getStackTrace().toString());
		} catch (JSONException e) {
			Log.e("getResponse", e.getStackTrace().toString());
		} 
		
		return array;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	public String getKey() {
		return key;
	}
}
