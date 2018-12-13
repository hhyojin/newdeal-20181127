package s181213.singleton;

public class Singletone {

  private static Singletone sharePC;
  
  private Singletone() {}
  
  public static Singletone getInstance() {  
    return sharePC;
  }
  
}
