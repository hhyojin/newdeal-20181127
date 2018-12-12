package s181212;

public class s2 {

  public static void main(String[] args) {
    //2. 어느 학생의 기말 성적
    int sum=0;
    float average = 0f;
    int[] jumsu = {100,55,90,60,78};
    //1. 총 과목수
    //2. 점수의 합
    //3. 점수의 평균을 구하세요  (2,3 번은 하나의 for문에서)

    System.out.println("총 과목수: " + jumsu.length );
    
    for(int i=0;i<jumsu.length;i++) {
      sum += jumsu[i];
      if(i==jumsu.length-1) {
        average= (float) sum/jumsu.length;
      }
    }
    
    System.out.println("점수의 합: " + sum);
    System.out.println("점수의 평균: " + average);
    
    
  }

}
