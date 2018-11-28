package bitcamp.newdeal.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
      final int DEFAULT_SIZE = 20; //이후 수정하려 하면 에러 남. 상수 리터럴. 이건 대문자로 쓰기도 함
      int[] no = new int[DEFAULT_SIZE]; //객체 20개 만들어
      String[] title = new String[DEFAULT_SIZE]; //객체 담을 레퍼런스 주소를 20개 만들어
      String[] contents = new String[DEFAULT_SIZE];
      Date[] startDate = new Date[DEFAULT_SIZE];
      Date[] endDate = new Date[DEFAULT_SIZE];
      int[] totalHours = new int[DEFAULT_SIZE];
      int[] dayHours = new int[DEFAULT_SIZE];

      int len =0;

      Scanner keyIn = new Scanner(System.in);

      for(int i =0 ; i<DEFAULT_SIZE; i++) { 

        System.out.print("번호? ");
        no[i] = Integer.parseInt(keyIn.nextLine());

        System.out.print("수업명? ");
        title[i] = keyIn.nextLine();

        System.out.print("내용? ");
        contents[i] = keyIn.nextLine();

        System.out.print("강의시작일? ");
        startDate[i] = Date.valueOf(keyIn.nextLine());

        System.out.print("강의종료일? ");
        endDate[i] = Date.valueOf(keyIn.nextLine()); 

        System.out.print("총 강의시간? ");
        totalHours[i] = Integer.parseInt(keyIn.nextLine());

        System.out.print("일 강의시간? ");
        dayHours[i] = Integer.parseInt(keyIn.nextLine());

        len++;

        System.out.print("계속하시겠습니까? (Y/n) "); //대문자 Y: 그냥 엔터를 치면 y라고 알아 듣겠다
        String input = keyIn.nextLine();

        if(input.equals("") || input.equalsIgnoreCase("y")) { //y거나 빈문자열인 경우. ignoreclass로 대소문자 구별 없앰        
          continue;
        } else {
        break;} //빈문자열이나 y가 아니면 반복문 중지     
      }
      keyIn.close();

      for (int i = 0; i <len; i++) {
        System.out.printf("%d, %s, %s, ~ %s, %d\n",
            no[i],
            title[i],
            startDate[i],
            endDate[i],
            totalHours[i],
            dayHours[i]
            );
      }
    }
}
