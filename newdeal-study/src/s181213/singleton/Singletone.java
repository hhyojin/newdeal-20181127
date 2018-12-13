package s181213.singleton;

public class Singletone {

  private static Singletone sharePC;
  
  private Singletone() {}
  
  public static Singletone getInstance() {
    
    if(sharePC != null) {
      return sharePC; 
    } else
    sharePC = new Singletone();
    return sharePC;
  }
  
  /*public static void main(String[] args) {
  
    Singletone s1 = Singletone.getInstance();
    Singletone s2 = Singletone.getInstance();
    
    System.out.println(s1 == s2);
    
  }*/
}
