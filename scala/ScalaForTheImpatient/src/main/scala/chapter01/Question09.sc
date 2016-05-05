package ch01

object Question09 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 9. 在 Scala 中如何获取字符串的首字符和尾字符？
  "hello".head                                    //> res0: Char = h
  "hello".tail                                    //> res1: String = ello
  "hello".last                                    //> res2: Char = o
}