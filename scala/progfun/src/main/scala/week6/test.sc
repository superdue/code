package week6

object test {
  
  val xs = Array(1, 2, 3, 44)                     //> xs  : Array[Int] = Array(1, 2, 3, 44)
  xs map (x => x * 2)                             //> res0: Array[Int] = Array(2, 4, 6, 88)
  
  val s = "Hello World"                           //> s  : String = Hello World
  s filter (c => c.isUpper)                       //> res1: String = HW
  
  s exists (c => c.isUpper)                       //> res2: Boolean = true
  s forall (c => c.isUpper)                       //> res3: Boolean = false
  
  val pairs = List(1, 2, 3) zip s                 //> pairs  : List[(Int, Char)] = List((1,H), (2,e), (3,l))
  pairs.unzip                                     //> res4: (List[Int], List[Char]) = (List(1, 2, 3),List(H, e, l))
  
  s flatMap (c => List('.', c))                   //> res5: String = .H.e.l.l.o. .W.o.r.l.d
  
  xs.sum                                          //> res6: Int = 50
  xs.max                                          //> res7: Int = 44
  
  def func(m: Int, n: Int) =
    (1 to m) flatMap (x => (1 to n) map (y => (x, y)))
                                                  //> func: (m: Int, n: Int)scala.collection.immutable.IndexedSeq[(Int, Int)]
    
  // pattern matching function value.
  def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
    (xs zip ys).map { case (x, y) => x * y }.sum  //> scalarProduct: (xs: Vector[Double], ys: Vector[Double])Double
    
  // excercise
  def isPrime(n: Int): Boolean = (2 until n) forall (d => n%d != 0)
                                                  //> isPrime: (n: Int)Boolean
  
  
  
  1 to 5 by 2                                     //> res8: scala.collection.immutable.Range = Range(1, 3, 5)
  "hello" flatMap (c => List('.', c))             //> res9: String = .h.e.l.l.o
}