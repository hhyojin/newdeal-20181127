import java.sql.Date;

//새 데이터 타입
//새로운 종류의 메모리를 구성하는 설계도 = 클래스

public class Lesson {
  int no; //메모리를 만들라는 명령어
  String title;
  String contents;
  Date startDate;
  Date endDate;
  int totalHours;
  int dayHours;
}

/*변수 앞에 static을 붙이면 클래스 변수가 됨.
이 클래스 만들 때 자동으로 생성됨

app.java에서 new 없이 바로 Lesson,name을 쓸 수 있음
근데 이러면 다음에 입력할 때 원래 값을 덮어씌움

static 없으면 new 할때 메모리 생성
이게 인스턴스 변수
데이터를 개별적으로 여러 개 만들어야 할 때 사용*/