package q181220;

public class FibonacciSequence {

  public int calc(int number) {

    int last1, last2, result = 0;
    
    if(number <= 1)
        return 1;
     
    last1 = 1;
    last2 = 1;
     
    for(int i=1; i <number; i++) {
        result = last1 + last2;
        last1 = last2;
        last2 = result; 
    }
     
    return result;
}

  //재귀함수 이용
  /*  public int fibo(int number) {

    if(number<=1)
      return 1;
    else return fibo(number-1)+fibo(number-2);
  }*/

}
