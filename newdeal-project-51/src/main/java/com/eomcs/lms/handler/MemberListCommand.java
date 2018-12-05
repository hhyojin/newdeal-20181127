package com.eomcs.lms.handler;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@Component("/member/list")
public class MemberListCommand implements Command {
  
  Scanner keyboard;
  MemberDao memberdao;
  
  public MemberListCommand(Scanner keyboard, MemberDao memberdao) {
    this.keyboard = keyboard;
    this.memberdao = memberdao;
  }
  
  public void execute() {
    try {
      List<Member> list = memberdao.findAll();
      
      for(Member member : list) {
        System.out.printf("%d, %s, %s, %s, %s, %s\n", 
            member.getNo(),
            member.getName(),
            member.getEmail(),
            member.getPassword(),
            member.getTel(),
            member.getRegisteredDate()
            );
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
