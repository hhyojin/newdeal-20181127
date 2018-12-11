package AirPlane;

public class AirPlainProgram {
    
  static int count;
    
  public static void main(String[] args) {
    
     AirPlain ap = new AirPlain();
     int no = ap.setNo();
     String name = ap.setName();
     
     System.out.println("비행기 번호: " + no);
     System.out.println("비행기 이름: " + name);
     count = ap.totalNo();
     System.out.println(count);
     
     AirPlain ap2 = new AirPlain();     
     int no2 = ap2.setNo();
     String name2 = ap2.setName();
     
     System.out.println("비행기 번호: " + no2);
     System.out.println("비행기 이름: " + name2);
     
     count = ap.totalNo();
     
     System.out.println(count);
     
      
     
     
   }

}
