package s181212;

class Triangle extends Shape{
  private Point[] pointarray;
  
  public Triangle() {    
      this(new Point[]{new Point(1,2),new Point(3,4),new Point(5,6)});
  }
  
  public Triangle(Point[] pointarray) {
      this.pointarray = pointarray;
  }
  
  public void triangePoint() {
      for(Point point : pointarray) {
          System.out.println("좌표: " + point.x + "," + point.y);
      }
  }
  

//Triangle triangle = new Triangle();
//triangle.triangePoint();
//  Point[] newpoint = {new Point(1,2),new Point(3,4),new Point(5,6)};
//  Triangle triangle = new Triangle(newpoint);
//  triangle.triangePoint();
  
  
  
}