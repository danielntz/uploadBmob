package com.example.jsontools;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;






import com.example.locationbomb.Location;

import android.util.Log;

public class JSONToolsAnalysis {

	private static final String TAG = null;
	private  List<Location>  locate  = new ArrayList<Location>();
	

	public   JSONToolsAnalysis(){

	}

	//��������JSON(����keyֵ)
	public  void    analysejsonkey(String jsonstring) throws JSONException{

		   JSONObject  jsonobject  = new JSONObject(jsonstring);  //��������json�ַ���
		   JSONObject  json  = jsonobject.getJSONObject("Person"); //���key�����е�valueֵ
		   String  name  = json.getString("name");
		   String  sex  =  json.getString("sex");
		   int  age = json.getInt("age");
		   Log.i(TAG, name + sex + age);
		
	}
	//����JSON����(����keyֵ)
	public  void    analysejsonskey(String jsonstring) throws JSONException{
          JSONObject  jsonobject = new JSONObject(jsonstring);
          JSONArray   jsonarray = jsonobject.getJSONArray("Person");           //���key�����е�valueֵ
          for(int i = 0;i < jsonarray.length(); i++){
		    	 JSONObject   jsonobject2 =  jsonarray.getJSONObject(i);      //json�������ж��json����
		         String name  =   jsonobject2.getString("name");
		         String sex  = jsonobject2.getString("sex");
		          int age  =  jsonobject2.getInt("age");
		          Log.i(TAG, name + sex + age);
		     }
          
	}

	//��������JSON(������keyֵ)
	public  void    analysejsonnokey(String jsonstring) throws JSONException{
             JSONObject  json = new JSONObject(jsonstring);
             String  name  = json.getString("name");
  		     String  sex  =  json.getString("sex");
  		     int  age = json.getInt("age");
  		     Log.i(TAG, name + sex + age);
	}

	//����JSON����(������keyֵ)
	public  List<Location>    analysejsonsnokey(String jsonstring) throws JSONException{
                   
		    JSONArray  jsonarray =  new JSONArray(jsonstring);
		    Log.i(TAG, jsonarray.length() + "");
		    for(int i = 0 ; i < jsonarray.length(); i ++){
		    	
		    	  Location    collect = new Location();
		    	String s = String.valueOf(jsonarray.get(i));
		    	JSONObject  jsonobject = new JSONObject(s);
		    	 String  name  = jsonobject.getString("name");
	  		     double latitude  =  jsonobject.getDouble("latitude");
	  		     double longtitude = jsonobject.getDouble("longtitude");
	  		     collect.setOld_name(name);
	  		     collect.setLatitude(latitude);
	  		      collect.setLongtitude(longtitude);
	  		      locate.add(collect);
	  		    // Log.i(TAG, name +  latitude+ longtitude); 
		    }
		
		  return   locate;
	}
}
