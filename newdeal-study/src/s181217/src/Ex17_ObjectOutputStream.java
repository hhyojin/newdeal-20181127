package s181217.src;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;




//파일에 객체 write
public class Ex17_ObjectOutputStream {

	public static void main(String[] args) {
		String filename = "UserData.txt";
		try {
			FileOutputStream fos = new FileOutputStream(filename ,true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			//객체통신 (직렬화) 
			ObjectOutputStream out = new ObjectOutputStream(bos);
			UserInfo u1 = new UserInfo("superman","super",100);
			UserInfo u2 = new UserInfo("scott","tiger",50);
			
			out.writeObject(u1); //직렬화
			out.writeObject(u2); //직렬화
			
			out.close();
			bos.close();
			fos.close();
			System.out.println("파일생성 -> buffer -> 직렬화객체 -> write");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
