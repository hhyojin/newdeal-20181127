package bitcamp.newdeal.lms;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App4 {
  public static void main(String[] args) {

    Scanner keyIn = new Scanner(System.in);
    
    System.out.print("번호? ");
    int no = Integer.parseInt(keyIn.nextLine());
    
    System.out.print("내용? ");
    String contents = keyIn.nextLine();
    
    keyIn.close();
    
    //작성일
    Date currentDate = new Date(System.currentTimeMillis()); 
    SimpleDateFormat writeDate = new SimpleDateFormat("yyyy-MM-dd");
    
    //조회수
    int count = 0;

    System.out.printf("번호 %d\n", no);
    System.out.printf("내용 %s\n", contents);
    System.out.println("작성일 " + writeDate.format(currentDate));
    System.out.printf("조회수 %d\n", count);
 
  }
}
