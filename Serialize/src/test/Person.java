package test;

import java.io.IOException;
import java.io.Serializable;
import java.security.KeyStore.Entry;
import java.util.Map;

public class Person implements Serializable{
	private String name;
	

	public Map map;
	
	private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException,IOException{
		in.defaultReadObject();
		
		if(map != null){
			Map.Entry a = (Map.Entry) map.entrySet().iterator().next();
			a.setValue("400m");
		}
	}
}
