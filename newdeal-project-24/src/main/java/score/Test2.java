package score;

public class Test2 {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Score s1 = new Score();
    s1.setName("홍길동");
    s1.setEng(20);
    s1.setKor(50);
    s1.setMath(80);
    
/*    s1.sum = s1.kor + s1.math + s1.eng;
    s1.aver = s1.sum / 3.0f;
    
    이 변수에 직접 접근해 결과를 바꾸므로 접근 못 하게 막음
    그리고 sum과 aver에는 setter를 없애버려
    s1.sum = 250;
    s1.aver = 65.5f;
    
    이게 캡슐화
     1. 데이터를 감춰서 조작하지 못하게 하거나
     2. 혹은 유의미한 데이터만 들어가게 할 수 있음
    (setter에 if문 넣어서)
    */
    
    System.out.println(s1.getSum());
    System.out.println(s1.getAver());
  }

}
