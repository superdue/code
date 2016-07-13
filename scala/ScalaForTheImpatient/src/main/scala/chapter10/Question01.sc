package ch10

object Question01 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 1. java.awt.Rectangle 类有两个很有用的方法 translate 和 grow，但可惜的是像 java.awt.geom.Ellipse2D 这样的类中没有。在 Scala 中，你可以解决掉这个问题。定义一个 RectangleLike 特质，加入具体的 translate 和 grow 方法。提供任何你需要用来实现的抽象方法，以便你可以像如下代码这样混入该特质：
  //    val egg = new java.awt.geom.Ellipse2D.Double(5, 10, 20, 30) with RectangleLike
  //    egg.translate(10, -10)
  //    egg.grow(10, 20)
}