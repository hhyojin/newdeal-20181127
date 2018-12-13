package s181213.product;

import java.util.ArrayList;
import java.util.List;

public class Buyer {

  private static int buyerMoney;
  private static int totalPrice;
  private static int buyerPoint=0;
  private Product product;
  private List<Product> cart = new ArrayList<>(10);

  public Buyer() {
    this.buyerMoney = 5000;
  }

  public Buyer(int setMoney) {
    this.buyerMoney = setMoney;
  }

  public void buy (Product product) {

    this.product = product;

    if(cart.size() > 9) {
      System.out.println("카트에 더 이상 담을 수 없습니다.");
      return;
    } else {
      cart.add(product); 
      System.out.printf("카트에 %s 상품을 담았습니다\n", product.toString()); }
  }


  public void summary () {
    
    for(int i=0;i<cart.size();i++) {
      totalPrice += cart.get(i).price;
      buyerPoint += cart.get(i).bonuspoint;
    }   
    if(buyerMoney < totalPrice) {
      System.out.println("예산 초과");      
    } else {
      
      System.out.printf("총 누적금액: %d\n", totalPrice);
      System.out.printf("남은 금액: %d\n", buyerMoney-totalPrice);
      System.out.printf("총 포인트: %d\n", buyerPoint);
      
      for(int i=0;i<cart.size();i++) {
        System.out.printf("구매한 물품명: %s\n", cart.get(i).toString());
        System.out.printf("구매한 물품 가격: %d\n", cart.get(i).price);      }
    }
  } 

}
