package com.eomcs.lms.handler;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDao;

public class MemberDeleteCommand implements Command {
  
  Scanner keyboard;
  MemberDao memberdao;
  
  public MemberDeleteCommand(Scanner keyboard, MemberDao memberdao) {
    this.keyboard = keyboard;
    this.memberdao = memberdao;
  }
  
  @Override
  public void execute() {
   try {
      
      System.out.print("회원번호? ");
      int no = Integer.parseInt(keyboard.nextLine());
      
      int result = memberdao.delete(no);
      
      if (result > 0) {
        System.out.println("삭제했습니다.");
      } else System.out.println("해당 번호의 회원이 없습니다.");
      
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
}
