package com.eomcs.lms.handler;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.LessonDao;

@Component("/lesson/delete")
public class LessonDeleteCommand implements Command {

  Scanner keyboard;
  LessonDao lessonDao;

  public LessonDeleteCommand(Scanner keyboard, LessonDao lessonDao) {
    this.keyboard = keyboard;
    this.lessonDao = lessonDao;
  }

  public void execute() {

    try {
      
      System.out.print("수업번호? ");
      int no = Integer.parseInt(keyboard.nextLine());
      
      int result =lessonDao.delete(no);

      if (result >0 ) {
        System.out.println("삭제했습니다.");
      } else System.out.println("해당 번호의 수업이 없습니다.");
     

    }catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
