package test;

public class Patient {
  //필드
  private String name; //필드명: _name
  private int age;    //필드명: age
  private int _weight;

  private int height;
  
  
  //setter/getter = property
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public int getWeight() {
    return _weight;
  }
  public void setWeight(int weight) {
    this._weight = weight;
  }
  public int getHeight() {
    return height;
  }
  public void setHeight(int height) {
    this.height = height;
  }
  
  
  
}
