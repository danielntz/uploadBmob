package com.example.sqlite;

import java.util.ArrayList;
import java.util.List;



import com.example.locationbomb.Location;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LocationDao {
     
	  private  LocationSQLite   locateopener = null;
	  
	  public  LocationDao(){
		  
	  }
	
	  public LocationDao(Context context){
		        locateopener  = new LocationSQLite(context);        
	  }
	  
	  //���������һ����¼
	  public  long   addinfo( String old_name , double latitude ,double longtitude){
  	    SQLiteDatabase  db = locateopener.getWritableDatabase();
  	    ContentValues content = new ContentValues();
       //��ֵ�ԣ������е����������ݿ���е����ԣ�����ͱ��е�������ͬ����������ҲҪ��ͬ����
  	    //  content.put("id", id);
          content.put("name", old_name);   
          content.put("latitude",latitude);
          content.put("longtitude", longtitude);
          long id1 = db.insert("Location", null, content);
          return id1;
  }
	  
	  //���������һ����¼
	  public  long   addinfo1(  SQLiteDatabase base, String old_name , double latitude ,double longtitude){
  	   // SQLiteDatabase  db = locateopener.getWritableDatabase();
  	    ContentValues content = new ContentValues();
       //��ֵ�ԣ������е����������ݿ���е����ԣ�����ͱ��е�������ͬ����������ҲҪ��ͬ����
  	    //  content.put("id", id);
          content.put("name", old_name);   
          content.put("latitude",latitude);
          content.put("longtitude", longtitude);
          long id1 = base.insert("Location", null, content);
          return id1;
  }
	  //���ұ������м�¼
	/*  public   List<Location> findall(){
  	    SQLiteDatabase db = locateopener.getReadableDatabase();
  	    Cursor cursor = db.query("hero", new String[]{"id","name","level","power","intelligence","life"},null,null, null, null, null);
         List<Location> list = new  ArrayList<Location>();
         while(cursor.moveToNext()){
      	   int id = cursor.getInt(cursor.getColumnIndex("id"));
      	   String name = cursor.getString(cursor.getColumnIndex("name"));
      	   int  level = cursor.getInt(cursor.getColumnIndex("level"));
      	   String power = cursor.getString(cursor.getColumnIndex("power"));
      	   String  intelligence = cursor.getString(cursor.getColumnIndex("intelligence"));
      	   String   life = cursor.getString(cursor.getColumnIndex("life"));
      	   Location   p = new Location(id,name,level,power,intelligence,life);
      	   list.add(p);
         }
         db.close();
			cursor.close();
			return   list;
  }*/
	  
	  
	  //ɾ�����е�һ����¼
	    public    void  delete(String name ){
	    	SQLiteDatabase db = locateopener.getReadableDatabase();
	    	String  sql  =  "delete from hero where name=?";
	    	db.execSQL(sql,new Object[]{name});
	    }
	   //ɾ�����е����м�¼
	    public   void   delete(){
	    	SQLiteDatabase  db =  locateopener.getReadableDatabase();
	    	String sql = "delete from hero ";
	    	db.execSQL(sql);
	    }
	    
	    
	   
}
