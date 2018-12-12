package s181212;

import java.util.ArrayList;
import java.util.List;

public class Account {
  private int accountNo;
  private String name;
  private int balance; //잔고
  private List<Transaction> transactions;

  public Account() { //생성자에 넣으면 안 됨
    transactions = new ArrayList<Transaction>();
  }
  
  public Account(List<Transaction> transactions) {
    this.transactions = transactions;
  }

}