package s181213.product;

class KtTv extends Product{
  KtTv(){
   super(500); //super: 상위 클래스의 생성자 호출
  }
  @Override
  public String toString() { //toString은 object 재정의한 것
   return "KtTv";
  }
 }

 
