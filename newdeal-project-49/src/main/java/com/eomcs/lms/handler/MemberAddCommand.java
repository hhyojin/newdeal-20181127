package com.eomcs.lms.handler;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberAddCommand implements Command {
  
  Scanner keyboard;
  MemberDao memberdao;
  
  public MemberAddCommand(Scanner keyboard, MemberDao memberdao) {
    this.keyboard = keyboard;
    this.memberdao = memberdao;
  }
  
  @Override
  public void execute() {
    try {
      
      Member member = new Member();
      
      System.out.print("수강생명? ");
      member.setName(keyboard.nextLine());

      System.out.print("이메일? ");
      member.setEmail(keyboard.nextLine());

      System.out.print("비밀번호? ");
      member.setPassword(keyboard.nextLine());

      System.out.print("사진? ");
      member.setPhoto(keyboard.nextLine());

      System.out.print("전화번호? ");
      member.setTel(keyboard.nextLine());
      
      int result = memberdao.insert(member);
      
      if(result > 0) {System.out.println("입력했습니다.");
      }else System.out.println("해당 번호의 회원이 없습니다.");
      
     
    }catch (Exception e) {
      e.printStackTrace();
    }

  }
}
