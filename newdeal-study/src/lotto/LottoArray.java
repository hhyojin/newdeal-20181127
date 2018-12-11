package lotto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class LottoArray {

  //1. 1~45까지의 난수를 발생시켜 6개의 배열에 담으세요
  //2. 배열에 담긴 6개의 배열값은 중복 불가
  //3. 배열에 있는 6개의 값을 낮은 순으로 정렬
  //최종결과를 파일로 저장
  //1. Array 사용
  //2. Collection 사용

  int[] arr = new int[6];

  public int[] selectNumber() {
    Random rdNum = new Random();

    //난수 담기
    for (int i =0; i < 6 ; i++) {
      arr[i] = rdNum.nextInt(45)+1;

      //중복값 삭제
      for(int j = 0; j < i ; j++) {
        if(arr[i] == arr[j]) {
          i--;
          break;         
        }
      }
    }
    //난수 정렬
    for(int i=0 ; i< arr.length;i++) { 
      for(int j=i+1; j< arr.length; j++) {
        if(arr[i] > arr[j]) {
          int temp = arr[i];
          arr[i] = arr[j];
          arr[j] =  temp;
        }
      }
    }
    return arr;
  }

  public void createFile(int[] arr) throws IOException {
    PrintWriter pw = new PrintWriter("C:/Users/USER/git/newdeal-20181127/lotto/src/test/lottoA.txt");
    
    for(int num:arr) {
      String txt = (Integer.toString(num))+", ";
      pw.print(txt);
    }
    pw.close();
  }





}


