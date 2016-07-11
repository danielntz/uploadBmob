package com.example.locationbomb;

import cn.bmob.v3.BmobObject;

public class Person  extends BmobObject {
 
	      private   String name;
	      private  String  address;
		  
	      
	      public Person(String name, String address) {
			super();
			this.name = name;
			this.address = address;
		}
	      
	      public   Person(){
	    	  
	      }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
	
	      
	
	
}
