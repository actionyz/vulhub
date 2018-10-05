package test;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
public class unserialize {
	public static void main(String[] args) throws Exception {
		FileInputStream in;
		try {
			in = new FileInputStream("bbb");
			ObjectInputStream ins = new ObjectInputStream(in);
			ins.readObject();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

