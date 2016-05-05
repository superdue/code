package ch02

object Question04 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 4. 针对下列 Java 循环编写一个 Scala 版：
  //    for (int i = 10; i >= 0; i--) System.out.println(i);
  
  1 to 5                                          //> res0: scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5)
  for (i <- (1 to 10).reverse) println(i)         //> 10
                                                  //| 9
                                                  //| 8
                                                  //| 7
                                                  //| 6
                                                  //| 5
                                                  //| 4
                                                  //| 3
                                                  //| 2
                                                  //| 1
}