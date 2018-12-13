package s181213.singleton;

public class program {

  public static void main(String[] args) {
   
    Singletone pc1 = Singletone.getInstance();
    Singletone pc2 = Singletone.getInstance();
    
    System.out.println();
    System.out.println(pc2);
    
  }

}
