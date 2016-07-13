package chapter10

object exercise02 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 整合 <http://www.ivanpig.com/blog/?cat=84&paged=1> 和 <https://github.com/hempalex/scala-impatient/blob/master/Chapter10/04.scala>

  import java.awt.Point

  class OrderedPoint(x: Int, y: Int) extends Point(x, y) with Ordered[Point] {
    def compare(that: Point): Int = if (this.x <= that.x && this.y < that.y) -1
    else if (this.x == that.x && this.y == that.y) 0
    else 1
  }

  val x1 = new OrderedPoint(1, 1)                 //> x1  : chapter10.exercise02.OrderedPoint = chapter10.exercise02$$anonfun$main
                                                  //| $1$OrderedPoint$1[x=1,y=1]
  val x2 = new OrderedPoint(1, -1)                //> x2  : chapter10.exercise02.OrderedPoint = chapter10.exercise02$$anonfun$main
                                                  //| $1$OrderedPoint$1[x=1,y=-1]
  val x3 = new OrderedPoint(2, 1)                 //> x3  : chapter10.exercise02.OrderedPoint = chapter10.exercise02$$anonfun$main
                                                  //| $1$OrderedPoint$1[x=2,y=1]

  x1 < x2                                         //> res0: Boolean = false
  x1 > x2                                         //> res1: Boolean = true
  x1 >= x3                                        //> res2: Boolean = true

}