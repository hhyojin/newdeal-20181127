package q181221;

public class ArrProgram {

  public static void main(String[] args) {
    /*선택정렬:
      https://terms.naver.com/entry.nhn?docId=2270435&cid=51173&categoryId=51173&expCategoryId=51173

      버블정렬:
      https://terms.naver.com/entry.nhn?docId=2270437&cid=51173&categoryId=51173&expCategoryId=51173

      선택정렬과 버블정렬을 이해한 후, 아래 콘솔창과 유사하게 나오도록 Arr 클래스를 구현하세요*/

          int[] array1 = {3, 4, 2, 5, 1};
          int[] array2 = {3, 4, 2, 5, 1};
          Arr arr = new Arr();
       
          System.out.println("선택정렬입니다:");
          arr.select(array1);
          System.out.println();
          System.out.println("버블정렬입니다:");
          arr.bubble(array2);
        }
       

}

/*선택정렬입니다:
현재 PASS
3 와 4 비교
3 와 2 비교
2 와 5 비교
2 와 1 비교
현재 PASS
4 와 2 비교
2 와 5 비교
2 와 3 비교
현재 PASS
4 와 5 비교
4 와 3 비교
현재 PASS
5 와 4 비교
[1, 2, 3, 4, 5]
 
버블정렬입니다:
현재PASS
3 와 4 비교
4 와 2 비교
4 와 5 비교
5 와 1 비교
현재PASS
3 와 2 비교
3 와 4 비교
4 와 1 비교
현재PASS
2 와 3 비교
3 와 1 비교
현재PASS
2 와 1 비교
[1, 2, 3, 4, 5]*/