package bitcamp.newdeal.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
       Scanner keyIn = new Scanner(System.in); //스캐너는 뭔가를 꼭 읽어야 함.
         //키보드 System.in
         //스캐너는 System.in이 있어야 하고 System.in에 의존함. dependency
         //System.in = 의존당하는 객체. 의존객체

       System.out.print("번호? ");
       int no = Integer.parseInt(keyIn.nextLine());
       //Integer.parseInt();
       //Static 메소드. 특정 객체가 아닌 일반 객체에 사용. 클래스의 값을 뽑음  
       
       System.out.print("수업명? ");
       String title = keyIn.nextLine();
       
       System.out.print("내용? ");
       String contents = keyIn.nextLine();
       
       System.out.print("강의시작일? ");
       Date startDate = Date.valueOf(keyIn.nextLine()); //얜 클래스 메소드 = static 메소드
 
       //int month = startData.getMonth(); -> 특정 날짜 객체에 대해서만 값을 뽑음. 특정 인스턴스에 작업 수행
       //인스턴스 메소드. static 안 붙임 
       
       System.out.print("강의종료일? ");
       Date endDate = Date.valueOf(keyIn.nextLine()); 
       
       System.out.print("총 강의시간? ");
       int totalHours = Integer.parseInt(keyIn.nextLine());
       
       System.out.print("일 강의시간? ");
       int dayHours = Integer.parseInt(keyIn.nextLine());
       
       
       keyIn.close(); //사용하고 나서 닫어
       
       System.out.println("번호: " + no);
       System.out.printf("수업명: %s\n", title); //이스케이프 문자: 문자 안에 들어가는 문자열
                                                 //뒤에 문자 오고 엔터 칠 거야~~~~
       System.out.printf("내용: %s\n", contents);
       System.out.printf("강의시작일: %s\n", startDate);
       System.out.printf("강의종료일: %s\n", endDate);
       System.out.printf("총 강의시간: %d\n", totalHours); //뒤에 날짜 오고 엔터 칠 거야~~~~
       System.out.printf("일 강의시간: %d\n", dayHours);
    }
}
