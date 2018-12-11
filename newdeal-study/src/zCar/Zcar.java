package zCar;

public class Zcar {
  
    String color;
    String geartype;
    int door;
    
    Zcar(){
        this.color = "red";
        this.geartype = "auto";
        this.door = 4;
        System.out.println("default constructor");
    }
    Zcar(String color , String geartype , int door){
        this.color = color;
        this.geartype = geartype;
        this.door = door;
        System.out.println("overloading constructor");
    }
    void print() {
        System.out.println(    this.color + "/" + this.geartype + "/" + this.door);
    
    }

}
