package chapter12

object exercise07 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // currying
  
  def adjustToPair(fn: (Int, Int) => Int)(x: (Int, Int)) = fn(x._1, x._2)
                                                  //> adjustToPair: (fn: (Int, Int) => Int)(x: (Int, Int))Int
    
  def adjustToPair2(fn: (Int, Int) => Int) = (x: (Int, Int)) => fn(x._1, x._2)
                                                  //> adjustToPair2: (fn: (Int, Int) => Int)((Int, Int)) => Int
  adjustToPair(_ * _)((6, 7))                     //> res0: Int = 42
  adjustToPair2(_ * _)((6, 7))                    //> res1: Int = 42
  
  val pairs = (1 to 10) zip (11 to 20)            //> pairs  : scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((1,11), 
                                                  //| (2,12), (3,13), (4,14), (5,15), (6,16), (7,17), (8,18), (9,19), (10,20))
  pairs map adjustToPair(_ + _)                   //> res2: scala.collection.immutable.IndexedSeq[Int] = Vector(12, 14, 16, 18, 20
                                                  //| , 22, 24, 26, 28, 30)
}