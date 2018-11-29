import java.sql.Date;
import java.util.Scanner;

public class App3 {

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    final int LENGTH = 10;
    
    Board[] board = new Board[LENGTH];
    
    int i = 0;
    while (i < LENGTH) {
      
      board[i] = new Board();
      
      System.out.print("번호? ");
      board[i].no = Integer.parseInt(keyboard.nextLine());
      
      System.out.print("내용? ");
      board[i].contents = keyboard.nextLine();
      
      board[i].createdDate = new Date(System.currentTimeMillis()); 
      
      board[i].viewCount = 0;
      
      i++;
      
      System.out.print("\n계속 입력하시겠습니까?(Y/n) ");
      String answer = keyboard.nextLine().toLowerCase();
      
      if (!answer.equals("y") && answer.length() > 0) {
        break;
      }

      System.out.println();
    }
    
    keyboard.close();
    
    System.out.println();
    
    // 배열에 입력한 개수만큼 출력한다.
    for (int j = 0; j < i; j++) {
      System.out.printf("%3d, %-20s, %s, %d\n", 
          board[j].no, 
          board[j].contents, 
          board[j].createdDate, 
          board[j].viewCount);
    }
  }
}
