package test;

import java.util.Set;
import java.util.TreeSet;

public class LottoSSS {

  public static void main(String[] args) {
    // TODO Auto-generated method stub

    Set<Integer> lotto = new TreeSet<>();
    for(int i=0;lotto.size() < 6 ; i++) {
        lotto.add((int)(Math.random()*45)+1);
    }
    System.out.println(lotto.toString());
    /*int sum=0;
    Iterator<Integer> lo = lotto.iterator();
    while(lo.hasNext()) {
        sum+=lo.next();
    }
    System.out.println(sum);*/
    
    
    
  }

}
