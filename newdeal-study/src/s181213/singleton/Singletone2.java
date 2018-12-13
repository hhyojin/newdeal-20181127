package s181213.singleton;

public class Singletone2 {
  private static Singletone2 sharePrint;
  
  private Singletone2() {}
  
  public static Singletone2 getInstance() {
    if(sharePrint != null) {
      return sharePrint;
    } else 
      sharePrint = new Singletone2();
    return sharePrint;
    
  }
  
  
}
