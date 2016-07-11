package com.example.locationbomb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.example.file.FileOperation;
import com.example.jsontools.JSONToolsAnalysis;
import com.example.locationbomb.MainActivity.read;
import com.example.sqlite.LocationDao;
import com.example.sqlite.LocationSQLite;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Button;
import android.widget.Toast;

public class main_Service  extends Service{
   
	private   boolean     end  = true;
	private   boolean    endafter = true;
	protected static final String TAG = null;
	 private   String   ID;
 	 private   Button    uploadLocation;
 	 private   LocationSQLite   locationopenhelper = null;
 	 LocationDao      dao = null;    //锟斤拷锟斤拷菘锟斤拷锟叫革拷锟街诧拷锟斤拷
 	 private     int    hole_key = 0;       //锟斤拷锟斤拷
 	 private   Button    huoquall;
 	 private  List<Location>  filedatabefore= new ArrayList<Location>();  //锟斤拷前锟斤拷锟斤拷
 	 private List<Location>  filedataafeter = new ArrayList<Location>();    //之锟斤拷锟斤拷锟�
 	 private  Boolean    flag  = true;           //锟斤拷一锟轿讹拷
 	 private  int   sizebefore ;                              //锟斤拷前锟侥筹拷锟斤拷
 	 private  int   sizeafter  = 0;
 	 private  int   sizebemiddle;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Bmob.initialize(this,"13197bceaa649971f7f0655de655885e");
		new Thread(new read()).start();
	
		
		
	}
	
	public   class   read  implements  Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			     while(end || endafter){
			    	    
			    	    	      try {
								    	uploadlocation(readfile("sdcard/upload.txt"));
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			    	   try {
			    		   if(!end){
			    			   endafter = false;
			    		   }
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    
			     }
		}
		
	}
	
	public List<Location>   readfile(String path) throws JSONException{
        
	      if(flag){
		    String jsonarray  = 	    new FileOperation().ReadFile1(path);
	        filedatabefore =   new JSONToolsAnalysis().analysejsonsnokey(jsonarray);
	        flag = false;
	        sizebefore = filedatabefore.size();
	        return  filedatabefore;
	      }
	      else{
	    	      String jsonarray = new FileOperation().ReadFile1(path);
	    	      filedataafeter = new JSONToolsAnalysis().analysejsonsnokey(jsonarray);
	    	      sizebemiddle = sizebefore;
	    	      sizeafter =  filedataafeter.size();
	    	      sizebefore = sizeafter;
	    	      
	    	      return  filedataafeter;
	        }
	}
	//锟较达拷锟矫伙拷锟斤拷锟�Bmob)
		public  void   uploadlocation( List<Location> upload){
			
			        if(flag){
			        	  for(int i = 0 ; i < upload.size(); i++){
			        		         upload.get(i).save(this,new SaveListener() {
										
										@Override
										public void onSuccess() {
											// TODO Auto-generated method stub
											//Toast.makeText(getApplicationContext(), "锟斤拷映晒锟�, 0).show();
										}
										
										@Override
										public void onFailure(int arg0, String arg1) {
											// TODO Auto-generated method stub
											Toast.makeText(getApplicationContext(), "锟斤拷锟绞э拷锟�", 0).show();
										}
									});
			        		         
			        	  }
			        }
			        else{
			        	  for(int i = sizebemiddle ; i < upload.size(); i++){
		        		         upload.get(sizebemiddle).save(this,new SaveListener() {
									
									@Override
									public void onSuccess() {
										// TODO Auto-generated method stub
									//	Toast.makeText(getApplicationContext(), "锟斤拷映晒锟�, 0).show();
									}
									
									@Override
									public void onFailure(int arg0, String arg1) {
										// TODO Auto-generated method stub
										Toast.makeText(getApplicationContext(), "锟斤拷锟绞э拷锟�", 0).show();
									}
								});
		        		         
		        	  }
			        }
			
			
		
		}
	 public void onStart(Intent intent, int startId) {
		 
		 
	 };
	 
	 
	 @Override
	public void onDestroy() {
		 //用于終止線程
		// TODO Auto-generated method stub
		super.onDestroy();
		
		end = false;
		//把bmob表上的數據刪除掉
	/*	Location   location = new Location();
		location.removeAll("old_name",Arrays.asList("小明"));
		location.update(this, new UpdateListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				  Toast.makeText(getApplicationContext(), "刪除成功", 0).show();
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "刪除失敗", 0).show();
			}
		});*/
	}
}
