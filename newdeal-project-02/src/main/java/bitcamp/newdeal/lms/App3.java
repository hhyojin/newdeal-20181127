package bitcamp.newdeal.lms;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {

    Scanner keyIn = new Scanner(System.in);

    System.out.print("번호? ");
    int no = Integer.parseInt(keyIn.nextLine());

    System.out.print("이름? ");
    String name = keyIn.nextLine();

    System.out.print("이메일? ");
    String email = keyIn.nextLine();

    System.out.print("암호? ");
    String password = keyIn.nextLine();

    System.out.print("사진? ");
    String photo = keyIn.nextLine();

    System.out.print("전화");
    String phone = keyIn.nextLine();

    keyIn.close();

    //가입일1    util의 Date 클래스만 사용 가능
    /*Date currentDate = new Date();
    SimpleDateFormat signupDate = new SimpleDateFormat("yyyy-MM-dd");*/

    //가입일 2 util, sql의 Date 클래스 둘 다 사용 가능       
    Date currentDate = new Date(System.currentTimeMillis());            
    SimpleDateFormat signupDate = new SimpleDateFormat("yyyy-MM-dd");           

    System.out.printf("번호 %d\n", no);
    System.out.printf("이름 %s\n", name);
    System.out.printf("이메일 %s\n", email);
    System.out.printf("암호?  %s\n", password);
    System.out.printf("사진 %s\n", photo);
    System.out.printf("전화 %s\n", phone);
    System.out.print("가입일 " + signupDate.format(currentDate));

  }
}
