import java.sql.Date;
import java.util.Scanner;

public class LessonHandler {

  //이 클래스는 keyboard 객체가 있어야 한다.
  //따라서 이 클래스의 메소드를 사용하기 전에
  //반드시 keyboard 객체를 꽂아줘야 한다.
  //keyboard는 의존객체
  /*생성자가 하는 일: 의존객체를 강제시키는 것
   * 아니면 잘못 써도 강제되지 않고 작성자도 모름
   * 여기서는 keyboard
   * 생성자 안 쓰고 app.java에서 주입해줌
  */
  static Scanner keyboard;
  
  static final int LENGTH = 10;
  
  static Lesson[] lessons = new Lesson[LENGTH];
  static int lessonIdx = 0;
  
  /*private면 다른 클래스에서 호출 못 함. 
  그러므로 public으로 설정*/
  public static void listLesson() {
    for (int j = 0; j < lessonIdx; j++) {
      System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
          lessons[j].no, lessons[j].title, lessons[j].startDate, 
          lessons[j].endDate, lessons[j].totalHours);
    }
  }

  public static void addLesson() {
    Lesson lesson = new Lesson();

    System.out.print("번호? ");
    lesson.no = Integer.parseInt(keyboard.nextLine());

    System.out.print("수업명? ");
    lesson.title = keyboard.nextLine();

    System.out.print("설명? ");
    lesson.contents = keyboard.nextLine();

    System.out.print("시작일? ");
    lesson.startDate = Date.valueOf(keyboard.nextLine());

    System.out.print("종료일? ");
    lesson.endDate = Date.valueOf(keyboard.nextLine());

    System.out.print("총수업시간? ");
    lesson.totalHours = Integer.parseInt(keyboard.nextLine());

    System.out.print("일수업시간? ");
    lesson.dayHours = Integer.parseInt(keyboard.nextLine());

    // i 번째 배열에 수업 정보를 담고 있는 Lesson 객체(의 주소)를 보관한다.
    lessons[lessonIdx] = lesson;
    lessonIdx++;

    System.out.println("저장하였습니다.");
  }
  
  
  
}
