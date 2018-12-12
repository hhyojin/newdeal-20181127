package s181212;

import java.util.ArrayList;
import java.util.List;

public class Bank {
  private Account[] accounts;
  private int totalAccount;
  
  public Bank(Account[] accounts) {
    this.accounts = accounts;
    totalAccount = accounts.length;
  }
  
  public void aaa() {
    List<Transaction> transactions = new ArrayList<>();
    Account a = new Account(transactions);
  }
 
}
