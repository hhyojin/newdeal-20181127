package com.eomcs.lms.handler;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@Component("/member/detail")
public class MemberDetailCommand implements Command {
  
  Scanner keyboard;
  MemberDao memberdao;
  
  public MemberDetailCommand(Scanner keyboard, MemberDao memberdao) {
    this.keyboard = keyboard;
    this.memberdao=memberdao;
  }
  
  public void execute() {
    try {
      System.out.print("회원번호? ");
      int no = Integer.parseInt(keyboard.nextLine());
      
      Member member = new Member();
      member = memberdao.findByNo(no);
      
      //DBMS에서 한 개의 레코드를 가져온다
      if(member != null) {
        System.out.printf("회원번호: %d\n", member.getNo());
        System.out.printf("이름: %s\n", member.getName());
        System.out.printf("이메일: %s\n", member.getEmail());
        System.out.printf("비밀번호: %s\n", member.getPassword());
        System.out.printf("사진: %s\n", member.getPhoto());
        System.out.printf("전화번호: %s\n", member.getTel());  
        System.out.printf("가입일자: %s\n", member.getRegisteredDate()); 
      } else {
        System.out.println("해당 번호의 게시물이 없습니다.");
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
}
