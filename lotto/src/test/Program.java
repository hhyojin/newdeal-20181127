package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Program {

  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub
    
    //array 사용
    LottoArray lottoA = new LottoArray();
    int[] arr = lottoA.selectNumber();
    lottoA.createFile(arr);
    System.out.println("lottoA: " + Arrays.toString(arr));
    
    //Collection 사용
    LottoCollection lottoC = new LottoCollection();
    ArrayList<Integer> arr2 = lottoC.selectNumber();
    lottoC.createFile(arr2);
    System.out.println("lottoC: " + arr2);

  }

}
