package chapter08

object exercise05 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  class Point(val x: Int, val y: Int) {
    override def toString = "Point(%d, %d)".format(x, y)
  }

  class LabeledPoint(val label: String, x: Int, y: Int) extends Point(x, y) {
    override def toString = { "LabeledPoint(%s, %d, %d)".format(label, x, y) }
  }

  val a = new Point(1, 1)                         //> a  : chapter08.exercise05.Point = Point(1, 1)
  a                                               //> res0: chapter08.exercise05.Point = Point(1, 1)

  val b = new LabeledPoint("Some point", 20, 50)  //> b  : chapter08.exercise05.LabeledPoint = LabeledPoint(Some point, 20, 50)
  b                                               //> res1: chapter08.exercise05.LabeledPoint = LabeledPoint(Some point, 20, 50)
}