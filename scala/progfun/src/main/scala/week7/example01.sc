package week7

object example01 {
  
  def expr = {
    val x = { print("x"); 1 }
    lazy val y = { print("y"); 2 }
    def z = { print("z"); 3 }
    z + y + x + z + y + x
  }                                               //> expr: => Int
  expr                                            //> xzyzres0: Int = 12
  
  val xs = Stream.cons(1, Stream.cons(2, Stream.empty))
                                                  //> xs  : Stream.Cons[Int] = Stream(1, ?)
  (1 to 1000).toStream                            //> res1: scala.collection.immutable.Stream[Int] = Stream(1, ?)
  
  def streamRange(lo: Int, hi: Int): Stream[Int] =
    if (lo >= hi) Stream.empty
    else Stream.cons(lo, streamRange(lo + 1, hi)) //> streamRange: (lo: Int, hi: Int)Stream[Int]
}