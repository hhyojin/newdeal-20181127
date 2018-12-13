package s181213.product;

class Product{
  int price;
  int bonuspoint;
  
  public Product() {
   
  }
  
  Product(int price) {
   this.price = price;
   this.bonuspoint = (int)(this.price/10.0);
  }

}


