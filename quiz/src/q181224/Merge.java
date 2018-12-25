package q181224;

public class Merge {

  public static void main(String[] args) {
    /* 이미 정렬되어 있다는 가정 하에 시작해야 하며
       정렬되어 있지 않다면 먼저 정렬해야 한다

       예제배열 A = {1, 3, 5, 7, 9, 11}
       예제배열 B = {2, 4, 6, 8, 10}
       를 Merge 배열에 순서대로 합병하라
       실행 결과 Merge: [1,2,3,4,5,6,7,8,9,10,11]*/

    int A[] = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
    int B[] = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
    int Merge[] = new int[20];
    int J, K, L;

    //정렬되어 있지 않을 수 있으니 정렬
    A = arr(A);
    B= arr(B);

    J=0;
    K=0;
    L=0;
  }

  public static int[] arr (int arr[]) {
    for(int i=0; i<arr.length; i++) {
      for(int j=1; j<arr.length;j++) {
        arr[i] = (arr[i]<arr[j])?arr[i]:arr[j];
      }
    }
    return arr;
  }
  
  public static void sort() {
    
  }

}
