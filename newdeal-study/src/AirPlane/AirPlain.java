package AirPlane;

public class AirPlain {

  String name;
  static int no = 0;
  static int totalNo=0;
  
  public AirPlain() {
    totalNo++;
  } 

  public int setNo() {
    no++;
    return no;
  }
  
  public String setName() {
    name = "AirPlain" + (Integer.toString(no));   
    return name;
  }
  
  public int totalNo() {
    return totalNo;
  }

}
