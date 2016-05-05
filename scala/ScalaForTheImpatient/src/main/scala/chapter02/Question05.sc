package ch02

object Question05 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val y = println("hello")                        //> hello
                                                  //| y  : Unit = ()
  
  // 5. 编写一个过程 countdown(n: Int)，打印从 n 到 0 的数字。
  def countdown(n: Int): Unit = {
    for (i <- (0 to n).reverse) println(i)
  }                                               //> countdown: (n: Int)Unit
  countdown(3)                                    //> 3
                                                  //| 2
                                                  //| 1
                                                  //| 0
}