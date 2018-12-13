package s181213.singleton;

public class Program2 {

  public static void main(String[] args) {
    
    Singletone2 print1 = Singletone2.getInstance();
    Singletone2 print2 = Singletone2.getInstance();
    
    System.out.println(print1 == print2);
    
    
  }

}
