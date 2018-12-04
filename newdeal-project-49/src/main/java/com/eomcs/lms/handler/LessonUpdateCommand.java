package com.eomcs.lms.handler;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonUpdateCommand implements Command {

  Scanner keyboard;
  LessonDao lessonDao;

  public LessonUpdateCommand(Scanner keyboard, LessonDao lessonDao) {
    this.keyboard = keyboard;
    this.lessonDao=lessonDao;
  }

  @Override
  public void execute() {
    
    try {
      
      System.out.print("수업 번호");
      int no = Integer.parseInt(keyboard.nextLine());
      
      Lesson lesson = new Lesson();
      lesson = lessonDao.findByNo(no);

      if(lesson != null) {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        String oldTitle = lesson.getTitle();
        String oldContent = lesson.getContents();
        String oldStardDate = transFormat.format(lesson.getStartDate());
        String oldEndDate = transFormat.format(lesson.getEndDate());
        String oldTotalHours = Integer.toString(lesson.getTotalHours());
        String oldDayHours = Integer.toString(lesson.getDayHours());
        String oldMno = Integer.toString(lesson.getWriterNo());

        System.out.printf("제목(%s)? \n", oldTitle);
        System.out.print("수업제목? "); 
        String title = keyboard.nextLine();
        if (title.equals("")) {lesson.setTitle(oldTitle); }
        else lesson.setTitle(title);
        
        System.out.printf("내용(%s)? \n", oldContent);
        System.out.print("수업내용? ");
        String content = keyboard.nextLine();
        if (content.equals("")) {lesson.setContents(oldContent); }
        else lesson.setContents(content);
        
        System.out.printf("시작일(%s)? \n", oldStardDate);
        System.out.print("시작일? ");
        String startDate = keyboard.nextLine();
        if (startDate.equals("")) {lesson.setStartDate(Date.valueOf(oldStardDate)); }
        else lesson.setStartDate(Date.valueOf(startDate));
        
        System.out.printf("종료일(%s)? \n", oldEndDate);
        System.out.print("종료일? ");
        String endDate = keyboard.nextLine();
        if (endDate.equals("")) {lesson.setEndDate(Date.valueOf(oldEndDate)); }
        else lesson.setEndDate(Date.valueOf(endDate));
        
        System.out.printf("총수업시간(%s)? \n", oldTotalHours);
        System.out.print("총수업시간? ");
        String totalHours = keyboard.nextLine();
        if (totalHours.equals("")) {lesson.setTotalHours(Integer.parseInt(oldTotalHours)); }
        else lesson.setTotalHours(Integer.parseInt(totalHours));
        
        System.out.printf("일수업시간(%s)? \n", oldDayHours);
        System.out.print("일수업시간? ");
        String dayHours = keyboard.nextLine();
        if (dayHours.equals("")) {lesson.setDayHours(Integer.parseInt(oldDayHours)); }
        else lesson.setDayHours(Integer.parseInt(dayHours));
        
        System.out.printf("회원번호(%s)? \n", oldMno);
        System.out.print("회원번호? ");
        String mno = keyboard.nextLine();
        if (mno.equals("")) {lesson.setWriterNo(Integer.parseInt(oldMno)); }
        else lesson.setWriterNo(Integer.parseInt(mno));
   
        /*System.out.printf("제목(%s)? \n", oldTitle);
        System.out.print("수업제목? "); 
        if (keyboard.nextLine().equals("")) {lesson.setTitle(oldTitle); }
        else lesson.setTitle(keyboard.nextLine());
        
        System.out.printf("내용(%s)? \n", oldContent);
        System.out.print("수업내용? ");
        if (keyboard.nextLine().equals("")) {lesson.setContents(oldContent); }
        else lesson.setContents(keyboard.nextLine());
        
        System.out.printf("시작일(%s)? \n", oldStardDate);
        System.out.print("시작일? ");
        if (keyboard.nextLine().equals("")) {lesson.setStartDate(Date.valueOf(oldStardDate)); }
        else lesson.setStartDate(Date.valueOf(keyboard.nextLine()));
        
        System.out.printf("종료일(%s)? \n", oldEndDate);
        System.out.print("종료일? ");
        if (keyboard.nextLine().equals("")) {lesson.setEndDate(Date.valueOf(oldEndDate)); }
        else lesson.setEndDate(Date.valueOf(keyboard.nextLine()));
        
        System.out.printf("총수업시간(%s)? \n", oldTotalHours);
        System.out.print("총수업시간? ");
        if (keyboard.nextLine().equals("")) {lesson.setTotalHours(Integer.parseInt(oldTotalHours)); }
        else lesson.setTotalHours(Integer.parseInt(keyboard.nextLine()));
        
        System.out.printf("일수업시간(%s)? \n", oldDayHours);
        System.out.print("일수업시간? ");
        if (keyboard.nextLine().equals("")) {lesson.setDayHours(Integer.parseInt(oldDayHours)); }
        else lesson.setDayHours(Integer.parseInt(keyboard.nextLine()));
        
        System.out.printf("회원번호(%s)? \n", oldMno);
        System.out.print("회원번호? ");
        if (keyboard.nextLine().equals("")) {lesson.setWriterNo(Integer.parseInt(oldMno)); }
        else lesson.setWriterNo(Integer.parseInt(keyboard.nextLine()));
        */
      } else System.out.println("해당 번호의 수업이 없습니다.");
      
      int result = lessonDao.update(lesson);
      
      if (result > 0) {
        System.out.println("변경했습니다.");
      } else System.out.println("변경 실패!");
      
    }catch (Exception e) {
      e.printStackTrace();
    }
  
    
   
  }

}
