package week5

object example01 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def isort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case y :: ys => insert(y, isort(ys))
  }                                               //> isort: (xs: List[Int])List[Int]
  
  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (x > y) y :: insert(x, ys) else x :: xs
  }                                               //> insert: (x: Int, xs: List[Int])List[Int]
}