package com.eomcs.lms.handler;


//Caller: App 클래스
//Callee: 명령처리기(BoardListCommand)

public interface Command {
  void execute();
  
}
