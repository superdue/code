package chapter14

object exercise10 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  import scala.math._
  def f(x: Double) = if (x >= 0) Some(sqrt(x)) else None
                                                  //> f: (x: Double)Option[Double]
  def g(x: Double) = if (x != 1) Some(1 / (x - 1)) else None
                                                  //> g: (x: Double)Option[Double]
  
  type T = Double => Option[Double]
  def compose(f: T, g: T): T = {
    (x: Double) => f(x) match {
      case Some(x) => g(x)
      case None => None
    }
  }                                               //> compose: (f: Double => Option[Double], g: Double => Option[Double])Double =>
                                                  //|  Option[Double]
  val h = compose(f, g)                           //> h  : Double => Option[Double] = <function1>
  h(2)                                            //> res0: Option[Double] = Some(2.4142135623730945)
  h(1)                                            //> res1: Option[Double] = None
  h(0)                                            //> res2: Option[Double] = Some(-1.0)
  
  // 上面的compose函数是错的。下面这个才是正确的 <https://github.com/alanktwong/scala-for-impatient/blob/master/core/src/main/scala/scala/impatient/Exercises14.scala>
  
  def compose2(f: Double => Option[Double], g: Double => Option[Double]): Double => Option[Double] = {
    g.apply(_) match {
      case Some(x) => f.apply(x)
      case None => None
    }
  }                                               //> compose2: (f: Double => Option[Double], g: Double => Option[Double])Double =
                                                  //| > Option[Double]
  val h2 = compose2(f, g)                         //> h2  : Double => Option[Double] = <function1>
  h2(2)                                           //> res3: Option[Double] = Some(1.0)
  h2(1)                                           //> res4: Option[Double] = None
  h2(0)                                           //> res5: Option[Double] = None
  
  // 另外一种解法 <http://www.ivanpig.com/blog/?p=513>
  def compose3(f: Double => Option[Double], g: Double => Option[Double]): Double => Option[Double] = {
    (x: Double) =>
      if (f(x) == None || g(x) == None) None
      else g(x)
  }                                               //> compose3: (f: Double => Option[Double], g: Double => Option[Double])Double 
                                                  //| => Option[Double]
  
  val h3 = compose3(f, g)                         //> h3  : Double => Option[Double] = <function1>
  h3(2)                                           //> res6: Option[Double] = Some(1.0)
  h3(1)                                           //> res7: Option[Double] = None
  h3(0)                                           //> res8: Option[Double] = Some(-1.0)
}