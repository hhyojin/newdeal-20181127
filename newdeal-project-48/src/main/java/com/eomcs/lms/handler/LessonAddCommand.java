package com.eomcs.lms.handler;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonAddCommand implements Command {

  Scanner keyboard;
  LessonDao lessonDao;


  public LessonAddCommand(Scanner keyboard, LessonDao lessonDao) {
    this.keyboard = keyboard;
    this.lessonDao=lessonDao;
  }

  public void execute() {

    Lesson lesson = new Lesson();
    
    try {
      System.out.print("수업명? ");
      lesson.setTitle(keyboard.nextLine());

      System.out.print("수업내용? ");
      lesson.setContents(keyboard.nextLine());

      System.out.print("시작일? ");
      lesson.setStartDate(Date.valueOf(keyboard.nextLine()));

      System.out.print("종료일? ");
      lesson.setEndDate(Date.valueOf(keyboard.nextLine()));

      System.out.print("총수업 시간? ");
      lesson.setTotalHours(Integer.parseInt(keyboard.nextLine()));

      System.out.print("일수업 시간? ");
      lesson.setDayHours(Integer.parseInt(keyboard.nextLine()));

      System.out.print("수강생 번호? ");
      lesson.setWriterNo(Integer.parseInt(keyboard.nextLine()));
      
      int result = lessonDao.insert(lesson);
      
      if (result > 0) {     
        System.out.println("입력했습니다.");
      } else System.out.println("입력 실패했습니다.");
     
    }catch (Exception e) {
      e.printStackTrace();
    }

  }
  
}
