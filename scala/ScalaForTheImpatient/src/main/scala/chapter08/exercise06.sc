package chapter08

object exercise06 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  class Point(val x: Int, val y: Int) {
    override def toString = "Point(%d, %d)".format(x, y)
  }

  abstract class Shape {
    def centerPoint: Point
    override def toString = "%s(Center: %s)".format(this.getClass.getSimpleName, centerPoint.toString)
  }

  class Rectangle(val topLeft: Point, val bottomRight: Point) extends Shape {
    override val centerPoint = new Point((bottomRight.x - topLeft.x) / 2, (bottomRight.y - topLeft.y) / 2)
  }

  class Circle(override val centerPoint: Point, val radius: Int) extends Shape

  val r = new Rectangle(new Point(0, 0), new Point(10, 10))
                                                  //> r  : chapter08.exercise06.Rectangle = anonfun$main$1$Rectangle$1(Center: Poi
                                                  //| nt(5, 5))
  r                                               //> res0: chapter08.exercise06.Rectangle = anonfun$main$1$Rectangle$1(Center: Po
                                                  //| int(5, 5))

  val c = new Circle(new Point(7, 7), 10)         //> c  : chapter08.exercise06.Circle = anonfun$main$1$Circle$1(Center: Point(7, 
                                                  //| 7))
  c                                               //> res1: chapter08.exercise06.Circle = anonfun$main$1$Circle$1(Center: Point(7,
                                                  //|  7))
}