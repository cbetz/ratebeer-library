package com.cbetz.RateBeer.parsers;

import org.json.JSONArray;
import org.json.JSONException;

import com.cbetz.RateBeer.types.Beer;

public class BeerParser {
	public Beer parse(JSONArray array) throws JSONException {
		Beer beer = new Beer(
				array.getJSONObject(0).getInt("BeerID"),
				array.getJSONObject(0).getString("BeerName"));
		return beer;
	}
}
