package s181217.src;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;



public class Ex18_ObjectInputStream {

	public static void main(String[] args) {
		String filename="UserData.txt";
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream in = null;
		
		try {
			 fis = new FileInputStream(filename);
			 bis = new BufferedInputStream(fis);
			 in = new ObjectInputStream(bis);
			 
			 
			 
			/* Object users =null;
			 while((users = in.readObject()) != null) {
				System.out.println(users); 
			 }*/
			
			 UserInfo r1 = (UserInfo)in.readObject();
			 UserInfo r2 = (UserInfo)in.readObject();
			 //UserInfo r3 = (UserInfo)in.readObject();
			 //UserInfo r4 = (UserInfo)in.readObject();
			 System.out.println("복원 객체 : " + r1.toString());
			 System.out.println("복원 객체 :" + r2.toString());
			 //System.out.println("복원 객체 : " + r3.toString());
			// System.out.println("복원 객체 :" + r4.toString());
		}catch(Exception e) {
			  e.printStackTrace();
		}finally {
			try {
				 in.close();
				 bis.close();
				 fis.close();
			}catch (Exception e) {
				
			}
		}

	}

}
