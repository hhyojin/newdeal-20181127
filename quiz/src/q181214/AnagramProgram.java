package q181214;

import java.util.Scanner;

public class AnagramProgram {

  public static void main(String[] args) {
    Anagram anagram = new Anagram();
    
    String first;
    String second;
    Scanner input = new Scanner(System.in);

    // 문장을 받을 수도 있기에 nextLine() 활용
    System.out.print("첫번째 단어/문장 입력:");
    first = input.nextLine();
    
    System.out.print("두번째 단어/문장 입력:");
    second = input.nextLine();

    System.out.println("Anagram ? "+anagram.checkAnagram(first,second));

  }

}
