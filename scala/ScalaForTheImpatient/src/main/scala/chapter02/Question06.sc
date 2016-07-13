package ch02

object Question06 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 6. 编写一个 for 循环，计算字符串中所有字母的 Unicode 代码的乘积。举例来说，"Hello" 中所有字符的乘积为 9415087488L。
  var sum: Long = 1L                              //> sum  : Long = 1
  for (c <- "Hello") {
    sum = sum * c.toLong
  }
  sum                                             //> res0: Long = 9415087488
  
  "Hello".foldLeft(1L)(_ * _.toLong)              //> res1: Long = 9415087488
}