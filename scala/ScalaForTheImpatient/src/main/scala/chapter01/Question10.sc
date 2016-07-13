package ch01

object Question10 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 10. take、drop、takeRight 和 dropRight 这些字符串函数是做什么用的？和 substring 相比，它们的优点和缺点都有哪些？
  
  val x = "hello"                                 //> x  : String = hello
  x take 2                                        //> res0: String = he
  x drop 3                                        //> res1: String = lo
  x takeRight 3                                   //> res2: String = llo
  x dropRight 1                                   //> res3: String = hell
  
  x.substring(0, 3)                               //> res4: String = hel
  x.substring(2, 4)                               //> res5: String = ll
  x take 4 drop 2                                 //> res6: String = ll
}