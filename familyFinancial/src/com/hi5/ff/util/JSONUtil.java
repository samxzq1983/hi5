package com.hi5.ff.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hi5.ff.entity.User;

public class JSONUtil {

	public static JSONArray genJSONArray(ArrayList<Object> objArray) throws JSONException{
		JSONArray ja = new JSONArray();
		if(objArray.isEmpty()){
			return ja;
		}
		Object rawObj = objArray.get(0);

		if(rawObj instanceof User){
			for(Object userObj:objArray){
				User user = (User) userObj;
				JSONObject userJson = new JSONObject();
				userJson.put("id", user.getUserId());
				userJson.put("profileId", user.getProfileId());
				userJson.put("name", user.getUseName());
				ja.put(user);
			}
		}

		return ja;
	}



	public static void main(String args[]) throws JSONException{
		JSONObject jo = new JSONObject();

		jo.put("id", 1);
		jo.put("price", "$1");

		JSONArray ja = new JSONArray();
		ja.put(jo);

		JSONObject joM = new JSONObject();
		joM.put("",ja);
		System.out.println(ja.toString());


	}

}
