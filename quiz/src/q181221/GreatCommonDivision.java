package q181221;

import java.util.ArrayList;
import java.util.List;

public class GreatCommonDivision {

  public int calc(int x, int y) {
    
    int min = (x > y)?y:x;
    int gcd=1;
    
    for(int i = min; i >=1 ; i--) {
      if( x%i == 0 & y%i == 0) {
        gcd=i;
        return gcd;
      }
    }
    return gcd;
  }
  
  
 /* public int calc(int x, int y) {

    List<Integer> xArr = new ArrayList<Integer>();
    List<Integer> yArr = new ArrayList<Integer>();

    xArr = commonD(x);
    yArr = commonD(y);

    for(int i=xArr.size()-1; i >= 0 ; i--) {
      for(int j=yArr.size()-1; j>=0;j--) {
        if(xArr.get(i) == yArr.get(j)){
          return xArr.get(i);
        }
      }
    }
    return 1;
  }

  public List<Integer> commonD(int x) {
    List<Integer> arr = new ArrayList<Integer>();
    for(int i =1 ; i <= x ; i++) {
      if(x%i == 0) {        
        arr.add(i);
      }
    }
    return arr;
  }*/
}
