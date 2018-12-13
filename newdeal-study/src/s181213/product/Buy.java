package s181213.product;

import java.util.Scanner;

public class Buy {

  public static void main(String[] args) {
    KtTv kttv = new KtTv();
    Audio audio = new Audio();
    NoteBook notebook = new NoteBook();
    Buyer buyer;

    Scanner sc = new Scanner(System.in);
    System.out.print("초기 금액을 설정해 주세요(기본금액: 5000): ");

    int setMoney = Integer.parseInt(sc.nextLine());

    if (setMoney != 0) {
      buyer = new Buyer(setMoney);    
    } else {
      buyer = new Buyer();
    }

    // 아래처럼 가능
    buyer.buy(kttv);
    buyer.buy(audio);
    buyer.buy(notebook);
    buyer.summary();

  }

}
