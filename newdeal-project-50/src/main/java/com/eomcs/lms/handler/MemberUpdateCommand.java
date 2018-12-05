package com.eomcs.lms.handler;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@Component("/member/update")
public class MemberUpdateCommand implements Command {
  
  Scanner keyboard;
  MemberDao memberdao;
  
  public MemberUpdateCommand(Scanner keyboard, MemberDao memberdao) {
    this.keyboard = keyboard;
    this.memberdao = memberdao;
  }
  
  @Override
  public void execute() {
    
    try {
      System.out.print("회원 번호");
      int no = Integer.parseInt(keyboard.nextLine());
      
      Member member = new Member();
      member = memberdao.findByNo(no);
      
      String oldName = member.getName();
      String oldEmail = member.getEmail();
      String oldPwd = member.getPassword();
      String oldPhoto = member.getPhoto();
      String oldTel = member.getTel();

      System.out.printf("이름(%s)? \n", oldName);
      System.out.print("이름? ");
      String name = keyboard.nextLine();      
      if (name.equals("")) {member.setName(oldName);}
      else member.setName(name);
      
      System.out.printf("이메일(%s)? \n", oldEmail);
      System.out.print("이메일? ");
      String email = keyboard.nextLine();      
      if (email.equals("")) {member.setEmail(oldEmail); }
      else member.setEmail(email);
      
      System.out.printf("비밀번호(%s)? \n", oldPwd);
      System.out.print("비밀번호? ");
      String pwd = keyboard.nextLine();      
      if (pwd.equals("")) {member.setPassword(oldPwd); }
      else member.setPassword(pwd);
      
      System.out.printf("사진(%s)? \n", oldPhoto);
      System.out.print("사진? ");
      String photo = keyboard.nextLine();      
      if (photo.equals("")) {member.setPhoto(oldPhoto); }
      else member.setPhoto(photo);
      
      System.out.printf("전화(%s)? \n", oldTel);
      System.out.print("전화? ");
      String tel = keyboard.nextLine();      
      if (tel.equals("")) {member.setTel(oldTel);}
      else member.setTel(tel);
      
      int result = memberdao.update(member);

      if(result > 0) {
        System.out.println("변경했습니다.");
      } else System.out.println("해당 번호의 회원이 없습니다.");

    }catch (Exception e) {
      e.printStackTrace();
    }
  
  }
}
