package com.example.locationbomb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;













import org.json.JSONException;

import com.example.file.FileOperation;
import com.example.jsontools.JSONToolsAnalysis;
import com.example.sqlite.LocationDao;
import com.example.sqlite.LocationSQLite;












import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends Activity  implements OnClickListener {
    
	 protected static final String TAG = null;
	 private   String   ID;
  	 private   Button    uploadLocation;
  	 private   LocationSQLite   locationopenhelper = null;
  	 LocationDao      dao = null;    //对数据库进行各种操作
  	 private     int    hole_key = 0;       //主键
  	 private   Button    huoquall;
  	 private  List<Location>  filedatabefore= new ArrayList<Location>();  //先前读的
  	 private List<Location>  filedataafeter = new ArrayList<Location>();    //之后读的
  	 private  Boolean    flag  = true;           //第一次读
  	 private  int   sizebefore ;                              //先前的长度
  	 private  int   sizeafter  = 0;
  	 private  int   sizebemiddle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bmob.initialize(this,"13197bceaa649971f7f0655de655885e");
		setContentView(R.layout.activity_main);
		uploadLocation = (Button)findViewById(R.id.upload);
		huoquall = (Button)findViewById(R.id.getall);
		uploadLocation.setOnClickListener(this);
		huoquall.setOnClickListener(this);
		//new Thread(new read()).start();
		//获取sdcard中的数据	
		//创建或打开一个数据库，进行操作
    /*	locationopenhelper = new LocationSQLite(this);
		SQLiteDatabase  db = locationopenhelper.getWritableDatabase();
		//以读写的方式获得一个数据库实例，然后可以对它进行操作数据库用完要关闭*/
 	//	writetoLocationTable(1, "sdfsdf");
		  /*try {
		  //把已经存在的数据库加载到此应用中
		 SQLiteDatabase base  =  	SQLiteDatabase.openOrCreateDatabase( importDatabase()+ "/" + "location.db", null);
		//  writetoLocationTable("eee", 345.564, 3455.56);
		 new LocationDao().addinfo1(base, "erer", 345.456, 345.7567);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
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
	
	public   class   read  implements  Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			     while(true){
			    	    
			    	    	      try {
								    	uploadlocation(readfile("sdcard/hahah.txt"));
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			    	   try {
						Thread.sleep(1200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    
			     }
		}
		
	}
	 //查询表中所有数据，最多可显示1000组数据(bmob)
	public  void   findalldata(){
	        BmobQuery<Location> query = new BmobQuery<Location>();
	        query.findObjects(this	, new FindListener<Location>() {
				
				@Override
				public void onSuccess(List<Location> arg0) {
					// TODO Auto-generated method stub
					   Toast.makeText(getApplicationContext(), "查询成功共" + arg0.size(), 0).show();
					   Log.i(TAG, arg0.get(0).getOld_name());
				}
				
				@Override
				public void onError(int arg0, String arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "查询失败", 0).show();
				}
			});
	}
   //上传用户坐标(Bmob)
	public  void   uploadlocation( List<Location> upload){
		
		        if(flag){
		        	  for(int i = 0 ; i < upload.size(); i++){
		        		         upload.get(i).save(this,new SaveListener() {
									
									@Override
									public void onSuccess() {
										// TODO Auto-generated method stub
										Toast.makeText(getApplicationContext(), "添加成功", 0).show();
									}
									
									@Override
									public void onFailure(int arg0, String arg1) {
										// TODO Auto-generated method stub
										Toast.makeText(getApplicationContext(), "添加失败", 0).show();
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
									Toast.makeText(getApplicationContext(), "添加成功", 0).show();
								}
								
								@Override
								public void onFailure(int arg0, String arg1) {
									// TODO Auto-generated method stub
									Toast.makeText(getApplicationContext(), "添加失败", 0).show();
								}
							});
	        		         
	        	  }
		        }
		
		
	
	}
      //上传测试人名(bmob)
	public   void   uploadperson(){
		final Person  p2 = new Person();
		p2.setName("sdf");
		p2.setAddress("GFDGFDG");
		//异步提交
		p2.save(this, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "添加成功"+p2.getObjectId(), 0).show();
				ID = p2.getObjectId();
			    
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "添加失败", 0).show();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}




	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		   switch (v.getId()) {
		case R.id.upload:
			    //   uploadlocation();
			      break;
		case R.id.getall:
			    //   findalldata();
			       break;
		}
	}
	
	
	//向数据表中写入数据(SQLite)
			public   void   writetoLocationTable(String name , double latitude, double longtitude){
				dao = new LocationDao(this);
				dao.addinfo(name,latitude,longtitude);	  
			}
	
	 //加载数据库
	public  String importDatabase() throws IOException {
        // 存放数据库的目录
        String dirPath = "/data/data/com.example.locationbomb/databases";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        // 数据库文件
        File file = new File(dir, "location.db");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            // 加载需要导入的数据库
            InputStream is = this.getApplicationContext().getResources()
                    .openRawResource(R.raw.location);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffere = new byte[is.available()];
            is.read(buffere);
            fos.write(buffere);
            is.close();
            fos.close();
      
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
    return dirPath;
    }
}
