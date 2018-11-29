package test;

public class Test {

  public static void main(String[] args) {
    Patient p1 = new Patient();
    p1.setName("홍길동");
    //인스턴스가 유효한 값을 갖게 하기 위해
    
    p1.setAge(200);
    p1.setWeight(400);
    p1.setHeight(500);
    
    System.out.println(p1.getName());

  }

}
