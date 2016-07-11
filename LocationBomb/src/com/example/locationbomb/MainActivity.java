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
  	 LocationDao      dao = null;    //�����ݿ���и��ֲ���
  	 private     int    hole_key = 0;       //����
  	 private   Button    huoquall;
  	 private  List<Location>  filedatabefore= new ArrayList<Location>();  //��ǰ����
  	 private List<Location>  filedataafeter = new ArrayList<Location>();    //֮�����
  	 private  Boolean    flag  = true;           //��һ�ζ�
  	 private  int   sizebefore ;                              //��ǰ�ĳ���
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
		//��ȡsdcard�е�����	
		//�������һ�����ݿ⣬���в���
    /*	locationopenhelper = new LocationSQLite(this);
		SQLiteDatabase  db = locationopenhelper.getWritableDatabase();
		//�Զ�д�ķ�ʽ���һ�����ݿ�ʵ����Ȼ����Զ������в������ݿ�����Ҫ�ر�*/
 	//	writetoLocationTable(1, "sdfsdf");
		  /*try {
		  //���Ѿ����ڵ����ݿ���ص���Ӧ����
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
	 //��ѯ�����������ݣ�������ʾ1000������(bmob)
	public  void   findalldata(){
	        BmobQuery<Location> query = new BmobQuery<Location>();
	        query.findObjects(this	, new FindListener<Location>() {
				
				@Override
				public void onSuccess(List<Location> arg0) {
					// TODO Auto-generated method stub
					   Toast.makeText(getApplicationContext(), "��ѯ�ɹ���" + arg0.size(), 0).show();
					   Log.i(TAG, arg0.get(0).getOld_name());
				}
				
				@Override
				public void onError(int arg0, String arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "��ѯʧ��", 0).show();
				}
			});
	}
   //�ϴ��û�����(Bmob)
	public  void   uploadlocation( List<Location> upload){
		
		        if(flag){
		        	  for(int i = 0 ; i < upload.size(); i++){
		        		         upload.get(i).save(this,new SaveListener() {
									
									@Override
									public void onSuccess() {
										// TODO Auto-generated method stub
										Toast.makeText(getApplicationContext(), "��ӳɹ�", 0).show();
									}
									
									@Override
									public void onFailure(int arg0, String arg1) {
										// TODO Auto-generated method stub
										Toast.makeText(getApplicationContext(), "���ʧ��", 0).show();
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
									Toast.makeText(getApplicationContext(), "��ӳɹ�", 0).show();
								}
								
								@Override
								public void onFailure(int arg0, String arg1) {
									// TODO Auto-generated method stub
									Toast.makeText(getApplicationContext(), "���ʧ��", 0).show();
								}
							});
	        		         
	        	  }
		        }
		
		
	
	}
      //�ϴ���������(bmob)
	public   void   uploadperson(){
		final Person  p2 = new Person();
		p2.setName("sdf");
		p2.setAddress("GFDGFDG");
		//�첽�ύ
		p2.save(this, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "��ӳɹ�"+p2.getObjectId(), 0).show();
				ID = p2.getObjectId();
			    
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "���ʧ��", 0).show();
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
	
	
	//�����ݱ���д������(SQLite)
			public   void   writetoLocationTable(String name , double latitude, double longtitude){
				dao = new LocationDao(this);
				dao.addinfo(name,latitude,longtitude);	  
			}
	
	 //�������ݿ�
	public  String importDatabase() throws IOException {
        // ������ݿ��Ŀ¼
        String dirPath = "/data/data/com.example.locationbomb/databases";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        // ���ݿ��ļ�
        File file = new File(dir, "location.db");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            // ������Ҫ��������ݿ�
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
