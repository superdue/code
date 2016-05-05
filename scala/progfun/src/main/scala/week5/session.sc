package week5

object session {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def removeAt(n: Int, xs: List[Int]) = (xs take n) ::: (xs drop n + 1)
                                                  //> removeAt: (n: Int, xs: List[Int])List[Int]
  
  
  val x = 1                                       //> x  : Int = 1
  val y = 2                                       //> y  : Int = 2
  val xs = 3 :: Nil                               //> xs  : List[Int] = List(3)
  val ys = 4 :: Nil                               //> ys  : List[Int] = List(4)
  
  val zs = Nil                                    //> zs  : scala.collection.immutable.Nil.type = List()
  
  (x :: y :: List(xs, ys) :: zs).length           //> res0: Int = 3
}