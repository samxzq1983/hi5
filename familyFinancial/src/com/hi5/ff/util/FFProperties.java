package com.hi5.ff.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FFProperties {

	private static Properties prop;

	private  FFProperties(){
		prop = new Properties();
		String propFileName = "ff.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Properties getProperties(){
		if(prop == null){
			new FFProperties();
		}
		return prop;

	}


}
