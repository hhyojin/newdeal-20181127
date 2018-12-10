package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class LottoCollection {

  ArrayList<Integer> arr = new ArrayList<>();

  public ArrayList<Integer> selectNumber() {

    Random rdNum = new Random();
    
    Set<Integer> set;
    
    /* set = new HashSet<>();
    while(set.size() < 6) {
      set.add(rdNum.nextInt(45)+1); 
    }    
    ArrayList<Integer> arr = new ArrayList<Integer>(set);
    
    Collections.sort(arr);
    ArrayList<Integer> arr = new ArrayList<Integer>(set); */

    set = new TreeSet<>();

    for(int i = 0; set.size() < 6 ; i++) {
      set.add(rdNum.nextInt(45)+1);
    }
    ArrayList<Integer> arr = new ArrayList<Integer>(set);
   
    return arr;
    
  }

  public void createFile(ArrayList<Integer> arr) throws IOException {
    PrintWriter pw = new PrintWriter("C:/Users/USER/git/newdeal-20181127/lotto/src/test/lottoC.txt");
    for(int num:arr) {
      String txt = (Integer.toString(num))+", ";
      pw.print(txt);
    }
    pw.close();
  }



}
