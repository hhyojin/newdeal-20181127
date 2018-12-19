package q181218;

import java.util.Scanner;

public class CompressProgram {

  public static void main(String[] args) {
    /*문자열을 입력받아서, 같은 문자가 연속적으로 반복되는 경우에 그 반복횟수를 표시해 문자열 압축
    입력 예: aaabbcccc
    출력 예:a3b2c4*/
    
    Scanner sc = new Scanner(System.in);
    System.out.print("문자입력 >> ");
    String str = sc.nextLine();
    StringCompress compress = new StringCompress();
    compress.compress(str);

  }

}
