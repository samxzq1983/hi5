package com.hi5.ff.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hi5.ff.dao.ProfileDao;
import com.hi5.ff.entity.Category;
import com.hi5.ff.entity.Profile;
import com.hi5.ff.entity.User;

public class JSONUtil {

	public static JSONArray genJSONArray(List objArray) throws JSONException{
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
				userJson.put("profileName", new ProfileDao().getProfileNameById(user.getProfileId()));
				userJson.put("name", user.getUseName());
				ja.put(userJson);
			}
		}else if(rawObj instanceof Profile){
			for(Object profileObj:objArray){
				Profile profile = (Profile) profileObj;
				JSONObject profileJson = new JSONObject();
				profileJson.put("id", profile.getProfileId());
				profileJson.put("name", profile.getProfileName());
				profileJson.put("desc", profile.getProfileDesc());
				ja.put(profileJson);
			}
		}else if(rawObj instanceof Category){
			for(Object categoryObj:objArray){
				Category category = (Category) categoryObj;
				JSONObject categoryJson = new JSONObject();
				categoryJson.put("id", category.getCategoryId());
				categoryJson.put("name", category.getCategoryName());
				ja.put(categoryJson);
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
