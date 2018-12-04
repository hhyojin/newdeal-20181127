package com.eomcs.lms;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.impl.MariaDBBoardDao;
import com.eomcs.lms.dao.impl.MariaDBLessonDao;
import com.eomcs.lms.dao.impl.MariaDBMemberDao;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.LessonAddCommand;
import com.eomcs.lms.handler.LessonDeleteCommand;
import com.eomcs.lms.handler.LessonDetailCommand;
import com.eomcs.lms.handler.LessonListCommand;
import com.eomcs.lms.handler.LessonUpdateCommand;
import com.eomcs.lms.handler.LoginCommand;
import com.eomcs.lms.handler.MemberAddCommand;
import com.eomcs.lms.handler.MemberDeleteCommand;
import com.eomcs.lms.handler.MemberDetailCommand;
import com.eomcs.lms.handler.MemberListCommand;
import com.eomcs.lms.handler.MemberUpdateCommand;

public class App {

  static Scanner keyboard = new Scanner(System.in);
  static Stack<String> commandHistory = new Stack<>();
  static Queue<String> commandHistory2 = new LinkedList<>();

  public static void main(String[] args) {
        
    //LessonHandler lessonHandler = new LessonHandler(keyboard, new ArrayList<>());
    //MemberHandler memberHandler = new MemberHandler(keyboard, new LinkedList<>());

    BoardDao boarddao = new MariaDBBoardDao();
    MemberDao memberdao = new MariaDBMemberDao();
    LessonDao lessondao = new MariaDBLessonDao();
    HashMap<String, Command> commandMap = new HashMap<>();

    //명령어 추가될 때마다 if else 추가될 필요가 없음
    commandMap.put("/lesson/add", new LessonAddCommand(keyboard, lessondao));
    commandMap.put("/lesson/list", new LessonListCommand(keyboard, lessondao));
    commandMap.put("/lesson/detail", new LessonDetailCommand(keyboard, lessondao));
    commandMap.put("/lesson/update", new LessonUpdateCommand(keyboard, lessondao));
    commandMap.put("/lesson/delete", new LessonDeleteCommand(keyboard, lessondao));

    commandMap.put("/member/add", new MemberAddCommand(keyboard, memberdao));
    commandMap.put("/member/list", new MemberListCommand(keyboard, memberdao));
    commandMap.put("/member/detail", new MemberDetailCommand(keyboard, memberdao));
    commandMap.put("/member/update", new MemberUpdateCommand(keyboard, memberdao));
    commandMap.put("/member/delete", new MemberDeleteCommand(keyboard, memberdao));    
    
    commandMap.put("/board/list", new BoardListCommand(keyboard, boarddao));
    commandMap.put("/board/detail", new BoardDetailCommand(keyboard, boarddao));
    commandMap.put("/board/delete", new BoardDeleteCommand(keyboard, boarddao));
    commandMap.put("/board/update", new BoardUpdateCommand(keyboard, boarddao));
    commandMap.put("/board/add", new BoardAddCommand(keyboard, boarddao));

    commandMap.put("/auth/login", new LoginCommand(keyboard, memberdao));
    
    while (true) {
      String command = prompt();

      // 사용자가 입력한 명령을 스택에 보관한다.
      commandHistory.push(command);

      // 사용자가 입력한 명령을 큐에 보관한다.
      commandHistory2.offer(command);

      Command commandHandler = commandMap.get(command); //명령어를 주면 그 이름으로 저장된 명령어를 꺼냄


      if (commandHandler !=null ) {
        try {
        commandHandler.execute();
        } catch (Exception e) {
         System.out.println("명령어 처리 중 오류 발생!");
        }
      } else if (command.equals("quit")) {
        System.out.println("안녕!");
        break;

      } else if (command.equals("history")) {
        printCommandHistory();

      } else if (command.equals("history2")) {
        printCommandHistory2();

      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }

      System.out.println(); 
    }

    keyboard.close();
  }

  private static void printCommandHistory() {
    Iterator<String> iterator = commandHistory.iterator();

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  private static void printCommandHistory2() {
    Iterator<String> iterator = commandHistory2.iterator();

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  private static String prompt() {
    System.out.print("명령> ");
    return keyboard.nextLine().toLowerCase();
  }
}
