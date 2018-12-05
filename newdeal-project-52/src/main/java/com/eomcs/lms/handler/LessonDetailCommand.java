package com.eomcs.lms.handler;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

@Component("/lesson/detail")
public class LessonDetailCommand implements Command {

  Scanner keyboard;
  LessonDao lessonDao;

  public LessonDetailCommand(Scanner keyboard, LessonDao lessonDao) {
    this.keyboard = keyboard;
    this.lessonDao=lessonDao;
  }

  @Override
  public void execute() {
    try {
      System.out.print("수업번호? ");
      int no = Integer.parseInt(keyboard.nextLine());

      Lesson lesson = new Lesson();
      lesson = lessonDao.findByNo(no);


      System.out.printf("수업번호: %d\n", lesson.getNo());
      System.out.printf("수업명: %s\n", lesson.getTitle());
      System.out.printf("설명: %s\n", lesson.getContents());
      System.out.printf("시작일: %s\n", lesson.getStartDate());
      System.out.printf("종료일: %s\n", lesson.getEndDate());
      System.out.printf("총수업시간: %d\n", lesson.getTotalHours());  
      System.out.printf("일수업시간: %d\n", lesson.getDayHours()); 
      System.out.printf("회원번호: %d\n", lesson.getWriterNo()); 

    }catch (Exception e) {
      e.printStackTrace();
    }

  }
}

