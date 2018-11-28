package bitcamp.newdeal.lms;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.swing.text.html.MinimalHTMLWriter;

public class App3 {
  public static void main(String[] args) {

    Scanner keyIn = new Scanner(System.in);

    final int DEFAULT_SIZE = 10;

    int[] no = new int[DEFAULT_SIZE];
    String[] contents = new String[DEFAULT_SIZE];

    int len = 0;

    for(int i=0 ;i<DEFAULT_SIZE ;i++) {
      System.out.print("번호? ");
      no[i] = Integer.parseInt(keyIn.nextLine());

      System.out.print("내용? ");
      contents[i] = keyIn.nextLine();

      len++;

      System.out.print("계속 입력하시겠습니까? (Y/n)");
      String input = keyIn.nextLine();

      if(input.equals("") || input.equalsIgnoreCase("y")) {
        continue;
      } break;
    }

    //작성일
    Date currentDate = new Date(System.currentTimeMillis());
    SimpleDateFormat writeDate = new SimpleDateFormat("yy-MM-dd");

    //조회수
    int count=0;

    for(int i=0 ; i<len ; i++) {
      System.out.printf("%d, %s, %s, %d\n", 
          no[i], 
          contents[i], 
          writeDate.format(currentDate), 
          count);
    }

  }
}
