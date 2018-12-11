package zCar;

class Zcar2{
  String color;
  String geartype;
  int door;
  
  Zcar2(){
      this("red", 1);
      System.out.println("default constructor");
  }
  Zcar2(String color ,int door){
      this(color,"auto",door);
      System.out.println("overloading constructor param 2 개");
  }
  
  Zcar2(String color , String geartype , int door){
      this.color = color;
      this.geartype = geartype;
      this.door = door;
      System.out.println("overloading constructor param 3 개");
  }
  void print() {
      System.out.println(    this.color + "/" + this.geartype + "/" + this.door);
  
  }
}
