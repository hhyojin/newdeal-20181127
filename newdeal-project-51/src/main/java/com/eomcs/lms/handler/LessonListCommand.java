package com.eomcs.lms.handler;

import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

@Component("/lesson/list")
public class LessonListCommand implements Command {
  
  Scanner keyboard;
  LessonDao lessonDao;

  public LessonListCommand(Scanner keyboard, LessonDao lessonDao) {
    this.keyboard = keyboard;
    this.lessonDao = lessonDao;
  }
  
  public void execute() {
    
    try {
      List<Lesson> list = lessonDao.findAll();
     
      for (Lesson lesson : list) {
        System.out.printf("%d, %s, %s, %s, %d\n", 
            lesson.getNo(),
            lesson.getTitle(),
            lesson.getStartDate(),
            lesson.getEndDate(),
            lesson.getTotalHours()
            );
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
}
}
