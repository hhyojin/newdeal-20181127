import java.sql.Date;
import java.util.Scanner;

public class App {
  
  //static 키워드를 붙이면 자바는 메모리 할당을 딱 한번만 하게 되어 메모리 사용에 이점을 볼 수 있게된다.
  //static 으로 설정하면 같은 곳의 메모리 주소만을 바라보기 때문에 static 변수의 값을 공유하게 되는 것
  static Scanner keyboard;
  static int lessonIdx;
  static int memberIdx;
  static int boardIdx;
  static Lesson[] lessons;
  static Member[] members;
  static Board[] boards;
  static final int LENGTH = 10;
  
  public static void main(String[] args) {

    /*메인 메서드 안에서 메서드 불러야 하니까 static 붙고,
    static 메서드 안에서 변수들 사용해야 하니까 변수들도 다 static 된 거예용*/

    keyboard = new Scanner(System.in);
    
    lessonIdx = 0;
    lessons  = new Lesson[LENGTH];
    
    memberIdx = 0;
    members = new Member[LENGTH];
    
    boardIdx = 0;
    boards = new Board[LENGTH];


    while (true) {
      String command = prompt();

      if (command.equals("/lesson/add")) {
        addLesson();

      } else if (command.equals("/lesson/list")) {
        listLesson();

      } else if (command.equals("/member/add")) {
        addMember();

      } else if (command.equals("/member/list")) {
        listMember();

      } else if (command.equals("/board/add")) {
        addBoard();

      } else if (command.equals("/board/list")) {
        listBoard();

      } else if (command.equals("quit")) {
        System.out.println("안녕!");
        break;

      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }

      System.out.println(); // 결과 출력 후 빈 줄 출력
    }

    keyboard.close();
  }

  private static String prompt() { //main 함수에서만 쓸 거니 private
    System.out.print("명령> ");
    return keyboard.nextLine().toLowerCase();
  }

  private static void listBoard() {
    for (int j = 0; j < boardIdx; j++) {
      System.out.printf("%3d, %-20s, %s, %d\n", 
          boards[j].no, boards[j].contents, boards[j].createdDate, boards[j].viewCount);
    }
  }
  
  private static void addBoard() {
    Board board = new Board();

    System.out.print("번호? ");
    board.no = Integer.parseInt(keyboard.nextLine());

    System.out.print("내용? ");
    board.contents = keyboard.nextLine();

    board.createdDate = new Date(System.currentTimeMillis()); 

    board.viewCount = 0;

    boards[boardIdx] = board;
    boardIdx++;

    System.out.println("저장하였습니다.");
  }
  
  private static void listMember() {
    for (int j = 0; j < memberIdx; j++) {
      System.out.printf("%3d, %-4s, %-20s, %-15s, %s\n", 
          members[j].no, members[j].name, members[j].email, 
          members[j].tel, members[j].registeredDate);
    }
  }
  
  private static void addMember() {
    Member member = new Member();

    System.out.print("번호? ");
    member.no = Integer.parseInt(keyboard.nextLine());

    System.out.print("이름? ");
    member.name = keyboard.nextLine();

    System.out.print("이메일? ");
    member.email = keyboard.nextLine();

    System.out.print("암호? ");
    member.password = keyboard.nextLine();

    System.out.print("사진? ");
    member.photo = keyboard.nextLine();

    System.out.print("전화? ");
    member.tel = keyboard.nextLine();

    member.registeredDate = new Date(System.currentTimeMillis()); 

    members[memberIdx] = member;
    memberIdx++;

    System.out.println("저장하였습니다.");
  }
  
  private static void addLesson(){
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

  private static void listLesson() {
    for (int j = 0; j < lessonIdx; j++) {
      System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
          lessons[j].no, lessons[j].title, lessons[j].startDate, 
          lessons[j].endDate, lessons[j].totalHours);
    }
  }

}
